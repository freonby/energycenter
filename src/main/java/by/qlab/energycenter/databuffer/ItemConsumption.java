package by.qlab.energycenter.databuffer;

public class ItemConsumption {
	private String interval;
	private float value;
	private String color;
	private float readout;

	public ItemConsumption(String interval, float value, String color, double readout) {
		super();
		this.interval = interval;
		this.value = value;
		this.color = color;
		this.readout = (float) readout;
	}

	public ItemConsumption() {
		super();
		this.interval = "";
		this.value = 0f;
		this.readout = 0f;
		this.color = "";
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getReadout() {
		return readout;
	}

	public void setReadout(float readout) {
		this.readout = readout;
	}

}
