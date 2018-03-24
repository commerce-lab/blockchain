package io.clab.mpf.shop.business.entity.msg;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@TableName("t_msg_tpl_subj")
@ApiModel(value="消息模板主题")
public class MsgTplSubj {
	@TableId(type=IdType.UUID)
	private String uuid;
	private String name;
	private Long creator;
	private Integer type ;//模板类型:0系统设置，1商家自定义
	private String createTime;
	private Integer deleted; 
}
