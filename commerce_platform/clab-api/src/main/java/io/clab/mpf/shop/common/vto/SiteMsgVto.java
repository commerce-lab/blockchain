package io.clab.mpf.shop.common.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 收件人接收的站内消息
 * by czh
 * ***/
@Data
@ApiModel(value="接收的站内消息")
public class SiteMsgVto {
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("发件人userId")
	private Long senderId; 
	@ApiModelProperty("发件人名称")
	private String senderName;//发件人名称，商家名称或者系统消息
	@ApiModelProperty("读取状态:已读取，未读取")
	private String state;
	private String sendTime;
	@ApiModelProperty("读取时间")
	private String readTime;
	@ApiModelProperty("消息类型")
	private String noticeType;
	@ApiModelProperty("标题")
	private String title;
	@ApiModelProperty("内容概要")
	private String summary;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("跳转路径")
	private String jumpUrl;
}
