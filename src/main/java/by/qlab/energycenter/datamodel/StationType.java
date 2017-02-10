package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class StationType implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -2635004433952467941L;
	private long stationtype_id;
	private String typeStation;
	private TransformerStation transformerStation;

	public StationType() {
		super();
	}

	public long getStationtype_id() {
		return stationtype_id;
	}

	public void setStationtype_id(long stationtype_id) {
		this.stationtype_id = stationtype_id;
	}

	public String getTypeStation() {
		return typeStation;
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
