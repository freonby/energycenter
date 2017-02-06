package by.qlab.energycenter.drivers;

public class PortSet {
	private int baudrate;
	private int databits;
	private int stopbits;
	private int parity;

	public PortSet(int baudrate, int databits, int stopbits, int parity) {
		super();
		this.baudrate = baudrate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity;
	}

	public PortSet() {
		super();
		this.baudrate = 9600;
		this.databits = 8;
		this.stopbits = 1;
		this.parity = 0;
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}

	public int getDatabits() {
		return databits;
	}

	public void setDatabits(int databits) {
		this.databits = databits;
	}

	public int getStopbits() {
		return stopbits;
	}

	public void setStopbits(int stopbits) {
		this.stopbits = stopbits;
	}

	public int getParity() {
		return parity;
	}

	public void setParity(int parity) {
		this.parity = parity;
	}

	// public void setParity(String parity) {
	// switch (parity) {
	// case "even":
	// this.parity = 2;
	// break;
	// case "mark":
	// this.parity = 3;
	// break;
	// case "odd":
	// this.parity = 1;
	// break;
	// case "none":
	// this.parity = 0;
	// break;
	// default:
	// this.parity = 0;
	// }
	//
	// }

}
