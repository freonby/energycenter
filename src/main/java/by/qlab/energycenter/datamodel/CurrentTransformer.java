package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CurrentTransformer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7323272196870017748L;
	private long currenttransformer_id;
	private String name;
	private Fider fider;
	private int KI;
	private List<TTType> listCurTransformerTypes = new ArrayList<TTType>();

	public CurrentTransformer() {
		super();
	}

	public long getCurrenttransformer_id() {
		return currenttransformer_id;
	}

	public void setCurrenttransformer_id(long currenttransformer_id) {
		this.currenttransformer_id = currenttransformer_id;
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

	public int getKI() {
		return KI;
	}

	public void setKI(int kI) {
		KI = kI;
	}

	public List<TTType> getListCurTransformerTypes() {
		return listCurTransformerTypes;
	}

	public void setListCurTransformerTypes(List<TTType> listCurTransformerTypes) {
		this.listCurTransformerTypes = listCurTransformerTypes;
	}

}
