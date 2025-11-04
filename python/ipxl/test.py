#!/usr/bin/env python3
import asyncio
from bleak import BleakClient, BleakScanner

async def explore_device(address):
    print(f"Connecting to {address}...")
    async with BleakClient(address) as client:
        print("Connected!")

        print("\n=== GATT Services and Characteristics ===")
        for service in client.services:
            print(f"\nService: {service.uuid}")
            for char in service.characteristics:
                props = ",".join(char.properties)
                print(f"  └─ Char: {char.uuid}  [{props}]")
                for desc in char.descriptors:
                    print(f"      └─ Desc: {desc.uuid}")

        print("\n--- End of GATT table ---")

async def main():
    print("Scanning for BLE devices (10s)...")
    devices = await BleakScanner.discover(timeout=10)
    for i, d in enumerate(devices):
        print(f"{i+1}. {d.name or 'Unknown'} [{d.address}]")
    idx = int(input("\nSelect device number: ")) - 1
    address = devices[idx].address
    await explore_device(address)

if __name__ == "__main__":
    asyncio.run(main())
