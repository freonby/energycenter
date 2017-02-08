package by.qlab.energycenter.datamodel;

import java.util.ArrayList;

public class ElectricalBus {
	private long id;
	private TransformerStation transformerStation;
	private ArrayList<Voltage> listVoltages = new ArrayList<Voltage>();
	private ArrayList<SectionBus> listSections = new ArrayList<SectionBus>();

	public ElectricalBus() {
		super();
	}

	public long getId() {
		return id;
	}

	public ArrayList<Voltage> getListVoltages() {
		return listVoltages;
	}

	public ArrayList<SectionBus> getListSections() {
		return listSections;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setListVoltages(ArrayList<Voltage> listVoltages) {
		this.listVoltages = listVoltages;
	}

	public void setListSections(ArrayList<SectionBus> listSections) {
		this.listSections = listSections;
	}

	public TransformerStation getTransformerStation() {
		return transformerStation;
	}

	public void setTransformerStation(TransformerStation transformerStation) {
		this.transformerStation = transformerStation;
	}
}
