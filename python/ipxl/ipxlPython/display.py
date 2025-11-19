from text_renderer import TextRenderer

class Display:
    def __init__(self):
        print("Display class initialized!")
    
    def sendPixels(rgb, positions):
        MAXPOSITIONPERPACKET = 120
        if not positions:
            return

        all_data = bytearray()
        for i in range(0, len(positions), MAXPOSITIONPERPACKET):
            chunk = positions[i:i + MAXPOSITIONPERPACKET]
            all_data.extend(Display.sendPixelChunk(rgb, chunk))
        return all_data

    def sendPixelChunk(rgb, positions):
        header = bytearray([0, 0, 5, 1, 0, rgb[0], rgb[1], rgb[2]])

        body = bytearray()
        for x, y in positions:
            body.extend([x, y])

        totalLen = len(header) + len(body)
        header[0] = totalLen

        data = header + body

        return data

    def sendText(text: str, rgb_color: tuple):
        renderer = TextRenderer()
        positions = renderer.text_to_pixels(text, rgb_color)
        return Display.sendPixels(rgb_color, positions)