package ipxl

const (
	WRITE_CHAR_UUID  = "0000fa02-0000-1000-8000-00805f9b34fb"
	NOTIFY_CHAR_UUID = "0000fa03-0000-1000-8000-00805f9b34fb"
)

const (
	// Data types
	TYPE_CAMERA           = 0
	TYPE_VIDEO            = 1
	TYPE_IMAGE            = 2
	TYPE_GIF              = 3
	TYPE_TEXT             = 4
	TYPE_DIY_IMAGE        = 5
	TYPE_DIY_IMAGE_UNREDO = 6
	TYPE_TEM              = 7

	// Default values
	DEFAULT_LED_FRAME_SIZE = 1024
	DEFAULT_BRIGHTNESS     = 50
	UBYTE_MAX_VALUE        = 255

	DIY_IMAGE_FUN_QUIT_NOSAVE_KEEP_PREV   = 0
	DIY_IMAGE_FUN_ENTER_CLEAR_CUR_SHOW    = 1
	DIY_IMAGE_FUN_QUIT_STILL_CUR_SHOW     = 2
	DIY_IMAGE_FUN_ENTER_NO_CLEAR_CUR_SHOW = 3
)

// LED size mapping based on device type
var ledSizeMap = map[byte][2]int{
	0:  {64, 64},  // Type 0
	1:  {96, 16},  // Type 1
	2:  {32, 32},  // Type 2
	3:  {64, 16},  // Type 3
	4:  {32, 16},  // Type 4
	5:  {64, 20},  // Type 5
	6:  {128, 32}, // Type 6
	7:  {144, 16}, // Type 7
	8:  {192, 16}, // Type 8
	9:  {48, 24},  // Type 9
	10: {64, 32},  // Type 10
	11: {96, 32},  // Type 11
	12: {128, 32}, // Type 12
	13: {96, 32},  // Type 13
	14: {160, 32}, // Type 14
	15: {192, 32}, // Type 15
	16: {256, 32}, // Type 16
	17: {320, 32}, // Type 17
	18: {384, 32}, // Type 18
	19: {448, 32}, // Type 19
}

// Device type byte values
var deviceTypeMap = map[byte]byte{
	129: 2,  // -127 -> Type 2 (32x32)
	128: 0,  // -128 -> Type 0 (64x64)
	130: 4,  // -126 -> Type 4 (32x16)
	131: 3,  // -125 -> Type 3 (64x16)
	132: 1,  // -124 -> Type 1 (96x16)
	133: 5,  // -123 -> Type 5 (64x20)
	134: 6,  // -122 -> Type 6 (128x32)
	135: 7,  // -121 -> Type 7 (144x16)
	136: 8,  // -120 -> Type 8 (192x16)
	137: 9,  // -119 -> Type 9 (48x24)
	138: 10, // -118 -> Type 10 (64x32)
	139: 11, // -117 -> Type 11 (96x32)
	140: 12, // -116 -> Type 12 (128x32)
	141: 13, // -115 -> Type 13 (96x32)
	142: 14, // -114 -> Type 14 (160x32)
	143: 15, // -113 -> Type 15 (192x32)
	144: 16, // -112 -> Type 16 (256x32)
	145: 17, // -111 -> Type 17 (320x32)
	146: 18, // -110 -> Type 18 (384x32)
	147: 19, // -109 -> Type 19 (448x32)
}

var LEDConfigs = map[string]struct {
	Width       int
	Height      int
	AspectRatio float32
	Description string
}{
	"16x16":  {16, 16, 1.0, "Standard 16x16 matrix"},
	"32x8":   {32, 8, 4.0, "Wide 32x8 display"},
	"64x16":  {64, 16, 4.0, "Large 64x16 display"},
	"96x16":  {96, 16, 6.0, "Extra wide 96x16"},
	"64x32":  {64, 32, 2.0, "Large square-ish 64x32"},
	"128x16": {128, 16, 8.0, "Ultra wide banner"},
	"192x16": {192, 16, 12.0, "Super wide banner"},
	"80x16":  {80, 16, 5.0, "Medium wide display"},
	"48x16":  {48, 16, 3.0, "Small wide display"},
	"64x20":  {64, 20, 3.2, "Special ratio display"},
}
