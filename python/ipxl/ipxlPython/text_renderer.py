from PIL import Image, ImageDraw, ImageFont

class TextRenderer:
    def __init__(self):
        # Try to load a default font. If not found, use a generic one.
        try:
            self.font = ImageFont.truetype("arial.ttf", 8) # Assuming Arial font is available
        except IOError:
            self.font = ImageFont.load_default()
        print("TextRenderer initialized!")

    def text_to_pixels(self, text: str, rgb_color: tuple, max_width: int = 16, max_height: int = 16):
        # Create a blank image with a transparent background
        img = Image.new('RGB', (max_width, max_height), (0, 0, 0))
        draw = ImageDraw.Draw(img)

        # Get text size
        bbox = draw.textbbox((0, 0), text, font=self.font)
        text_width = bbox[2] - bbox[0]
        text_height = bbox[3] - bbox[1]

        # Calculate position to center the text
        x = (max_width - text_width) // 2
        y = (max_height - text_height) // 2

        # Draw the text onto the image
        draw.text((x, y), text, font=self.font, fill=rgb_color)

        pixels = []
        for y in range(max_height):
            for x in range(max_width):
                r, g, b = img.getpixel((x, y))
                # Only add pixels that are not black (background)
                if (r, g, b) != (0, 0, 0):
                    pixels.append((x, y))
        return pixels
