package io.clab.mpf.shop.consumer.vto;

import lombok.Data;

/**
 * 用户获取或者支出积分历史记录
 * ***/
@Data
public class CreditHisVto {
	private String title;//如“ 三星 52L黑款旅行袋；兑换”，“Beurer 72 加热贴；授权”，“新人礼包”
	private double quantity;//增减积分数量
	private String createTime;//积分变动时间
	
}
