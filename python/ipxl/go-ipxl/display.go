package ipxl

import (
	"context"
	"fmt"
	"image"
	"image/color"
	"time"

	"tinygo.org/x/bluetooth"
)

type Display interface {
	Connect(ctx context.Context) error
	Disconnect() error

	GetScreenSize(ctx context.Context) (width, height int, err error)
	GetDeviceInfo(ctx context.Context) (*DeviceInfo, error)
	SetLED(on bool) error
	SendImage(ctx context.Context, image image.Image) error
	SwitchToDiyFunMode(mode int) error
}

type display struct {
	address string

	device     bluetooth.Device
	writeChar  bluetooth.DeviceCharacteristic
	notifyChar bluetooth.DeviceCharacteristic
}

// Position represents a pixel coordinate
type Position struct {
	X, Y int
}

func NewDisplay(address string) Display {
	return &display{address: address}
}

// Connect establishes BLE connection to the LED display
func (disp *display) Connect(ctx context.Context) error {
	// Enable BLE
	err := bluetooth.DefaultAdapter.Enable()
	if err != nil {
		return fmt.Errorf("failed to enable BLE adapter: %v", err)
	}

	// Start scanning
	err = bluetooth.DefaultAdapter.Scan(func(adapter *bluetooth.Adapter, result bluetooth.ScanResult) {
		if result.Address.String() == disp.address {
			// Stop scanning
			adapter.StopScan()

			// Connect to device
			device, err := adapter.Connect(result.Address, bluetooth.ConnectionParams{})
			if err != nil {
				return
			}

			disp.device = device
		}
	})

	if err != nil {
		return fmt.Errorf("failed to start scan: %v", err)
	}

	// Wait for connection with timeout
	timeout := time.NewTimer(10 * time.Second)
	ticker := time.NewTicker(100 * time.Millisecond)
	defer timeout.Stop()
	defer ticker.Stop()

	for disp.device == (bluetooth.Device{}) {
		select {
		case <-timeout.C:
			return fmt.Errorf("connection timeout")
		case <-ticker.C:
		}
	}

	// Discover services
	services, err := disp.device.DiscoverServices(nil)
	if err != nil {
		return fmt.Errorf("failed to discover services: %v", err)
	}

	// Find the characteristics
	for _, service := range services {
		chars, err := service.DiscoverCharacteristics(nil)
		if err != nil {
			continue
		}

		for _, char := range chars {
			switch char.UUID().String() {
			case WRITE_CHAR_UUID:
				disp.writeChar = char
			case NOTIFY_CHAR_UUID:
				disp.notifyChar = char
			}
		}
	}

	if disp.writeChar == (bluetooth.DeviceCharacteristic{}) {
		return fmt.Errorf("write characteristic %s not found", WRITE_CHAR_UUID)
	}

	return nil
}

// Disconnect closes the BLE connection
func (disp *display) Disconnect() error {
	if disp.device != (bluetooth.Device{}) {
		return disp.device.Disconnect()
	}
	return nil
}

// SendData sends raw data to the LED display
func (disp *display) SendData(data []byte) error {
	if disp.writeChar == (bluetooth.DeviceCharacteristic{}) {
		return fmt.Errorf("not connected to characteristic")
	}

	_, err := disp.writeChar.WriteWithoutResponse(data)
	if err != nil {
		return fmt.Errorf("failed to write data: %v", err)
	}

	return nil
}

// SetLED turns the LED display on/off
func (disp *display) SetLED(on bool) error {
	onByte := byte(0)
	if on {
		onByte = 1
	}
	data := []byte{5, 0, 7, 1, onByte}
	err := disp.SendData(data)
	if err != nil {
		return err
	}
	return nil
}

// SendPixels sends pixel data to specific positions
func (disp *display) SendPixels(rgb color.RGBA, positions []Position) error {
	if len(positions) == 0 {
		return nil
	}

	// Split into chunks if too many positions
	const maxPositionsPerPacket = 120 // 240 bytes / 2 bytes per position

	for i := 0; i < len(positions); i += maxPositionsPerPacket {
		end := i + maxPositionsPerPacket
		if end > len(positions) {
			end = len(positions)
		}

		chunk := positions[i:end]
		err := disp.sendPixelChunk(rgb, chunk)
		if err != nil {
			return err
		}
	}

	return nil
}

// sendPixelChunk sends a single chunk of pixel data
func (disp *display) sendPixelChunk(rgb color.RGBA, positions []Position) error {
	header := []byte{0, 0, 5, 1, 0, rgb.R, rgb.G, rgb.B} // Length will be set below

	body := make([]byte, 0, len(positions)*2)
	for _, pos := range positions {
		body = append(body, byte(pos.X), byte(pos.Y))
	}

	// Set the total length
	totalLen := len(header) + len(body)
	header[0] = byte(totalLen)

	// Combine header and body
	data := append(header, body...)

	return disp.SendData(data)
}
