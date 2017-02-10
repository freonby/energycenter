package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class TTType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9031873107577881831L;
	private long tttype_id;
	private String type;
	private CurrentTransformer currentTransformer;

	public TTType() {
		super();
	}

	public long getTttype_id() {
		return tttype_id;
	}

	public void setTttype_id(long tttype_id) {
		this.tttype_id = tttype_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CurrentTransformer getCurrentTransformer() {
		return currentTransformer;
	}

	public void setCurrentTransformer(CurrentTransformer currentTransformer) {
		this.currentTransformer = currentTransformer;
	}

}
