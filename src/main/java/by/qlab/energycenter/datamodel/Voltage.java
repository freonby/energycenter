package by.qlab.energycenter.datamodel;

public class Voltage {
	private long id;
	private String voltage;
	private TransformerStation transformerStation;
	private ElectricalBus electricalBus;

	public Voltage() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public TransformerStation getTransformerStation() {
		return transformerStation;
	}

	public void setTransformerStation(TransformerStation transformerStation) {
		this.transformerStation = transformerStation;
	}

	public ElectricalBus getElectricalBus() {
		return electricalBus;
	}

	public void setElectricalBus(ElectricalBus electricalBus) {
		this.electricalBus = electricalBus;
	}

}
