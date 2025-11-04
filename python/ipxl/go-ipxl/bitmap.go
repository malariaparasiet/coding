package ipxl

import (
	"errors"
	"image"
	"image/color"
	_ "image/jpeg" // Import for JPEG decoding support
	_ "image/png"  // Import for PNG decoding support
	"os"
)

// BitmapProcessor handles bitmap-to-RGB conversion for LED displays
type BitmapProcessor struct {
	Width  int
	Height int
}

// NewBitmapProcessor creates a new bitmap processor with specified dimensions
func NewBitmapProcessor(width, height int) *BitmapProcessor {
	return &BitmapProcessor{
		Width:  width,
		Height: height,
	}
}

// LoadImageFromFile loads an image from file path (supports PNG, JPEG)
func (bp *BitmapProcessor) LoadImageFromFile(filepath string) (image.Image, error) {
	file, err := os.Open(filepath)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	// Decode image (automatically detects format)
	img, _, err := image.Decode(file)
	if err != nil {
		return nil, err
	}

	return img, nil
}

// ResizeImage resizes an image to the specified width and height using nearest neighbor
func (bp *BitmapProcessor) ResizeImage(src image.Image, width, height int) image.Image {
	srcBounds := src.Bounds()
	srcWidth := srcBounds.Max.X
	srcHeight := srcBounds.Max.Y

	dst := image.NewRGBA(image.Rect(0, 0, width, height))

	// Simple nearest neighbor scaling
	for y := 0; y < height; y++ {
		for x := 0; x < width; x++ {
			// Map destination coordinates to source coordinates
			srcX := (x * srcWidth) / width
			srcY := (y * srcHeight) / height

			// Get pixel from source and set in destination
			c := src.At(srcX, srcY)
			dst.Set(x, y, c)
		}
	}

	return dst
}

// ImageToRGB converts an image to RGB byte array (similar to BGRUtils.bitmap2RGB)
// Returns byte array in format: [R, G, B, R, G, B, ...]
func (bp *BitmapProcessor) ImageToRGB(img image.Image) []byte {
	bounds := img.Bounds()
	width := bounds.Max.X
	height := bounds.Max.Y

	// Each pixel has 3 bytes (R, G, B)
	rgbData := make([]byte, width*height*3)

	index := 0
	for y := 0; y < height; y++ {
		for x := 0; x < width; x++ {
			// Get the color at this pixel
			c := img.At(x, y)
			r, g, b, _ := c.RGBA()

			// Convert from 16-bit to 8-bit values
			// RGBA() returns values in range 0-65535, we need 0-255
			rgbData[index] = byte(r >> 8)   // R
			rgbData[index+1] = byte(g >> 8) // G
			rgbData[index+2] = byte(b >> 8) // B

			index += 3
		}
	}

	return rgbData
}

// ProcessImageFile loads, resizes, and converts an image file to RGB byte array
func (bp *BitmapProcessor) ProcessImageFile(filepath string) ([]byte, error) {
	// Load image from file
	img, err := bp.LoadImageFromFile(filepath)
	if err != nil {
		return nil, err
	}

	// Resize to target dimensions
	resizedImg := bp.ResizeImage(img, bp.Width, bp.Height)

	// Convert to RGB byte array
	rgbData := bp.ImageToRGB(resizedImg)

	return rgbData, nil
}

// CreateSolidColorRGB creates RGB data for a solid color image
func (bp *BitmapProcessor) CreateSolidColorRGB(r, g, b byte) []byte {
	totalPixels := bp.Width * bp.Height
	rgbData := make([]byte, totalPixels*3)

	for i := 0; i < totalPixels; i++ {
		rgbData[i*3] = r   // R
		rgbData[i*3+1] = g // G
		rgbData[i*3+2] = b // B
	}

	return rgbData
}

// CreateFromColorArray creates RGB data from a color array (useful for patterns)
func (bp *BitmapProcessor) CreateFromColorArray(colors []color.RGBA) ([]byte, error) {
	expectedPixels := bp.Width * bp.Height
	if len(colors) != expectedPixels {
		return nil, errors.New("color array length must match width * height")
	}

	rgbData := make([]byte, expectedPixels*3)

	for i, c := range colors {
		rgbData[i*3] = c.R   // R
		rgbData[i*3+1] = c.G // G
		rgbData[i*3+2] = c.B // B
	}

	return rgbData, nil
}

// BuildCameraData prepares camera/image data for BLE transmission
func (sc *PacketBuilder) BuildCameraData(rgbData []byte) [][]byte {
	const CHUNK_SIZE = 12288 // 12KB chunks as seen in Java code

	// Calculate number of chunks needed
	totalChunks := len(rgbData) / CHUNK_SIZE
	if len(rgbData)%CHUNK_SIZE != 0 {
		totalChunks++
	}

	// Split data into chunks
	chunks := make([][]byte, 0, totalChunks)

	for i := 0; i < totalChunks; i++ {
		start := i * CHUNK_SIZE
		end := start + CHUNK_SIZE

		if end > len(rgbData) {
			end = len(rgbData)
		}

		// Create chunk
		chunk := make([]byte, end-start)
		copy(chunk, rgbData[start:end])
		chunks = append(chunks, chunk)
	}

	// Create payloads for each chunk
	payloads := make([][]byte, len(chunks))
	for i, chunk := range chunks {
		// Use TYPE_CAMERA with default parameters
		payloads[i] = sc.Payload(TYPE_CAMERA, chunk, rgbData, 0, DEFAULT_LED_FRAME_SIZE, DEFAULT_BRIGHTNESS)
	}

	return payloads
}
