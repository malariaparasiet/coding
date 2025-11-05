import asyncio
from bleak import BleakClient
import PIL
from PIL import Image, ImageColor

WRITE_CHAR_UUID = "0000fa02-0000-1000-8000-00805f9b34fb"

def image_to_rgb(image_path: str, size=(16, 16)) -> bytes:
    """Convert an image to raw RGB bytes (8-bit per channel)."""
    with Image.open(image_path) as im:
        # Ensure it's in RGB mode
        im = im.convert("RGB")
        # Resize the image
        im = im.resize(size)
        width, height = im.size

        # Get all pixel data (each pixel is a tuple of (R, G, B))
        pixels = list(im.getdata())

        # Flatten to raw bytes
        rgb_data = bytearray()
        for r, g, b in pixels:
            rgb_data.extend([r, g, b])

    return bytes(rgb_data)

def sendPixels(rgb: tuple, position: list)

class IPixelController:
    def __init__(self, address: str):
        self.address = address
        self.client = BleakClient(address)

    async def connect(self):
        print(f"🔗 Connecting to {self.address}...")
        await self.client.connect()
        print("✅ Connected!")

    async def disconnect(self):
        await self.client.disconnect()
        print("🔌 Disconnected.")

    async def send_led_on_off(self, on_off: int):
        """Turns the LED display on (1) or off (0)."""
        if on_off not in (0, 1):
            raise ValueError("on_off must be 0 or 1")

        packet = bytearray([0x05, 0x00, 0x07, 0x01, on_off])
        await self.client.write_gatt_char(WRITE_CHAR_UUID, packet, response=False)
        print(f"💡 LED {'ON' if on_off else 'OFF'} | Sent: {packet.hex()}")

    async def send_clock_mode(self, mode: int, timeScale: bool, showDate: bool):
        bArr = bytearray([0x11, 0x00, 0x06, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00])
        
        bArr[4] = mode
        
        if timeScale:
            bArr[5] = 0
        else:
            bArr[5] = 1
        if showDate:
            bArr[6] = 1
        else:
            bArr[6] = 0

        year = 25
        month = 11
        day = 2
        weekOfDate = 42
        bArr[7] = year;
        bArr[8] = month;
        bArr[9] = day;
        bArr[10] = weekOfDate;

        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

    async def delete_all_data(self):
        bArr = bytearray([0x04, 0x00, 0x03, 0x00])
        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

    async def set_upside_down(self, isDown: int):
        bArr = bytearray([0x05, 0x00, 0x06, 0x00, 0x00])
        i = 180
        bArr[4] = i
        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

    async def sendSportData(self, mode: int, speed: int, decimal: int):
        bArr = bytearray([0x07, 0x00, 0x06, 0x00, 0x00, 0x00, 0x00])
        bArr[4] = mode
        bArr[5] = speed
        bArr[6] = decimal
        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

    async def setDiyFunMode(self, mode: int):
        bArr = bytearray([0x05, 0x00, 0x04, 0x01, 0x00])
        bArr[4] = mode
        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

    async def writeImageToSign(self):
        image_path = input("Path to image: ").strip()
        rgb_data = image_to_rgb(image_path, size=(16, 16))
        if len(rgb_data) != (16*16)*3:
            print("Something went wrong :(")
        
                    
        

async def main():
    mac = input("Enter device MAC address: ").strip()
    device = IPixelController(mac)
    await device.connect()

    # Define commands here for easy expansion
    commands = {
        "1": ("Turn LED ON", lambda: device.send_led_on_off(1)),
        "2": ("Turn LED OFF", lambda: device.send_led_on_off(0)),
        "3": ("Send clock mode", lambda: device.send_clock_mode(5, 0, 0)),
        "4": ("Delete all data (reset)", lambda: device.delete_all_data()),
        "5": ("Set upside down < not working", lambda: device.set_upside_down(1)),
        "6": ("Send sport data < not working", lambda: device.sendSportData(4, 40, 1)),
        "7": ("Set diy fun mode < possibly needed for text", lambda: device.setDiyFunMode(1)),
        "8": ("Send image", lambda: device.writeImageToSign()),
        "q": ("Quit", None),
    }

    try:
        while True:
            print("\n=== iPixel Menu ===")
            for key, (desc, _) in commands.items():
                print(f"  {key}. {desc}")

            choice = input("Select an option: ").strip().lower()
            if choice == "q":
                break

            action = commands.get(choice)
            if action:
                _, func = action
                await func()
            else:
                print("❌ Invalid selection.")
    finally:
        await device.disconnect()

if __name__ == "__main__":
    asyncio.run(main())
