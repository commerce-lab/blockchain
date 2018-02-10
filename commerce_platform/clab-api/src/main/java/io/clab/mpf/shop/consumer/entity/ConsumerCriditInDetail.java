package io.clab.mpf.shop.consumer.entity;

import lombok.Data;

/**
 * 消费者积分明细-获取到的积分
 * **/
@Data
public class ConsumerCriditInDetail {
	private Long detailId;
	private Long userId;
	private String name;//如“分享"
	private String shopId;//商家id
	private String channel;//积分来源渠道：商家，系统。取值来源CriditChannelEnum
	private Double quantity;//获取的积分数量
	private String createTime;//创建时间
}
