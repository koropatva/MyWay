package com.hmel.myway.central.blogic.interfaces;

import java.util.List;

import com.hmel.myway.central.holder.BaseConditionHolder;
import com.hmel.myway.central.models.Block;
import com.hmel.myway.dao.blogic.interfaces.IHibernateDAO;
import com.hmel.myway.exceptions.PhoneDictionaryException;

public interface IBlockService extends IHibernateDAO<Block, Long> {
	
	List<Block> findByConditionHolder(BaseConditionHolder holder) throws PhoneDictionaryException;
}