package com.hmel.myway.central.blogic.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IBlockSearchResponeService;
import com.hmel.myway.central.models.BlockSearchRespone;
import com.hmel.myway.central.models.CriteriaBlock;
import com.hmel.myway.central.models.CriteriaSearchHolder;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;
import com.hmel.myway.exceptions.MyWayException;

@Service
public class BlockSearchResponeService extends
		BaseHibernateDAO<BlockSearchRespone, Long> implements
		IBlockSearchResponeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<BlockSearchRespone> findByCriteriaHolder(
			CriteriaSearchHolder holder) throws MyWayException {
		logger.info("findByCriteriaHolder");
		if (holder == null) {
			throw new NullPointerException("holder is null");
		}
		List<BlockSearchRespone> res = new ArrayList<>();
		if (!holder.getCriteries_ids().isEmpty()) {
			try {
				getCurrentSession().beginTransaction();

				DetachedCriteria criteria = DetachedCriteria
						.forClass(CriteriaBlock.class);
				criteria.add(Restrictions.in("criteria.id",
						holder.getCriteries_ids()));
				criteria.setProjection(Projections.property("block.id"));
				List<Long> lstId = criteria
						.getExecutableCriteria(getCurrentSession())
						.setFirstResult(0).setMaxResults(Integer.MAX_VALUE)
						.list();
				if (lstId != null && !lstId.isEmpty()) {
					criteria = DetachedCriteria
							.forClass(BlockSearchRespone.class);
					criteria.add(Restrictions.in("id", lstId));
					res = criteria.getExecutableCriteria(getCurrentSession())
							.setFirstResult(0).setMaxResults(Integer.MAX_VALUE)
							.list();
				}
			} finally {
				getCurrentSession().close();
			}
		}
		return res;
	}

}
