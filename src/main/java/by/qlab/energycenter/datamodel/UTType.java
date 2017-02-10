package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class UTType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -815024927991520665L;
	private long uttype_id;
	private String type;
	private VoltageTransformer voltageTransformer;

	public UTType() {
		super();
	}

	public long getUttype_id() {
		return uttype_id;
	}

	public void setUttype_id(long uttype_id) {
		this.uttype_id = uttype_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VoltageTransformer getVoltageTransformer() {
		return voltageTransformer;
	}

	public void setVoltageTransformer(VoltageTransformer voltageTransformer) {
		this.voltageTransformer = voltageTransformer;
	}

}
