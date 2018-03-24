package io.clab.mpf.shop.common.service.impl;


import io.clab.mpf.shop.common.repository.MyBatisSql;
import io.clab.mpf.shop.common.service.IMyBatisSqlService;
import io.clab.mpf.shop.common.util.MyBatisSqlUtil;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyBatisSqlServiceImpl implements IMyBatisSqlService{

	private static Logger logger = Logger.getLogger(MyBatisSqlServiceImpl.class);
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	 * 获取mybatis Sql和传参
	 * @param id 就是sql唯一编号， 如：用户资产列表sql id为：io.clab.mpf.shop.business.repository.uuser.UuserMapper.selectUserAssetsList
	 * @param paramMap 传参集合
	 * */
	public MyBatisSql getSqlById(String id,Map<String, Object> paramMap){

		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        MyBatisSql sql = MyBatisSqlUtil.getMyBatisSql(id, paramMap, sqlSessionFactory); 

        logger.info("getSqlById-- sql --->"+sql.getSql());
        logger.info("getSqlById-- parameters --->"+sql.getParameters());

		return sql;
	}
}
