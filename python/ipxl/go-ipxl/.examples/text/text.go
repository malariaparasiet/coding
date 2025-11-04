package main

import (
	"image"
	"image/color"
	"image/draw"
	"os"

	"github.com/golang/freetype"
	"github.com/golang/freetype/truetype"
	"golang.org/x/image/font"
)

// StringToImage converts a string to a pixel image using arial.ttf font
func StringToImage(width, height int, text string) (image.Image, error) {
	// Create a new RGBA image
	img := image.NewRGBA(image.Rect(0, 0, width, height))

	// Fill background with black
	draw.Draw(img, img.Bounds(), &image.Uniform{color.RGBA{0, 0, 0, 255}}, image.Point{}, draw.Src)

	// Load the arial.ttf font
	fontBytes, err := os.ReadFile("arial.ttf")
	if err != nil {
		return nil, err
	}

	// Parse the font
	f, err := truetype.Parse(fontBytes)
	if err != nil {
		return nil, err
	}

	// Create a freetype context
	c := freetype.NewContext()
	c.SetDPI(72)
	c.SetFont(f)
	c.SetFontSize(12) // Adjust font size for 16 pixel height
	c.SetClip(img.Bounds())
	c.SetDst(img)
	c.SetSrc(&image.Uniform{color.RGBA{255, 255, 255, 255}}) // White text

	// Calculate text position to center it
	face := truetype.NewFace(f, &truetype.Options{
		Size: 12,
		DPI:  72,
	})

	// Measure text width
	textWidth := font.MeasureString(face, text)
	textWidthPx := int(textWidth >> 6) // Convert from fixed.Int26_6 to pixels

	// Center the text horizontally and vertically
	x := (width - textWidthPx) / 2
	y := height/2 + 4 // Adjust vertical position for better centering

	// Draw the text
	pt := freetype.Pt(x, y)
	_, err = c.DrawString(text, pt)
	if err != nil {
		return nil, err
	}

	return img, nil
}
