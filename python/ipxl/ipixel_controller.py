import asyncio
from bleak import BleakClient

WRITE_CHAR_UUID = "0000fa02-0000-1000-8000-00805f9b34fb"

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
        i = 0
        bArr[4] = i
        await self.client.write_gatt_char(WRITE_CHAR_UUID, bArr, response=False)

async def main():
    mac = input("Enter device MAC address: ").strip()
    device = IPixelController(mac)
    await device.connect()

    # Define commands here for easy expansion
    commands = {
        "1": ("Turn LED ON", lambda: device.send_led_on_off(1)),
        "2": ("Turn LED OFF", lambda: device.send_led_on_off(0)),
        "3": ("Send clock mode", lambda: device.send_clock_mode(8, 1, 1)),
        "4": ("Delete all data", lambda: device.delete_all_data()),
        "5": ("Set upside down", lambda: device.set_upside_down(1)),
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
