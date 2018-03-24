package io.clab.mpf.shop.common.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
/**
 * 站内消息接收人信息
 * ***/
@Data
@TableName("t_site_msg_receiver")
public class SiteMsgReceiver {
	private Long id;
	private Long siteMsgBasicInfoId;//关联SiteNoticeBasicInfo 的id字段
	private Long receiverId;//接收人id
	private String state;//发送状态:未发送，未读取，已读取，已删除
	private String readTime;//读取时间
	private String deleteTime;//逻辑删除时间
	private int deleted;//逻辑删除
}
