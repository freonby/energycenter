package by.qlab.energycenter.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import by.qlab.energycenter.datamodel.Customer;
import by.qlab.energycenter.datamodel.User;
import by.qlab.energycenter.model.Interval30;

public class HibDAO implements DAO {

	private SessionFactory sessionFactory;

	public HibDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Interval30 getInterval30Day(long device_id) {
		Interval30 item = (Interval30) sessionFactory.getCurrentSession().createCriteria(Interval30.class).add(Restrictions.eq("device_id", device_id)).uniqueResult();
		return item;
	}

	@Override
	@Transactional
	public void addInterval30Day(Interval30 interval30) {
		sessionFactory.getCurrentSession().save(interval30);

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
	public User getUser(long user_id) {
		User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", user_id)).uniqueResult();
		return user;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);

	}

}
