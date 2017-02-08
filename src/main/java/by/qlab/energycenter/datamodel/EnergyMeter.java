package by.qlab.energycenter.datamodel;

import java.util.ArrayList;

public class EnergyMeter {
	private long id;
	private long number;
	private Fider fider;
	private ArrayList<EnergyMeterType> listTypes = new ArrayList<EnergyMeterType>();

	public EnergyMeter() {

	}

	public long getId() {
		return id;
	}

	public long getNumber() {
		return number;
	}

	public Fider getFider() {
		return fider;
	}

	public ArrayList<EnergyMeterType> getListTypes() {
		return listTypes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public void setFider(Fider fider) {
		this.fider = fider;
	}

	public void setListTypes(ArrayList<EnergyMeterType> listTypes) {
		this.listTypes = listTypes;
	}

}
