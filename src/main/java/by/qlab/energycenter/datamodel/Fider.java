package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fider implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3304501375317136168L;
	private long fider_id;
	private String name;
	private SectionBus sectionBus;
	private List<EnergyMeter> listEnergyMeters = new ArrayList<EnergyMeter>();
	private List<CurrentTransformer> listCurrentTransformers = new ArrayList<CurrentTransformer>();
	private List<VoltageTransformer> listVoltageTransformers = new ArrayList<VoltageTransformer>();

	public Fider() {
		super();
	}

	public long getFider_id() {
		return fider_id;
	}

	public void setFider_id(long fider_id) {
		this.fider_id = fider_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SectionBus getSectionBus() {
		return sectionBus;
	}

	public void setSectionBus(SectionBus sectionBus) {
		this.sectionBus = sectionBus;
	}

	public List<EnergyMeter> getListEnergyMeters() {
		return listEnergyMeters;
	}

	public void setListEnergyMeters(List<EnergyMeter> listEnergyMeters) {
		this.listEnergyMeters = listEnergyMeters;
	}

	public List<CurrentTransformer> getListCurrentTransformers() {
		return listCurrentTransformers;
	}

	public void setListCurrentTransformers(List<CurrentTransformer> listCurrentTransformers) {
		this.listCurrentTransformers = listCurrentTransformers;
	}

	public List<VoltageTransformer> getListVoltageTransformers() {
		return listVoltageTransformers;
	}

	public void setListVoltageTransformers(List<VoltageTransformer> listVoltageTransformers) {
		this.listVoltageTransformers = listVoltageTransformers;
	}

}
