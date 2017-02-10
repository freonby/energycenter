package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SectionBus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7175635737733748192L;
	private long sectionbus_id;
	private String name;
	private ElectricalBus electricalBus;
	private List<Fider> listFiders = new ArrayList<Fider>();

	public SectionBus() {
		super();
	}

	public long getSectionbus_id() {
		return sectionbus_id;
	}

	public void setSectionbus_id(long sectionbus_id) {
		this.sectionbus_id = sectionbus_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElectricalBus getElectricalBus() {
		return electricalBus;
	}

	public void setElectricalBus(ElectricalBus electricalBus) {
		this.electricalBus = electricalBus;
	}

	public List<Fider> getListFiders() {
		return listFiders;
	}

	public void setListFiders(List<Fider> listFiders) {
		this.listFiders = listFiders;
	}

}
