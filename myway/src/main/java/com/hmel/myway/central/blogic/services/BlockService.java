package com.hmel.myway.central.blogic.services;

import org.springframework.stereotype.Service;

import com.hmel.myway.central.blogic.interfaces.IBlockService;
import com.hmel.myway.central.models.Block;
import com.hmel.myway.dao.blogic.services.BaseHibernateDAO;

@Service
public class BlockService extends BaseHibernateDAO<Block, Long> implements
		IBlockService {

}
