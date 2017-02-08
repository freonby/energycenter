package by.qlab.energycenter.datamodel;

import java.util.ArrayList;

public class SectionBus {
	private long id;
	private String name;
	private ElectricalBus electricalBus;
	private ArrayList<Fider> listFiders = new ArrayList<Fider>();

	public SectionBus() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Fider> getListFiders() {
		return listFiders;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setListFiders(ArrayList<Fider> listFiders) {
		this.listFiders = listFiders;
	}

	public ElectricalBus getElectricalBus() {
		return electricalBus;
	}

	public void setElectricalBus(ElectricalBus electricalBus) {
		this.electricalBus = electricalBus;
	}

}
