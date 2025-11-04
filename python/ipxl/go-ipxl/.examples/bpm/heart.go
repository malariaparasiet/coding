package main

import (
	"context"
	"errors"
	"fmt"
	"log"

	"tinygo.org/x/bluetooth"
)

const HR_SERVICE_UUID = "0000180d-0000-1000-8000-00805f9b34fb"
const HR_MEASUREMENT_CHAR_UUID = "00002a37-0000-1000-8000-00805f9b34fb"

type HeartMonitor struct {
	Address string

	bpm       chan int
	adapter   *bluetooth.Adapter
	device    *bluetooth.Device
	service   *bluetooth.DeviceService
	char      *bluetooth.DeviceCharacteristic
	scanning  bool
	connected bool
}

func NewHeartMonitor(address string) *HeartMonitor {
	return &HeartMonitor{
		Address:   address,
		bpm:       make(chan int, 10), // Buffered channel to prevent blocking
		scanning:  false,
		connected: false,
	}
}

// Connect establishes a connection to the heart rate monitor
func (hm *HeartMonitor) Connect(ctx context.Context) error {
	if hm.connected {
		return errors.New("already connected")
	}

	// Enable BLE interface
	err := bluetooth.DefaultAdapter.Enable()
	if err != nil {
		return fmt.Errorf("failed to enable BLE adapter: %w", err)
	}
	hm.adapter = bluetooth.DefaultAdapter

	log.Printf("Scanning for heart rate monitor at %s...", hm.Address)
	hm.scanning = true

	// Start scanning for devices
	err = hm.adapter.Scan(func(adapter *bluetooth.Adapter, device bluetooth.ScanResult) {
		if device.Address.String() == hm.Address {
			log.Printf("Found target device: %s", device.Address.String())
			hm.adapter.StopScan()
			hm.scanning = false

			// Connect to the device
			dev, err := hm.adapter.Connect(device.Address, bluetooth.ConnectionParams{})
			if err != nil {
				log.Printf("Failed to connect to device: %v", err)
				return
			}
			hm.device = &dev
			hm.connected = true

			// Discover services
			err = hm.discoverServices(ctx)
			if err != nil {
				log.Printf("Failed to discover services: %v", err)
				hm.Disconnect()
				return
			}

			log.Printf("Successfully connected to heart rate monitor")
		}
	})

	if err != nil {
		return fmt.Errorf("failed to start scan: %w", err)
	}

	// Wait for connection or timeout
	return nil
}

// Disconnect closes the connection to the heart rate monitor
func (hm *HeartMonitor) Disconnect() error {
	if !hm.connected {
		return nil
	}

	if hm.scanning {
		hm.adapter.StopScan()
		hm.scanning = false
	}

	if hm.device != nil {
		err := hm.device.Disconnect()
		if err != nil {
			log.Printf("Error disconnecting: %v", err)
		}
		hm.device = nil
	}

	hm.connected = false
	hm.service = nil
	hm.char = nil
	close(hm.bpm)
	hm.bpm = make(chan int, 10) // Recreate channel for potential reconnection

	log.Printf("Disconnected from heart rate monitor")
	return nil
}

// IsConnected returns true if the monitor is connected
func (hm *HeartMonitor) IsConnected() bool {
	return hm.connected
}

// GetBPMChannel returns the channel that receives BPM updates
func (hm *HeartMonitor) GetBPMChannel() <-chan int {
	return hm.bpm
}

// discoverServices discovers the heart rate service and characteristics
func (hm *HeartMonitor) discoverServices(ctx context.Context) error {
	services, err := hm.device.DiscoverServices(nil)
	if err != nil {
		return fmt.Errorf("failed to discover services: %w", err)
	}

	if len(services) == 0 {
		return errors.New("heart rate service not found")
	}

	for _, svc := range services {
		if svc.UUID().String() == HR_SERVICE_UUID {
			hm.service = &svc
			break
		}
	}

	if hm.service == nil {
		return errors.New("heart rate service not found")
	}

	log.Printf("Found heart rate service")

	// Discover characteristics
	chars, err := hm.service.DiscoverCharacteristics([]bluetooth.UUID{
		bluetooth.NewUUID([16]byte{0x00, 0x00, 0x2a, 0x37, 0x00, 0x00, 0x10, 0x00, 0x80, 0x00, 0x00, 0x80, 0x5f, 0x9b, 0x34, 0xfb}), // HR Measurement
	})
	if err != nil {
		return fmt.Errorf("failed to discover characteristics: %w", err)
	}

	if len(chars) == 0 {
		return errors.New("heart rate measurement characteristic not found")
	}

	hm.char = &chars[0]
	log.Printf("Found heart rate measurement characteristic")

	// Enable notifications
	err = hm.char.EnableNotifications(func(buf []byte) {
		if len(buf) >= 2 {
			// Parse heart rate measurement according to Bluetooth spec
			var bpm int
			if buf[0]&0x01 == 0 {
				// 8-bit BPM value
				bpm = int(buf[1])
			} else {
				// 16-bit BPM value
				if len(buf) >= 3 {
					bpm = int(buf[1]) | (int(buf[2]) << 8)
				}
			}

			if bpm > 0 && bpm < 300 { // Reasonable BPM range
				select {
				case hm.bpm <- bpm:
					log.Printf("Received BPM: %d", bpm)
				default:
					// Channel is full, skip this measurement
					log.Printf("BPM channel full, skipping measurement: %d", bpm)
				}
			}
		}
	})

	if err != nil {
		return fmt.Errorf("failed to enable notifications: %w", err)
	}

	log.Printf("Enabled heart rate notifications")
	return nil
}
