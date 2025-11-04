package ipxl

import (
	"encoding/binary"
	"hash/crc32"
)

// int2byte converts an integer to 4-byte little-endian byte array
func int2byte(value int) []byte {
	bytes := make([]byte, 4)
	binary.LittleEndian.PutUint32(bytes, uint32(value))
	return bytes
}

// coerceIn constrains a value within the given range
func coerceIn(value, min, max int) int {
	if value < min {
		return min
	}
	if value > max {
		return max
	}
	return value
}

// appendByteArrays concatenates multiple byte arrays (equivalent to ArraysKt.plus)
func appendByteArrays(arrays ...[]byte) []byte {
	totalLen := 0
	for _, arr := range arrays {
		totalLen += len(arr)
	}

	result := make([]byte, 0, totalLen)
	for _, arr := range arrays {
		result = append(result, arr...)
	}
	return result
}

// crc32Checksum calculates CRC32 for the given data
func crc32Checksum(data []byte) uint32 {
	return crc32.ChecksumIEEE(data)
}

// hsvToRgb converts HSV color to RGB
func hsvToRgb(h, s, v float64) (uint8, uint8, uint8) {
	h = h / 60.0
	i := int(h)
	f := h - float64(i)
	p := v * (1.0 - s)
	q := v * (1.0 - s*f)
	t := v * (1.0 - s*(1.0-f))

	var r, g, b float64
	switch i % 6 {
	case 0:
		r, g, b = v, t, p
	case 1:
		r, g, b = q, v, p
	case 2:
		r, g, b = p, v, t
	case 3:
		r, g, b = p, q, v
	case 4:
		r, g, b = t, p, v
	case 5:
		r, g, b = v, p, q
	}

	return uint8(r * 255), uint8(g * 255), uint8(b * 255)
}
