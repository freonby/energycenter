package by.qlab.energycenter.dao;

import java.util.Date;
import java.util.List;

import by.qlab.energycenter.databuffer.IntervalStrings;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.databuffer.TimeZone;
import by.qlab.energycenter.datamodel.Customer;

public interface DAO {

	public Customer getCustomer(String companyName);

	public void addCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public List<Register> getRegisterList(String energyMeterNumber, int energyType, Date start_YYYY_MM_DD, Date end_YYYY_MM_DD);

	public List<Register> getAllRegisterList(Date start_YYYY_MM_DD, Date end_YYYY_MM_DD);

	public Register getRegister(String energyMeterNumber, int intervalNumber, int energyType, Date date);

	public void addRegister(Register register);

	public List<IntervalStrings> getIntervals();

	public List<TimeZone> getZone();

}
