package io.clab.mpf.shop.common.entity;

import lombok.Data;
/***
 * 消息内容
 * by chenzhenhua
 * **/
@Data
public class MsgContent {
	private Long id;
	private String type;//消息类型：短信,push
	private String title;//push标题
	private String content;//短信内容或者push内容
	private String jumpUrl;//appPush跳转路径
	private Long msgBasicInfoId;//关联MsgBasicInfo 的id字段
}
