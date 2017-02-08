package by.qlab.energycenter.datamodel;

public class StationType {
	private long id;
	private String typeStation;
	private TransformerStation transformerStation;

	public StationType() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getTypeStation() {
		return typeStation;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTypeStation(String typeStation) {
		this.typeStation = typeStation;
	}

	public TransformerStation getTransformerStation() {
		return transformerStation;
	}

	public void setTransformerStation(TransformerStation transformerStation) {
		this.transformerStation = transformerStation;
	}
}
