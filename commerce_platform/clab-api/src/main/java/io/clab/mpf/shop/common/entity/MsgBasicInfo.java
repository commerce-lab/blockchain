package io.clab.mpf.shop.common.entity;

import lombok.Data;
/***
 * 消息发送基本信息表
 *  * by chenzhenhua
 * **/
@Data
public class MsgBasicInfo {
	private Long id;
	private Long senderId;//发件人
	private Integer state;//消息状态
	private String content;//短信内容
	private int mobileCount;//收件人号码数量
	private String beginSendTime;//调用发送的时间
	private String endSendTime;//发送完所有手机号的时间
	private String createTime;//消息创建时间
}
