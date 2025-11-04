package main

import (
	"context"
	"flag"
	"log"

	pixl "github.com/yyewolf/go-ipxl"
)

var (
	deviceAddress string
	text          = "coucou !"
)

func main() {
	// Get device MAC address from flags, or show help
	flag.StringVar(&deviceAddress, "address", "", "MAC address of the LED display device (e.g., EF:BA:C0:8F:FD:BD)")
	flag.StringVar(&text, "text", text, "Text to display on the LED display")
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

	w, h, err := display.GetScreenSize(context.Background())
	if err != nil {
		log.Fatalf("Failed to get display height and width: %v", err)
	}

	err = clearScreen(display)
	if err != nil {
		log.Fatalf("Failed to switch to DIY mode: %v", err)
	}

	// Create a text image
	textImg, err := StringToImage(w, h, text)
	if err != nil {
		log.Fatalf("Failed to create text image: %v", err)
	}

	// Send the text image to the display
	err = display.SendImage(context.Background(), textImg)
	if err != nil {
		log.Fatalf("Failed to send text image: %v", err)
	}

	log.Println("Text image sent successfully!")
	select {}
}

func clearScreen(display pixl.Display) error {
	return display.SwitchToDiyFunMode(pixl.DIY_IMAGE_FUN_ENTER_CLEAR_CUR_SHOW)
}
