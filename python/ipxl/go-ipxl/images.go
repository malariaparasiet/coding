package ipxl

import (
	"context"
	"fmt"
	"image"
	"image/color"
)

const CHUNK_SIZE = 12288

func (disp *display) SendImage(ctx context.Context, image image.Image) error {
	width, height, err := disp.GetScreenSize(ctx)
	if err != nil {
		return fmt.Errorf("failed to get screen size: %v", err)
	}

	// Convert image to RGB byte array
	rgbData := NewBitmapProcessor(width, height).ImageToRGB(image)

	err = disp.rawSendImage(rgbData, width, height)
	if err != nil {
		return fmt.Errorf("failed to send image using chunk protocol: %v", err)
	}

	return nil
}

func (disp *display) rawSendImage(rgbData []byte, width, height int) error {
	if len(rgbData) != width*height*3 {
		return fmt.Errorf("invalid RGB data size: expected %d, got %d", width*height*3, len(rgbData))
	}

	// Calculate number of chunks needed
	totalChunks := len(rgbData) / CHUNK_SIZE
	if len(rgbData)%CHUNK_SIZE != 0 {
		totalChunks++
	}

	// Create PacketBuilder instance
	packetBuilder := NewPacketBuilder()

	// Send chunks sequentially following Java protocol exactly
	for chunkIndex := 0; chunkIndex < totalChunks; chunkIndex++ {
		// Calculate chunk boundaries
		startPos := chunkIndex * CHUNK_SIZE
		endPos := startPos + CHUNK_SIZE
		if endPos > len(rgbData) {
			endPos = len(rgbData)
		}

		// Extract chunk data
		chunkData := make([]byte, endPos-startPos)
		copy(chunkData, rgbData[startPos:endPos])

		// Determine option parameter (0 for first chunk, 2 for subsequent chunks)
		option := 0
		if chunkIndex > 0 {
			option = 2 // CONTINUE_SEND
		}

		payload := packetBuilder.PayloadDefault(TYPE_CAMERA, chunkData, rgbData, option, 0, 0, 56)

		// Send the chunk
		err := disp.SendData(payload)
		if err != nil {
			return fmt.Errorf("failed to send chunk %d: %v", chunkIndex+1, err)
		}
	}

	return nil
}

// rawSendPixels sends using the DIY pixel protocol (like Python code)
func (disp *display) rawSendPixels(rgbData []byte, width, height int) error {
	positions := make(map[color.RGBA][]Position)

	for y := 0; y < height; y++ {
		for x := 0; x < width; x++ {
			idx := (y*width + x) * 3
			rgb := color.RGBA{
				R: rgbData[idx],
				G: rgbData[idx+1],
				B: rgbData[idx+2],
				A: 255,
			}

			// Skip black pixels (off)
			if rgb.R == 0 && rgb.G == 0 && rgb.B == 0 {
				continue
			}

			// Group pixels by color for efficient transmission
			if positions[rgb] == nil {
				positions[rgb] = make([]Position, 0)
			}
			positions[rgb] = append(positions[rgb], Position{X: x, Y: y})
		}
	}

	for rgb, pos := range positions {
		err := disp.SendPixels(rgb, pos)
		if err != nil {
			return fmt.Errorf("failed to send pixels: %v", err)
		}
	}

	return nil
}
