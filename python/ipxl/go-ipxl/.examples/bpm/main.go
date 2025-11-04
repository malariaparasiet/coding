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
	heartMonitorAddress string
	displayAddress      string
)

func main() {
	// Get device MAC address from flags, or show help
	flag.StringVar(&heartMonitorAddress, "heart-monitor-address", "", "MAC address of the heart rate monitor device (e.g., EF:BA:C0:8F:FD:BD)")
	flag.StringVar(&displayAddress, "display-address", "", "MAC address of the LED display device (e.g., EF:BA:C0:8F:FD:BD)")
	flag.Parse()

	if heartMonitorAddress == "" || displayAddress == "" {
		flag.Usage()
		return
	}

	display := pixl.NewDisplay(displayAddress)
	err := display.Connect(context.Background())
	if err != nil {
		log.Fatalf("Failed to connect to display: %v", err)
	}
	defer display.Disconnect()

	// Create and connect heart monitor
	heartMonitor := NewHeartMonitor(heartMonitorAddress)
	err = heartMonitor.Connect(context.Background())
	if err != nil {
		log.Fatalf("Failed to connect to heart monitor: %v", err)
	}
	defer heartMonitor.Disconnect()

	err = clearScreen(display)
	if err != nil {
		log.Fatalf("Failed to clear screen: %v", err)
	}

	bpmChannel := heartMonitor.GetBPMChannel()
	var currentBPM int = 0

	// Display initial BPM
	textImg, err := StringToImage(fmt.Sprintf("BPM: %d", currentBPM))
	if err != nil {
		log.Fatalf("Failed to create initial text image: %v", err)
	}
	err = display.SendImage(context.Background(), textImg)
	if err != nil {
		log.Fatalf("Failed to send initial image: %v", err)
	}

	log.Printf("Listening for heart rate data...")

	// Listen for BPM updates
	for {
		select {
		case bpm := <-bpmChannel:
			currentBPM = bpm
			log.Printf("New BPM reading: %d", bpm)

			// Create a text image with the BPM
			textImg, err := StringToImage(fmt.Sprintf("BPM: %d", bpm))
			if err != nil {
				log.Printf("Failed to create text image: %v", err)
				continue
			}

			// Send the BPM to the display
			err = display.SendImage(context.Background(), textImg)
			if err != nil {
				log.Printf("Failed to send BPM image: %v", err)
				continue
			}

		case <-time.After(30 * time.Second):
			// Check if still connected, if not try to reconnect
			if !heartMonitor.IsConnected() {
				log.Printf("Heart monitor disconnected, attempting to reconnect...")
				err = heartMonitor.Connect(context.Background())
				if err != nil {
					log.Printf("Failed to reconnect: %v", err)
				}
			}
		}
	}
}

func clearScreen(display pixl.Display) error {
	return display.SwitchToDiyFunMode(pixl.DIY_IMAGE_FUN_ENTER_CLEAR_CUR_SHOW)
}
