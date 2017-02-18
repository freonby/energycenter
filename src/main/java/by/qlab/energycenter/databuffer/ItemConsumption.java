package by.qlab.energycenter.databuffer;

public class ItemConsumption {
	private String interval;
	private float value;
	private String color;

	public ItemConsumption(String interval, float value, String color) {
		super();
		this.interval = interval;
		this.value = value;
		this.color = color;
	}

	public ItemConsumption() {
		super();
		this.interval = "";
		this.value = 0f;
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

}
