package io.clab.mpf.shop.common.entity;

import lombok.Data;
/***
 * 消息发送基本信息表
 *  * by chenzhenhua
 * **/
@Data
public class SmsBasicInfo {
	private Long id;
	private Long senderId;//发件人
	private String state;//短信状态，见类SmsBasicInfoStateConstant 
	private String contentTemplate;//短信内容模板
	private int mobileCount;//收件人号码数量
	private String beginSendTime;//调用发送的开始时间
	private String endSendTime;//发送完所有手机号的时间
	private String abortSendTime;//终止发送的时间
	private String createTime;//消息创建时间
}
