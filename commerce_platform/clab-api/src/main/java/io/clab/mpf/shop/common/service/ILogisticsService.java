package io.clab.mpf.shop.common.service;


import io.clab.mpf.shop.business.service.base.IBaseService;
import io.clab.mpf.shop.common.entity.LogisticsMsg;

public interface ILogisticsService extends IBaseService<LogisticsMsg>{

	/**
	 * 快递鸟回推物流信息
	 * */
	public Boolean kdBirdPush(String data);
	
	//查询物流
	public LogisticsMsg selectByLogisticNo(String logisticNo);
	
	
}
