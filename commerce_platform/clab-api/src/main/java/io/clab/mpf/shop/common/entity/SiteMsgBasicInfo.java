package io.clab.mpf.shop.common.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * 站内消息基本信息
 * ***/
@Data
@TableName("t_site_msg_basic_info")
public class  SiteMsgBasicInfo {  
	private Long id;
	private Long senderId;//发送人
	private int isSend;//是否发送,0->未发送，1已发送
	private int receiverCount;//接收人数量
	private String noticeType;//消息类型
	private String title;//标题
	private String summary;//内容概要
	private String content;//内容
	private String jumpUrl;//跳转路径
	private String sendTime;//调用发送的时间
	private String createTime;//消息创建时间
}
