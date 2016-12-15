package by.qlab.energycenter.packets;

public class Refine {
	private Byte codeRefine;

	public Refine(int codeRefine) {
		super();
		this.codeRefine = new Byte((byte)codeRefine);
	}

	public Byte getCodeRefine() {
		return codeRefine;
	}

	public void setCodeRefine(Byte codeRefine) {
		this.codeRefine = codeRefine;
	}

}
