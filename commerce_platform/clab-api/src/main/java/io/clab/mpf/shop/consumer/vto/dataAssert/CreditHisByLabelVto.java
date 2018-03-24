package io.clab.mpf.shop.consumer.vto.dataAssert;

import java.util.Date;

import lombok.Data;
/**
 * 消费者通过某个标签获取积分的历史来源记录
 * ***/
@Data
public class CreditHisByLabelVto {
	private String title;//如：授权，赠送
	private String merchantName;//商家名称
	private String businessLogo;//商家logo地址
	private Integer quantity;//给予积分数量
	private Date createTime;//积分变动时间
}
