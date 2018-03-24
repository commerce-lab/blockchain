package io.clab.mpf.shop.business.entity.msg;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("t_msg_tpl")
@ApiModel(value="消息模板")
public class MsgTpl {
	@TableId(type=IdType.UUID)
	private String uuid;
	private Long creator;//创建人
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("主题")
	private String msgTplSubjId;
	private String state;//模板状态
	@ApiModelProperty("创建时间")
	private String createTime;
	@ApiModelProperty("最后更新时间")
	private String lastUpdateTime;
	private  int deleted; 
}
