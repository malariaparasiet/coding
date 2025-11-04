# go-ipxl

A Go library for controlling iPixel Color LED displays via Bluetooth Low Energy (BLE).

## Overview

go-ipxl provides a clean, idiomatic Go interface for communicating with iPixel Color devices. The library handles BLE connectivity, device discovery, image processing, and data transmission protocols required to control LED matrix displays.

## Features

- **BLE Connectivity**: Seamless Bluetooth Low Energy connection management
- **Device Information**: Query device specifications, firmware versions, and screen dimensions
- **Image Display**: Send images directly to LED matrices with automatic scaling and color conversion
- **Format Support**: Built-in support for PNG and JPEG image formats
- **DIY Modes**: Switch between different display modes including DIY functionality
- **Low-Level Control**: Direct LED control and raw data transmission capabilities
- **Robust Protocol**: Implements chunked data transmission with CRC verification

## Installation

```bash
go get github.com/yyewolf/go-ipxl
```

## Quick Start

```go
package main

import (
    "context"
    "log"
    "time"
    
    "github.com/yyewolf/go-ipxl"
)

func main() {
    // Create a new display instance
    display := ipxl.NewDisplay("AA:BB:CC:DD:EE:FF")
    
    // Connect to the device
    ctx, cancel := context.WithTimeout(context.Background(), 30*time.Second)
    defer cancel()
    
    if err := display.Connect(ctx); err != nil {
        log.Fatal("Failed to connect:", err)
    }
    defer display.Disconnect()
    
    // Get device information
    info, err := display.GetDeviceInfo(ctx)
    if err != nil {
        log.Fatal("Failed to get device info:", err)
    }
    
    log.Printf("Device: %dx%d matrix, MCU: %s, WiFi: %s", 
        info.Width, info.Height, info.MCUVersion, info.WiFiVersion)
    
    // Load and send an image
    processor := ipxl.NewBitmapProcessor(info.Width, info.Height)
    img, err := processor.LoadImageFromFile("image.png")
    if err != nil {
        log.Fatal("Failed to load image:", err)
    }
    
    if err := display.SendImage(ctx, img); err != nil {
        log.Fatal("Failed to send image:", err)
    }
}
```

## API Reference

### Display Interface

The main interface for interacting with iPixel devices:

```go
type Display interface {
    Connect(ctx context.Context) error
    Disconnect() error
    GetScreenSize(ctx context.Context) (width, height int, err error)
    GetDeviceInfo(ctx context.Context) (*DeviceInfo, error)
    SetLED(on bool) error
    SendImage(ctx context.Context, image image.Image) error
    SwitchToDiyFunMode(mode int) error
}
```

### Device Information

Query device specifications and capabilities:

```go
type DeviceInfo struct {
    DeviceType   byte   // Device type identifier
    MCUVersion   string // Microcontroller firmware version
    WiFiVersion  string // WiFi/BLE module version
    Width        int    // LED matrix width
    Height       int    // LED matrix height
    HasWifi      bool   // WiFi capability flag
    PasswordFlag byte   // Password requirement status
}
```

### Image Processing

Convert and process images for LED display:

```go
// Create a bitmap processor for your display dimensions
processor := ipxl.NewBitmapProcessor(64, 64)

// Load from file
img, err := processor.LoadImageFromFile("path/to/image.png")

// Convert to RGB data
rgbData := processor.ImageToRGB(img)
```

## Supported Image Formats

- PNG
- JPEG
- Any format supported by Go's `image` package

Images are automatically resized and color-converted to match the target LED matrix dimensions.

## Connection Management

The library handles BLE connection lifecycle automatically:

- Device scanning and discovery
- Characteristic enumeration
- Connection timeout handling
- Automatic cleanup on disconnect

## Data Transmission

Images are transmitted using a chunked protocol with:

- 12KB chunk size for optimal BLE throughput
- CRC32 verification for data integrity
- Sequential transmission with proper packet ordering
- Support for various data types (images, videos, text, etc.)

## Error Handling

All operations return descriptive errors for proper error handling:

```go
if err := display.Connect(ctx); err != nil {
    switch {
    case errors.Is(err, context.DeadlineExceeded):
        log.Println("Connection timeout")
    case strings.Contains(err.Error(), "characteristic not found"):
        log.Println("Device not compatible")
    default:
        log.Printf("Connection failed: %v", err)
    }
}
```

## Requirements

- Go 1.21+
- Bluetooth Low Energy support
- Platform support via [TinyGo Bluetooth](https://github.com/tinygo-org/bluetooth)

## Platform Support

The library uses TinyGo's bluetooth package, which supports:

- Linux (BlueZ)
- macOS (Core Bluetooth)
- Windows (WinRT)

## Contributing

Contributions are welcome! Please feel free to submit pull requests, report bugs, or suggest features.

## Acknowledgments

Based on the iPixel protocol implementation and inspired by the original Android SDK.
