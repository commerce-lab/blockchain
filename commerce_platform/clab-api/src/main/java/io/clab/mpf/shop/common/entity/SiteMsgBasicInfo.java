package io.clab.mpf.shop.common.entity;

import lombok.Data;

/**
 * 站内消息基本信息
 * ***/
@Data
public class  SiteMsgBasicInfo {
	private Long id;
	private Long senderId;//发送人
	private String state;//消息状态：未发送，已发送
	private int receiverCount;//接收人数量
	private String noticeType;//消息类型
	private String title;//标题
	private String summary;//内容概要
	private String content;//内容
	private String jumpUrl;//跳转路径
	private String beginSendTime;//调用发送的时间
	private String endSendTime;//发送完所有手机号的时间
	private String createTime;//消息创建时间
}
