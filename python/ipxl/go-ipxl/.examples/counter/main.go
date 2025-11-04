package main

import (
	"context"
	"flag"
	"fmt"
	"log"
	"time"

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
		log.Fatalf("Failed to connect to device: %v", err)
	}
	defer display.Disconnect()

	err = clearScreen(display)
	if err != nil {
		log.Fatalf("Failed to switch to DIY mode: %v", err)
	}

	var i int
	for {
		// Create a text image
		textImg, err := StringToImage(fmt.Sprintf("%d", i))
		if err != nil {
			log.Fatalf("Failed to create text image: %v", err)
		}

		// Send the text image to the display
		err = display.SendImage(context.Background(), textImg)
		if err != nil {
			log.Fatalf("Failed to send text image: %v", err)
		}

		i++
		time.Sleep(500 * time.Millisecond) // Update every 500 milliseconds
	}
}

func clearScreen(display pixl.Display) error {
	return display.SwitchToDiyFunMode(pixl.DIY_IMAGE_FUN_ENTER_CLEAR_CUR_SHOW)
}
