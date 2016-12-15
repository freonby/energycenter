package by.qlab.energycenter.packets;

public class Parameter {
	private Byte codeParam;
	private String name;
	private String version;

	public Parameter(int codeParam,String name,String version) {
		super();
		this.codeParam = new Byte((byte)codeParam);
		this.name = name;
		this.version=version;
	}

	public String getName() {
		return name;
	}

	public Byte getParameter() {
		return codeParam;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParameter(Byte parameter) {
		this.codeParam = parameter;
	}

	@Override
	public String toString() {
		String s = "��������: " + this.getName();
		return s;
	}

	public Byte getCodeParam() {
		return codeParam;
	}

	public String getVersion() {
		return version;
	}

	public void setCodeParam(Byte codeParam) {
		this.codeParam = codeParam;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
