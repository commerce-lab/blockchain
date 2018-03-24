package io.clab.mpf.shop.common.service;

import io.clab.mpf.shop.common.repository.MyBatisSql;

import java.util.Map;

public interface IMyBatisSqlService {

	/**
	 * 获取mybatis Sql和传参
	 * @param id 就是sql唯一编号， 如：用户资产列表sql id为：io.clab.mpf.shop.business.repository.uuser.UuserMapper.selectUserAssetsList
	 * @param paramMap 传参集合
	 * */
	public MyBatisSql getSqlById(String id,Map<String, Object> paramMap);
}
