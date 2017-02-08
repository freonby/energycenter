package by.qlab.energycenter.datamodel;

import java.util.ArrayList;

public class VoltageTransformer {
	private long id;
	private String name;
	private Fider fider;
	private int KU;
	private ArrayList<TransformerType> listTypes = new ArrayList<TransformerType>();

	public VoltageTransformer() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getKU() {
		return KU;
	}

	public ArrayList<TransformerType> getListTypes() {
		return listTypes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKU(int kU) {
		KU = kU;
	}

	public void setListTypes(ArrayList<TransformerType> listTypes) {
		this.listTypes = listTypes;
	}

	public Fider getFider() {
		return fider;
	}

	public void setFider(Fider fider) {
		this.fider = fider;
	}
}
