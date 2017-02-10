package by.qlab.energycenter.dao;

import by.qlab.energycenter.datamodel.Customer;
import by.qlab.energycenter.datamodel.User;
import by.qlab.energycenter.model.Interval30;

public interface DAO {
	public Interval30 getInterval30Day(long device_id);

	public void addInterval30Day(Interval30 interval30);

	public Customer getCustomer(String companyName);

	public void addCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public User getUser(long user_id);

	public void addUser(User user);

}
