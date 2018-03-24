package io.clab.mpf.shop.consumer.entity.credit;

import lombok.Data;

/***
 * 数据资产记录
 * by chenzhenhua
 * **/
@Data
public class DataAssertHis {
	private Long dataId;//数据行id 
	private String businessName;//商家名称
	private String businessLogo;//商家logo地址
	private Long creditQuantity;//商家给予的积分数量
	//private List<String> labelList;//所打标签
	private String createTime;//数据时间
}
