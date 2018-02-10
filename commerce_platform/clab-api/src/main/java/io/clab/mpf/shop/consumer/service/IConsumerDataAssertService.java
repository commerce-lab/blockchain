package io.clab.mpf.shop.consumer.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.vto.DataAssertHisVto;
import io.clab.mpf.shop.consumer.vto.DataAssertLabelVto;

/**
 * 消费者数据资产相关操作
 * **/
public interface IConsumerDataAssertService {
	/**
	 * *数据资产-与多少个商家有关系
	 * ***/
	public int getRelevantBusinessesCount(Long consumerId);
	/***
	 * 获取消费者资产总收入
	 * **/
	public int getGeneralIncome(Long consumerId);
	
	/***
	 * 获取消费者拥有的标签和对应的积分
	 * **/
	public List<DataAssertLabelVto> getDataAssertLabel(Long consumerId); 
	/***
	 * 获取数据记录历史
	 * **/
	public Page<DataAssertHisVto> getDataAssertHis(Long consumerId,int pageNow,int pageSize);
	
	/***
	 * 消费者对数据项授权，获得积分
	 * ***/
	public void grantDataItem(Long dataItemId,Long consumerId); 
}
