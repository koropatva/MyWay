package com.hmel.myway.central.holder;

import org.hibernate.criterion.DetachedCriteria;

public abstract class BaseConditionHolder {

	public int firstPage = 0;
	public int pageSize = 10;

	public abstract DetachedCriteria loadCriteria();

	public abstract void reset();

}
