package by.qlab.energycenter.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

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

}
