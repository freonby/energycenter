package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ElectricalBus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4960230858927305820L;
	private long electricalbus_id;
	private TransformerStation transformerStation;
	private String voltage;
	private List<SectionBus> listSections = new ArrayList<SectionBus>();

	public ElectricalBus() {
		super();
	}

	public long getElectricalbus_id() {
		return electricalbus_id;
	}

	public void setElectricalbus_id(long electricalbus_id) {
		this.electricalbus_id = electricalbus_id;
	}

	public TransformerStation getTransformerStation() {
		return transformerStation;
	}

	public void setTransformerStation(TransformerStation transformerStation) {
		this.transformerStation = transformerStation;
	}

	public List<SectionBus> getListSections() {
		return listSections;
	}

	public void setListSections(List<SectionBus> listSections) {
		this.listSections = listSections;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

}
