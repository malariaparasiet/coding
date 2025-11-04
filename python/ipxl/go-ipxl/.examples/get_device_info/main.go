package main

import (
	"context"
	"flag"

	pixl "github.com/yyewolf/go-ipxl"
)

var (
	deviceAddress string
)

func main() {
	// Get device MAC address from flags, or show help
	flag.StringVar(&deviceAddress, "address", "", "MAC address of the LED display device (e.g., EF:BA:C0:8F:FD:BD)")
	flag.Parse()

	if deviceAddress == "" {
		flag.Usage()
		return
	}

	display := pixl.NewDisplay(deviceAddress)
	err := display.Connect(context.Background())
	if err != nil {
		panic(err)
	}

	info, err := display.GetDeviceInfo(context.Background())
	if err != nil {
		panic(err)
	}

	println("Device Info:")
	println("Device Type:", info.DeviceType)
	println("MCU Version:", info.MCUVersion)
	println("WiFi Version:", info.WiFiVersion)
	println("Has Wifi:", info.HasWifi)
	println("Password Flag:", info.PasswordFlag)
	println("Height:", info.Height)
	println("Width:", info.Width)

	height, width, err := display.GetScreenSize(context.Background())
	if err != nil {
		panic(err)
	}
	println("Screen Size:", width, "x", height)
}
