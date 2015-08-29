package com.hmel.myway.central.blogic.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IAutosuggestService;
import com.hmel.myway.central.models.Autosuggest;
import com.hmel.myway.central.models.AutosuggestCriteria;
import com.hmel.myway.central.models.Criteria;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

@Service
public class AutosuggestService extends BaseHibernateDAO<Autosuggest, Long>
		implements IAutosuggestService {

	private static final Logger logger = LoggerFactory
			.getLogger(AutosuggestService.class);

	@Override
	public Autosuggest findByParams(AutosuggestCriteria autosuggestCriteria)
			throws MyWayException {
		logger.info("findByParams");
		if (autosuggestCriteria == null) {
			throw new NullPointerException("autosuggestCriteria is null");
		}
		String criteria = autosuggestCriteria.getCriteria();
		Integer startPage = autosuggestCriteria.getPage();
		Integer pageLimit = autosuggestCriteria.getLimit();
		if (StringUtils.isBlank(criteria)) {
			throw new MyWayException("criteria is empty");
		}
		if (startPage < 1) {
			startPage = 1;
		}
		if (pageLimit == 0 || pageLimit < 0) {
			pageLimit = 6;
		}
		int firstRecordForSearch = (startPage - 1) * pageLimit;
		getCurrentSession().beginTransaction();
		Autosuggest autosuggest = null;
		try {
			DetachedCriteria cr = DetachedCriteria.forClass(Criteria.class);
			cr.add(Restrictions.like("name", criteria, MatchMode.START));

			@SuppressWarnings("unchecked")
			List<Criteria> lstCriteria = cr
					.getExecutableCriteria(getCurrentSession())
					.setFirstResult(firstRecordForSearch)
					.setMaxResults(pageLimit).list();
			autosuggest = new Autosuggest(startPage, lstCriteria.size(),
					lstCriteria);
		} finally {
			getCurrentSession().close();
		}

		return autosuggest;
	}

}
