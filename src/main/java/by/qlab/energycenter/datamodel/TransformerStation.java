package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransformerStation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8395474580992647107L;
	private long transformerstation_id;
	private String name;
	private Customer customer;
	private List<StationType> listTypes = new ArrayList<StationType>();
	private List<ElectricalBus> listElectricalBusses = new ArrayList<ElectricalBus>();

	public TransformerStation() {
		super();
	}

	public long getTransformerstation_id() {
		return transformerstation_id;
	}

	public void setTransformerstation_id(long transformerstation_id) {
		this.transformerstation_id = transformerstation_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<StationType> getListTypes() {
		return listTypes;
	}

	public void setListTypes(List<StationType> listTypes) {
		this.listTypes = listTypes;
	}

	public List<ElectricalBus> getListElectricalBusses() {
		return listElectricalBusses;
	}

	public void setListElectricalBusses(List<ElectricalBus> listElectricalBusses) {
		this.listElectricalBusses = listElectricalBusses;
	}

}
