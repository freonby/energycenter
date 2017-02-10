package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnergyMeter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4354052398892358720L;
	private long energymeter_id;
	private String number;
	private Fider fider;
	private List<EnergyMeterType> listTypes = new ArrayList<EnergyMeterType>();

	public EnergyMeter() {

	}

	public long getEnergymeter_id() {
		return energymeter_id;
	}

	public void setEnergymeter_id(long energymeter_id) {
		this.energymeter_id = energymeter_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Fider getFider() {
		return fider;
	}

	public void setFider(Fider fider) {
		this.fider = fider;
	}

	public List<EnergyMeterType> getListTypes() {
		return listTypes;
	}

	public void setListTypes(List<EnergyMeterType> listTypes) {
		this.listTypes = listTypes;
	}

}
