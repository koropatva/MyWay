package com.hmel.myway.central.blogic.interfaces;

import java.util.List;

import com.hmel.myway.central.models.BlockSearchRespone;
import com.hmel.myway.central.models.CriteriaSearchHolder;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

public interface IBlockSearchResponeService extends
		IHibernateDAO<BlockSearchRespone, Long> {

	public List<BlockSearchRespone> findByCriteriaHolder(
			CriteriaSearchHolder holder) throws MyWayException;

}
