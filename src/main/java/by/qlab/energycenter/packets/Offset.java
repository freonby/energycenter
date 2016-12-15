package by.qlab.energycenter.packets;

public class Offset {
	private Byte codeOffset;

	public Offset(int codeOffset) {
		super();
		this.codeOffset = new Byte((byte)codeOffset);
	}

	public Byte getCodeOffset() {
		return codeOffset;
	}

	public void setCodeOffset(Byte codeOffset) {
		this.codeOffset = codeOffset;
	}
}
