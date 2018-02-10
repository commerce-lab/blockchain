package io.clab.mpf.shop.common.entity;

import lombok.Data;

/***
 * 消息接收人手机号
 * 
 * by chenzhenhua
 * ***/
@Data
public class MsgReceiverMobile {
	private Long msgBasicInfoId;//关联MsgBasicInfo 的id字段
	private String mobile;//手机号
	private String state;//发送状态:未发送，发送成功，发送失败
	private String sendTime;//发送时间
}
