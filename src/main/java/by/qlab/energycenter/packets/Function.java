package by.qlab.energycenter.packets;

public class Function {
	private Byte codeFunc;


	public Function(int codeFunc) {
		super();
		this.codeFunc = new Byte((byte)codeFunc);

	}
	public Function(byte codeFunc) {
		super();
		this.codeFunc = new Byte(codeFunc);

	}
	public Byte getCodeFunc() {
		return codeFunc;
	}

	public void setCodeFunc(Byte codeFunc) {
		this.codeFunc = codeFunc;
	}

	

	

	@Override
	public String toString() {
		String s = "������� : " + this.getCodeFunc();
		return s;
	}

}
