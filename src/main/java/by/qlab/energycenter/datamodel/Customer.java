package by.qlab.energycenter.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2508685783402372945L;
	private long customer_id;
	private String companyName;
	private String address;
	private String email;
	private String phone;
	private String unp;
	private String okpo;
	private String bankName;
	private String bankAdress;
	private String bankCode;
	private String bankAccount;
	private String contactPerson;
	private String comment;
	private List<TransformerStation> listStations = new ArrayList<TransformerStation>();

	public Customer() {

	}

	public Customer(String companyName, String email, String phone, String contactPerson) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.phone = phone;
		this.contactPerson = contactPerson;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnp() {
		return unp;
	}

	public void setUnp(String unp) {
		this.unp = unp;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAdress() {
		return bankAdress;
	}

	public void setBankAdress(String bankAdress) {
		this.bankAdress = bankAdress;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<TransformerStation> getListStations() {
		return listStations;
	}

	public void setListStations(List<TransformerStation> listStations) {
		this.listStations = listStations;
	}

	public Fider findFiderByName(String fiderName) {
		Fider find = null;
		List<TransformerStation> list = this.listStations;
		for (TransformerStation stList : list) {
			for (ElectricalBus ebList : stList.getListElectricalBusses()) {
				for (SectionBus sbList : ebList.getListSections()) {
					for (Fider fider : sbList.getListFiders()) {
						if (fider.getName().equals(fiderName)) {
							find = fider;
							break;
						}
					}
				}

			}

		}
		return find;

	}

	public List<Fider> allFiders() {
		List<Fider> find = new ArrayList<Fider>();
		List<TransformerStation> list = this.listStations;
		for (TransformerStation stList : list) {
			for (ElectricalBus ebList : stList.getListElectricalBusses()) {
				for (SectionBus sb : ebList.getListSections()) {
					if (sb.getListFiders() != null)
						find.addAll(sb.getListFiders());
				}

			}

		}
		return find;

	}

}
