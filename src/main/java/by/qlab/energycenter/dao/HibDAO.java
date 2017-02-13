package by.qlab.energycenter.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.transaction.annotation.Transactional;

import by.qlab.energycenter.databuffer.IntervalStrings;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.databuffer.TimeZone;
import by.qlab.energycenter.datamodel.Customer;

public class HibDAO implements DAO {

	private SessionFactory sessionFactory;

	public HibDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Customer getCustomer(String companyName) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class).add(Restrictions.eq("companyName", companyName)).uniqueResult();
		return customer;
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);

	}

	@Override
	@Transactional
	public void addRegister(Register register) {
		sessionFactory.getCurrentSession().saveOrUpdate(register);

	}

	@Override
	@Transactional
	public List<Register> getRegisterList(String energyMeterNumber, int energyType, Date start_YYYY_MM_DD, Date end_YYYY_MM_DD) {
		Criteria criteria48 = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteria48.add(Restrictions.eq("energyMeter_number", energyMeterNumber));
		criteria48.add(Restrictions.eq("energyType", energyType));
		criteria48.add(Restrictions.ge("date", start_YYYY_MM_DD));
		criteria48.add(Restrictions.le("date", end_YYYY_MM_DD));
		@SuppressWarnings("unchecked")
		List<Register> listIntervals = (List<Register>) criteria48.list();
		Criteria criteriaMax = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteriaMax.add(Restrictions.eq("energyMeter_number", energyMeterNumber));
		criteriaMax.add(Restrictions.eq("energyType", energyType));
		criteriaMax.add(Restrictions.ge("date", start_YYYY_MM_DD));
		criteriaMax.add(Restrictions.le("date", end_YYYY_MM_DD));
		criteriaMax.setProjection(Projections.max("consumption"));
		criteriaMax.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
		double maxValue = (Double) criteriaMax.uniqueResult();
		Register rmax = new Register();
		rmax.setConsumption(maxValue);
		listIntervals.add(rmax);
		Criteria criteriaSum = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteriaSum.add(Restrictions.eq("energyMeter_number", energyMeterNumber));
		criteriaSum.add(Restrictions.eq("energyType", energyType));
		criteriaSum.add(Restrictions.ge("date", start_YYYY_MM_DD));
		criteriaSum.add(Restrictions.le("date", end_YYYY_MM_DD));
		criteriaSum.setProjection(Projections.sum("consumption"));
		double sumValue = (Double) criteriaSum.uniqueResult();
		Register rsum = new Register();
		rsum.setConsumption(sumValue);
		listIntervals.add(rsum);

		return listIntervals;
	}

	@Override
	@Transactional
	public Register getRegister(String energyMeterNumber, int intervalNumber, int energyType, Date date) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteria.add(Restrictions.eq("energyMeter_number", energyMeterNumber));
		criteria.add(Restrictions.eq("intervalNumber", intervalNumber));
		criteria.add(Restrictions.eq("energyType", energyType));
		criteria.add(Restrictions.eq("date", date));
		Register register = (Register) criteria.uniqueResult();
		return register;
	}

	@Override
	@Transactional
	public List<IntervalStrings> getIntervals() {
		@SuppressWarnings("unchecked")
		List<IntervalStrings> list = (List<IntervalStrings>) sessionFactory.getCurrentSession().createCriteria(IntervalStrings.class).list();
		return list;
	}

	@Override
	@Transactional
	public List<TimeZone> getZone() {
		@SuppressWarnings("unchecked")
		List<TimeZone> list = (List<TimeZone>) sessionFactory.getCurrentSession().createCriteria(TimeZone.class).list();
		return list;
	}

	@Override
	@Transactional
	public List<Register> getAllRegisterList(Date start_YYYY_MM_DD, Date end_YYYY_MM_DD) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Register.class);
		criteria.add(Restrictions.ge("date", start_YYYY_MM_DD));
		criteria.add(Restrictions.le("date", end_YYYY_MM_DD));
		@SuppressWarnings("unchecked")
		List<Register> listIntervals = (List<Register>) criteria.list();
		return listIntervals;
	}

}
