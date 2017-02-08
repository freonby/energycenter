package by.qlab.energycenter.datamodel;

public class Fider {
	private long id;
	private String name;
	private EnergyMeter energyMeter;
	private CurrentTransformer currentTransformer;
	private VoltageTransformer voltageTransformer;

	public Fider() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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

}
