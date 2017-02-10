package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class Voltage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9017829640938546499L;
	private long voltage_id;
	private String voltage;
	private ElectricalBus electricalBus;

	public Voltage() {
		super();
	}

	public long getVoltage_id() {
		return voltage_id;
	}

	public void setVoltage_id(long voltage_id) {
		this.voltage_id = voltage_id;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public ElectricalBus getElectricalBus() {
		return electricalBus;
	}

	public void setElectricalBus(ElectricalBus electricalBus) {
		this.electricalBus = electricalBus;
	}

}
