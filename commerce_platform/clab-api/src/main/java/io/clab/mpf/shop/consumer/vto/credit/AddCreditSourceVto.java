package io.clab.mpf.shop.consumer.vto.credit;

import lombok.Data;
/**
 * 消费者获取的积分来源汇总
 * ***/
@Data
public class AddCreditSourceVto {
	private long grantData;//数据搜权
	private long shopPresent;//商家赠送
	private long consumptionReurn;//消费返还
	private long dailyTask;//日常任务
}
