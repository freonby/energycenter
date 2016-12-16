package by.qlab.energycenter.model;

public class DeviceRequest {
	private byte netAddress;
	private String portName;

	public DeviceRequest(byte netAddress, String portName) {
		super();
		this.netAddress = netAddress;
		this.portName = portName;
	}

	public DeviceRequest() {
		super();
		this.netAddress = 1;
		this.portName = "";
	}

	public byte getNetAddress() {
		return netAddress;
	}

	public String getPortName() {
		return portName;
	}

	public void setNetAddress(byte netAddress) {
		this.netAddress = netAddress;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
}
