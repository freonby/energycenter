package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class EnergyMeterType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1110801707650510750L;
	private long energymetertype_id;
	private String type;

	public EnergyMeterType() {

	}

	public long getEnergymetertype_id() {
		return energymetertype_id;
	}

	public void setEnergymetertype_id(long energymetertype_id) {
		this.energymetertype_id = energymetertype_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
