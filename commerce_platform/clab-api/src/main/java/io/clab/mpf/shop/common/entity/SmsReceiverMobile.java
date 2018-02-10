package io.clab.mpf.shop.common.entity;

import lombok.Data;

/***
 * 短信接收人手机号
 * 
 * by chenzhenhua
 * ***/
@Data
public class SmsReceiverMobile {
	private Long smsBasicInfoId;//关联SmsBasicInfo 的id字段
	private String mobile;//手机号
	private String content;
	private String state;//发送状态，见类SmsReceiverMobileStateConstant
	private String sendTime;//发送时间
}
