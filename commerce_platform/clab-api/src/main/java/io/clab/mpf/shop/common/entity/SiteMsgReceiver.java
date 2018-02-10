package io.clab.mpf.shop.common.entity;

import lombok.Data;
/**
 * 站内消息接收人信息
 * ***/
@Data
public class SiteMsgReceiver {
	private Long id;
	private Long SiteNoticeBasicInfoId;//关联SiteNoticeBasicInfo 的id字段
	private Long receiverId;//接收人id
	private String state;//发送状态:未发送，已发送未读取，已读取
	private String sendTime;//发送时间
	private String readTime;//读取时间
	private String deleteTime;//逻辑删除时间
	private int deleted;//逻辑删除
}
