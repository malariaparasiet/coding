import asyncio, math, datetime

class BaseSend:
    def __init__(self):
        print("BaseSend class initialized")
    
    def sendRhytm(level: int, mode: int):
        bArr = bytearray([0x06, 0x00, 0x00, 0x02, 0x00, 0x00])
        bArr[4] = level
        bArr[5] = mode

        return bArr
    
    def sendRhytmChart(mode: int, data: bytearray):
        bArr = bytearray(11)
        length = len(data)
        i = 0
        i2 = 0
        while True:
            i3 = i
            if i >= length:
                break
            i4 = i2 + 1
            i5 = ((data[i] & 255)*15)/255
            absolute = abs(i5)
            if (1 <= absolute and absolute < 16):
                i3 = i5
            elif absolute != 0:
                i3 = 15
            bArr[i2] = i3
            i+=1
            i2 = i4
        plus = bytearray([56, 0, 1, 2, mode]) + bArr

        return plus
    
    def deleteAllData():
        bArr = bytearray([4, 0, 3, 0])
        return bArr

    def setLedLight(light: int):
        bArr = bytearray([5, 0, 4, 0, 50])
        backLight = input("What led light level do you want[int<=256]: ")
        bArr[4] = int(backLight)
        
        return bArr
    
    def setUpsideDown(isDown: int):
        bArr = bytearray([5, 0, 6, 0, 0])
        bArr[4] = isDown

        return bArr
    
    def sendSportData(mode: int, speed: int, decimal: int):
        bArr = bytearray([7, 0, 6, 0, 0, 0, 0])
        bArr[4] = mode
        bArr[5] = speed
        bArr[6] = decimal

        return bArr

    def sendClockMode(mode: int, timeScale: bool, showDate: bool):
        bArr = bytearray([11, 0, 6, 1, 1, 1, 0, 0, 0, 0, 0])
        bArr[4] = mode
        if timeScale:
            bArr[5] = 0
        else:
            bArr[5] = 1
        if showDate:
            bArr[6] = 1
        else:
            bArr[6] = 0
        
        year = datetime.now().year - 2000
        month = datetime.now().month
        day = datetime.now().day
        weekOfDate = datetime.now().date().isocalendar().week
        bArr[7] = year;
        bArr[8] = month;
        bArr[9] = day;
        bArr[10] = weekOfDate;

        return bArr
    
    def sendLedOnOff(onOff: int):
        bArr = bytearray([5, 0, 7, 1, 1])
        bArr[4] = onOff

        return bArr
    
    def setDiyFunMode(mode: int):
        bArr = bytearray([5, 0, 4, 1, 0])
        bArr[4] = mode

        return bArr