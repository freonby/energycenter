package by.qlab.energycenter.dao;

import by.qlab.energycenter.model.Interval30;

public interface DAO {
	public Interval30 getInterval30Day(long device_id);

	public void addInterval30Day(Interval30 interval30);

}
