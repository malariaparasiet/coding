from PIL import Image, ImageDraw, ImageFont

class TextRenderer:
    def __init__(self):
        # Try to load a default font. If not found, use a generic one.
        try:
            self.font = ImageFont.truetype("arial.ttf", 8) # Assuming Arial font is available
        except IOError:
            self.font = ImageFont.load_default(12)
        print("TextRenderer initialized!")

    def text_to_pixels(self, text: str, rgb_color: tuple, max_width: int = 64, max_height: int = 16):

            # FIX 1: Use 'RGBA' for a transparent background and anti-aliasing
            img = Image.new('RGBA', (max_width, max_height), (0, 0, 0, 0)) 
            draw = ImageDraw.Draw(img)

            bbox = draw.textbbox((0, 0), text, font=self.font)
            text_width = bbox[2] - bbox[0]
            text_height = bbox[3] - bbox[1]

            x_margin = (max_width - text_width) // 2
            y_margin = (max_height - text_height) // 2

            # This centering logic from before is correct
            x = x_margin - bbox[0]
            y = y_margin - bbox[1]

            text = (" ".join(text))

            # Draw the text. PIL will automatically anti-alias on an RGBA image
            draw.text((x, y), text, font=self.font, fill=rgb_color)

            # 

            pixels = []
            for y_px in range(max_height):
                for x_px in range(max_width):
                    # FIX 2: Get all 4 channels (r, g, b, a)
                    r, g, b, a = img.getpixel((x_px, y_px))

                    # FIX 3: Check if the pixel is not fully transparent
                    if a > 0:
                        pixels.append((x_px, y_px))
            return pixels