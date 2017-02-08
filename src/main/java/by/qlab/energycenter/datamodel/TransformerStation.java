package by.qlab.energycenter.datamodel;

import java.util.ArrayList;

public class TransformerStation {
	private long id;
	private String name;
	private Customer customer;
	private ArrayList<StationType> listTypes = new ArrayList<StationType>();
	private ArrayList<Voltage> listVoltages = new ArrayList<Voltage>();
	private ArrayList<ElectricalBus> listElectricalBusses = new ArrayList<ElectricalBus>();

	public TransformerStation() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<StationType> getListTypes() {
		return listTypes;
	}

	public ArrayList<Voltage> getListVoltages() {
		return listVoltages;
	}

	public ArrayList<ElectricalBus> getListElectricalBusses() {
		return listElectricalBusses;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setListTypes(ArrayList<StationType> listTypes) {
		this.listTypes = listTypes;
	}

	public void setListVoltages(ArrayList<Voltage> listVoltages) {
		this.listVoltages = listVoltages;
	}

	public void setListElectricalBusses(ArrayList<ElectricalBus> listElectricalBusses) {
		this.listElectricalBusses = listElectricalBusses;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
