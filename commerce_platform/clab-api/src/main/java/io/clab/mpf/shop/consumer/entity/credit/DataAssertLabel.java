package io.clab.mpf.shop.consumer.entity.credit;

import lombok.Data;
/***
 * 数据资产-标签情况
 * **/
@Data
public class DataAssertLabel {
	private String labelType;//类型，比如:运动，音乐
	private String labelIcon;//标签对应的图标
	private long creditQuantity;//积分数量
	private long businessCount;//商家数量
}
