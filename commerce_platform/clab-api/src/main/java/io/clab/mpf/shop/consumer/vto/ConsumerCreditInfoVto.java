package io.clab.mpf.shop.consumer.vto;

import lombok.Data;

/**
 * 消费者积分信息概要
 * balance = totalEarning - totalUse - freeze;
 * 
 * ***/
@Data
public class ConsumerCreditInfoVto {
	private Integer balance;//余额
	private Integer totalEarning; //累计获得积分总数
	private Integer totalUse;//累计支出的积分总数
	private Integer freeze;//冻结数量
	
}
