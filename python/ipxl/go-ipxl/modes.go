package ipxl

// SwitchToDiyFunMode enables DIY mode on the LED display
func (disp *display) SwitchToDiyFunMode(mode int) error {
	data := []byte{5, 0, 4, 1, byte(mode)}
	err := disp.SendData(data)
	if err != nil {
		return err
	}
	return nil
}
