package ipxl

// PacketBuilder represents the core sending functionality
type PacketBuilder struct{}

// NewPacketBuilder creates a new SendCore instance
func NewPacketBuilder() *PacketBuilder {
	return &PacketBuilder{}
}

// shouldCrc determines if CRC should be calculated for the given type
func (sc *PacketBuilder) shouldCrc(dataType int) bool {
	return dataType == TYPE_IMAGE || dataType == TYPE_VIDEO || dataType == TYPE_GIF || dataType == TYPE_TEXT || dataType == TYPE_TEM
}

// getDataType returns the data type bytes for the given type
func (sc *PacketBuilder) getDataType(dataType int) []byte {
	switch dataType {
	case TYPE_CAMERA, TYPE_DIY_IMAGE_UNREDO:
		return []byte{0, 0}
	case TYPE_VIDEO:
		return []byte{1, 0}
	case TYPE_IMAGE:
		return []byte{2, 0}
	case TYPE_GIF:
		return []byte{3, 0}
	case TYPE_TEXT:
		return []byte{0, 1}
	case TYPE_DIY_IMAGE:
		return []byte{5, 1}
	case TYPE_TEM:
		return []byte{4, 0}
	default:
		return []byte{0, 0}
	}
}

// changeLight modifies the brightness of the data
func (sc *PacketBuilder) changeLight(bright int, data []byte) {
	length := len(data)
	for i := 0; i < length; i++ {
		// Convert signed byte to unsigned, apply brightness, then back to signed
		unsignedByte := int(data[i]) & UBYTE_MAX_VALUE
		newValue := coerceIn((unsignedByte*bright)/100, 0, 255)
		data[i] = byte(newValue)
	}
}

// PayloadDefault provides default parameter handling for Payload function
// This is equivalent to the payload$default method in Java
func (sc *PacketBuilder) PayloadDefault(dataType int, data []byte, totalData []byte, option int, totalLength int, bright int, mask int) []byte {
	// Apply default values based on mask (similar to Java's default parameter handling)
	// mask=56 means bits 3,4,5 are set: totalData=[], totalLength=DEFAULT_LED_FRAME_SIZE, bright=100
	if (mask & 4) != 0 { // bit 2
		totalData = []byte{}
	}
	if (mask & 8) != 0 { // bit 3
		option = 0
	}
	if (mask & 16) != 0 { // bit 4
		totalLength = DEFAULT_LED_FRAME_SIZE
	}
	if (mask & 32) != 0 { // bit 5
		bright = DEFAULT_BRIGHTNESS
	}

	return sc.Payload(dataType, data, totalData, option, totalLength, bright)
}

// Payload creates a payload packet for the given parameters
// This is the direct translation of the payload method in Java
func (sc *PacketBuilder) Payload(dataType int, data []byte, totalData []byte, option int, totalLength int, bright int) []byte {
	// Determine header length based on type
	headerLength := 9
	switch {
	case dataType == TYPE_TEXT:
		headerLength = 10
	case dataType == TYPE_DIY_IMAGE:
		headerLength = 5
	}

	// Get data type bytes
	dataTypeBytes := sc.getDataType(dataType)

	// Create header array
	header := make([]byte, headerLength)

	// Check if CRC is needed
	shouldCrc := sc.shouldCrc(dataType)

	// Calculate total packet length
	length := len(data) + headerLength
	if shouldCrc {
		length += 5 // CRC length
	}

	// Fill header
	header[0] = byte(length & 255)
	header[1] = byte((length >> 8) & 255)
	header[2] = dataTypeBytes[0]
	header[3] = dataTypeBytes[1]
	header[4] = byte(option)

	// Add frame length for most types (except TYPE_DIY_IMAGE)
	if dataType != TYPE_DIY_IMAGE {
		frameLength := int2byte(totalLength)
		copy(header[5:], frameLength)
	}

	// Apply brightness if not 100%
	if bright != 100 {
		// Make a copy of data to avoid modifying the original
		dataCopy := make([]byte, len(data))
		copy(dataCopy, data)
		sc.changeLight(bright, dataCopy)
		data = dataCopy
	}

	// Handle CRC if needed
	if shouldCrc {
		crcBytes := make([]byte, 5)

		var crcValue uint32
		if dataType == TYPE_CAMERA || dataType == TYPE_VIDEO || dataType == TYPE_GIF {
			crcValue = crc32Checksum(totalData)
		} else {
			crcValue = crc32Checksum(data)
		}

		crcByteArray := int2byte(int(crcValue))
		copy(crcBytes[0:4], crcByteArray)

		if dataType == TYPE_GIF {
			crcBytes[4] = 2
		}

		return appendByteArrays(header, crcBytes, data)
	}

	return appendByteArrays(header, data)
}
