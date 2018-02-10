package io.clab.mpf.shop.consumer.service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.vto.AddCreditSourceVto;
import io.clab.mpf.shop.consumer.vto.ConsumerCreditInfoVto;
import io.clab.mpf.shop.consumer.vto.CreditHisVto;

/**
 * 消费者积分相关操作
 */
public interface IConsumerCreditService {
	/**
	 * @param userId 用户id
	 * @return 消费者积分信息概要
	 */
	public ConsumerCreditInfoVto getCreditInfo(long userId);
	
	/**
	 * @param userId
	 * @param pageNow 当年页
	 * @param pageSize 每页数量
	 * @param creditHisRangeEnum 积分类型（全部，收入，支出）
	 * @return 用户获取或者支出积分历史记录
	 */
	public Page<CreditHisVto> getCreditHisList(long userId,CreditHisRangeEnum creditHisRangeEnum,int pageNow,int pageSize);
	
	/**
	 * @param userId
	 * @return 消费者获取的积分来源汇总
	 */
	public AddCreditSourceVto getAddCreditSource(long userId);
	
}
