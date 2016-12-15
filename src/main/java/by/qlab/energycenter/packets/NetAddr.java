package by.qlab.energycenter.packets;

public class NetAddr {
	private Byte netAddr;

	public NetAddr(byte netAddr) {
		super();
		this.netAddr = new Byte(netAddr);
	}
	public NetAddr(int netAddr) {
		super();
		this.netAddr = new Byte((byte)netAddr);
	}

	public Byte getNetAddr() {
		return netAddr;
	}

	public void setNetAddr(Byte netAddr) {
		this.netAddr = netAddr;
	}
	public void setNetAddr(byte netAddr) {
		this.netAddr = netAddr;
	}
	public void setNetAddr(Integer netAddr) {
		this.netAddr = netAddr.byteValue();
	}

	public boolean isValid() {
		if (netAddr >= (byte) 255 || netAddr < 0) {
			return false;
		}
		return true;

	}

	@Override
	public String toString() {
		String s = "������� ����� : " + this.getNetAddr();
		;
		return s;
	}
}
