package com.hmel.myway.central.blogic.interfaces;

import com.hmel.myway.central.models.Autosuggest;
import com.hmel.myway.central.models.AutosuggestCriteria;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

public interface IAutosuggestService extends IHibernateDAO<Autosuggest, Long> {

	Autosuggest findByParams(AutosuggestCriteria autosuggestCriteria)
			throws MyWayException;

}
