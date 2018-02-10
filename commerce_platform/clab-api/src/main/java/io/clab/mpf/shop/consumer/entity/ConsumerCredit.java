package io.clab.mpf.shop.consumer.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * 消费者积分信息
 * balance = totalEarning - totalUse - freeze;
 * 
 * ***/
@Data
@TableName("t_user_credit_pool")
public class ConsumerCredit {
	private Long id; //id
	private Long userId; //用户ID
	private Integer balance;//余额
	private Integer totalEarning; //累计获得积分总数
	private Integer totalUse;//累计支出的积分总数
	private Integer freeze;//冻结数量
	private Integer assertValue; //数据资产
	
}
