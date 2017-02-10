package by.qlab.energycenter.datamodel;

import java.io.Serializable;

public class VoltageTransformer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6404428611684102542L;
	private long voltagetransformer_id;
	private String name;
	private Fider fider;
	private int KU;

	public VoltageTransformer() {
		super();
	}

	public long getVoltagetransformer_id() {
		return voltagetransformer_id;
	}

	public void setVoltagetransformer_id(long voltagetransformer_id) {
		this.voltagetransformer_id = voltagetransformer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Fider getFider() {
		return fider;
	}

	public void setFider(Fider fider) {
		this.fider = fider;
	}

	public int getKU() {
		return KU;
	}

	public void setKU(int kU) {
		KU = kU;
	}

}
