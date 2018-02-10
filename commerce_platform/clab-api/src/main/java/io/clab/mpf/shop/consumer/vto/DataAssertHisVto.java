package io.clab.mpf.shop.consumer.vto;

import java.util.List;

import lombok.Data;

/***
 * 数据资产记录
 * **/
@Data
public class DataAssertHisVto {
	//private Long dataItemId;//数据项id
	private String businessName;//商家名称
	private Long creditQuantity;//积分数量
	private List<String> labelList;//所打标签
	private String dataTime;//数据时间
	private String dataState;//数据项状态：未授权权，已授权
}
