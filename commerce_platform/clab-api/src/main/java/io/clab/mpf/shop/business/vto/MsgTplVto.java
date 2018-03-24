package io.clab.mpf.shop.business.vto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="消息模板")
public class MsgTplVto {
	private String uuid;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("主题名称")
	private String subjName;
	@ApiModelProperty("模板类型：0系统设置，1商家自定义")
	private String subjType;
	@ApiModelProperty("状态")
	private String state;
	@ApiModelProperty("最后更新时间")
	private String lastUpdateTime;
}
