package io.clab.mpf.shop.consumer.service.dataassert;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.entity.credit.DataAssertDetail;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertLabel;
import io.clab.mpf.shop.consumer.vto.dataAssert.CreditHisByLabelVto;

/**
 * 消费者数据资产相关操作
 * **/
public interface IConsumerDataAssertService {
	/**
	 * *数据资产-与多少个商家有关系
	 * ***/
	public int getRelevantBusinessCount(Long consumerId);
	/***
	 * 获取消费者资产总收入
	 * **/
	public long getGeneralIncome(Long consumerId);
	
	/***
	 * 获取消费者拥有的标签和对应的积分
	 * **/
	public List<DataAssertLabel> getDataAssertLabel(Long consumerId); 
	/***
	 * 获取数据记录历史
	 * **/
	public Page<DataAssertDetail> getDataAssertHis(Long consumerId,int pageNow,int pageSize);
	
	/*
	 * 消费者对数据项授权，获得积分
	 */
	//public void grantDataItem(Long dataItemId,Long consumerId); 
	
	
	/**
	 *获取消费者是否对某个标签进行了授权
	 * **/
	public boolean isGrantLabel(Long consumerId,String label);
	
	/***
	 * 数据记录详情
	 * 见消费者原型，我的数据资产 ->  数据记录详情
	 * 郭，用consumerId 校验数据项是属于该消费者的
	 * ***/
	public DataAssertDetail getDataDetail(Long consumerId,Long dataId);
	/**
	 * 某个标签取得的积分数量
	 * 
	 * 见消费者原型，我的数据资产 -> 标签数据详情
	 **/
	public long getCreditQuantityByLabel(Long consumerId,String label);
	/***
	 * 消费者通过某个标签获取积分的历史来源记录
	 * 
	 * 见消费者原型，我的数据资产 -> 标签数据详情
	 **/
	public List<CreditHisByLabelVto> getCreditHisByLabel(Long consumerId,String label);
	/**
	 * 授权开关
	 * 见消费者原型，我的数据资产 -> 标签数据详情
	 * @isGrantg 是否对某个标签授权，【授权开关】，用户关闭了授权后，则其它商户不可使用
	 **/
	public boolean grantLabelSwitch(Long consumerId,String label,boolean isGrantg);
}
