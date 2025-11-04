package ipxl

import (
	"context"
	"fmt"
	"time"

	"tinygo.org/x/bluetooth"
)

// DeviceInfo contains information retrieved from the LED device
type DeviceInfo struct {
	DeviceType   byte   // Byte 4 from device response
	MCUVersion   string // Major.Minor version
	WiFiVersion  string // Major.Minor version (or BLE version)
	Width        int    // LED matrix width
	Height       int    // LED matrix height
	HasWifi      bool   // Whether device supports WiFi
	PasswordFlag byte   // Password requirement flag
}

// GetScreenSize queries the device and returns the LED matrix dimensions
func (disp *display) GetScreenSize(ctx context.Context) (width, height int, err error) {
	deviceInfo, err := disp.GetDeviceInfo(ctx)
	if err != nil {
		return 0, 0, fmt.Errorf("failed to get device info: %v", err)
	}

	return deviceInfo.Width, deviceInfo.Height, nil
}

// GetDeviceInfo queries the device for hardware information and screen size
func (disp *display) GetDeviceInfo(ctx context.Context) (*DeviceInfo, error) {
	if disp.writeChar == (bluetooth.DeviceCharacteristic{}) {
		return nil, fmt.Errorf("device not connected or characteristic not found")
	}

	// Channel to receive notification data
	responseChan := make(chan []byte, 1)
	errorChan := make(chan error, 1)

	// Enable notifications on the characteristic
	err := disp.notifyChar.EnableNotifications(func(data []byte) {
		responseChan <- data
	})
	if err != nil {
		return nil, fmt.Errorf("failed to enable notifications: %v", err)
	}
	defer disp.notifyChar.EnableNotifications(nil)

	// Build the getLedType command based on Android implementation
	// Command format: [8, 0, 1, 128, hour, minute, second, language]
	now := time.Now()
	cmd := []byte{
		8,                  // Command header
		0,                  // Reserved
		1,                  // Sub-command
		128,                // 0x80 (corresponds to -128 in signed byte)
		byte(now.Hour()),   // Current hour
		byte(now.Minute()), // Current minute
		byte(now.Second()), // Current second
		0,                  // Language (0 for default)
	}

	// Send the command
	_, err = disp.writeChar.WriteWithoutResponse(cmd)
	if err != nil {
		return nil, fmt.Errorf("failed to write getLedType command: %v", err)
	}

	// Wait for response with timeout
	select {
	case response := <-responseChan:
		return disp.parseDeviceInfo(response)
	case err := <-errorChan:
		return nil, fmt.Errorf("notification error: %v", err)
	case <-time.After(5 * time.Second):
		return nil, fmt.Errorf("timeout waiting for device response")
	case <-ctx.Done():
		return nil, ctx.Err()
	}
}

// parseDeviceInfo parses the device response and extracts screen size and other info
func (disp *display) parseDeviceInfo(response []byte) (*DeviceInfo, error) {
	if len(response) < 5 {
		return nil, fmt.Errorf("response too short: got %d bytes, need at least 5", len(response))
	}

	deviceInfo := &DeviceInfo{}

	// Extract device type from byte 4 (as per Android app logic)
	deviceTypeByte := response[4]
	deviceInfo.DeviceType = deviceTypeByte

	// Map device type byte to LED type and get dimensions
	ledType, exists := deviceTypeMap[deviceTypeByte]
	if !exists {
		// Default to type 0 if unknown (as per Android app)
		ledType = 0
	}

	// Get screen dimensions from LED size map
	if dimensions, exists := ledSizeMap[ledType]; exists {
		deviceInfo.Width = dimensions[0]
		deviceInfo.Height = dimensions[1]
	} else {
		// Default to 64x64 if type not found
		deviceInfo.Width = 64
		deviceInfo.Height = 64
	}

	// Parse version information if response is long enough
	if len(response) >= 8 {
		// MCU Version (bytes 4-5): major.minor
		mcuMajor := response[4]
		mcuMinor := response[5]
		if mcuMinor < 10 {
			deviceInfo.MCUVersion = fmt.Sprintf("%d.0%d", mcuMajor, mcuMinor)
		} else {
			deviceInfo.MCUVersion = fmt.Sprintf("%d.%d", mcuMajor, mcuMinor)
		}

		// WiFi/BLE Version (bytes 6-7): major.minor
		wifiMajor := response[6]
		wifiMinor := response[7]
		if wifiMinor < 10 {
			deviceInfo.WiFiVersion = fmt.Sprintf("%d.0%d", wifiMajor, wifiMinor)
		} else {
			deviceInfo.WiFiVersion = fmt.Sprintf("%d.%d", wifiMajor, wifiMinor)
		}
	}

	// Set WiFi capability based on device type (from Android logic)
	deviceInfo.HasWifi = true  // Default to true
	if deviceTypeByte == 134 { // Type 6 (128x32) doesn't have WiFi
		deviceInfo.HasWifi = false
	}

	// Extract password flag if response is long enough
	if len(response) >= 11 {
		deviceInfo.PasswordFlag = response[10]
	} else {
		deviceInfo.PasswordFlag = 255 // Default: no password
	}

	return deviceInfo, nil
}
