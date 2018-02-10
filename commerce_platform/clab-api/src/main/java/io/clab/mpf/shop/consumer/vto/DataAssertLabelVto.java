package io.clab.mpf.shop.consumer.vto;

import lombok.Data;
/***
 * 数据资产-标签情况
 * **/
@Data
public class DataAssertLabelVto {
	private String labelType;//类型，比如:运动，音乐
	private String labelIcon;//标签对应的图标
	private long creditQuantity;//积分数量
	private long bussinerssCount;//商家数量
}
