package io.clab.mpf.shop.consumer.entity.credit;

import java.util.List;

import lombok.Data;
/**
 * 数据详情
 * 见消费者原型，我的数据资产 -> 数据记录详情
 * ***/
@Data
public class DataAssertDetail {
	private Long dataId;//数据行id
	private String businessName;//商家名称
	private String businessLogo;//商家logo地址
	private String businessUrl;//旗舰店地址
	private Long creditQuantity;//商家给予的积分数量
	private String category;//品类
	private List<String> labelList;//标签列表
	private String createTime;//创建时间
}
