package by.qlab.energycenter.model;

public class Item30 {
	private String interval;
	private double value;
	private String color;

	public Item30(String interval, double value, String color) {
		super();
		this.interval = interval;
		this.value = value;
		this.color = color;
	}

	public Item30() {
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
