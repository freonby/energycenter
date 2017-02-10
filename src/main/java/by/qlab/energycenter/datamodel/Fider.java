package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Fider implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3304501375317136168L;
	private long fider_id;
	private String name;
	private SectionBus sectionBus;
	private EnergyMeter energyMeter = new EnergyMeter();
	private CurrentTransformer currentTransformer = new CurrentTransformer();
	private VoltageTransformer voltageTransformer = new VoltageTransformer();

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

	public EnergyMeter getEnergyMeter() {
		return energyMeter;
	}

	public CurrentTransformer getCurrentTransformer() {
		return currentTransformer;
	}

	public VoltageTransformer getVoltageTransformer() {
		return voltageTransformer;
	}

	public void setEnergyMeter(EnergyMeter energyMeter) {
		this.energyMeter = energyMeter;
	}

	public void setCurrentTransformer(CurrentTransformer currentTransformer) {
		this.currentTransformer = currentTransformer;
	}

	public void setVoltageTransformer(VoltageTransformer voltageTransformer) {
		this.voltageTransformer = voltageTransformer;
	}

	public String toJson() {

		ArrayList<String> list = new ArrayList<String>();
		list.add(name);
		list.add(this.energyMeter.getNumber());
		list.add(this.energyMeter.getEnergymeterType());
		list.add(this.currentTransformer.getName());
		list.add(Integer.toString(this.currentTransformer.getKI()));
		list.add(this.voltageTransformer.getName());
		list.add(Integer.toString(this.voltageTransformer.getKU()));
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;

	}
}
