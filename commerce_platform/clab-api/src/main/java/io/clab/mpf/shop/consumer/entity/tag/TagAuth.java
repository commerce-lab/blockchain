package io.clab.mpf.shop.consumer.entity.tag;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value="用户标签授权信息")
@Data
@TableName("t_tag_authorization")
public class TagAuth{

	@ApiModelProperty("自增长")
	@TableId("aut_id")
	private Long authId;
	
	@ApiModelProperty("用户ID")
	private Long uuserId;
	
	@ApiModelProperty("标签ID")
	private Integer tagId;
	
	@ApiModelProperty("授权标记(0:否,1:是)")
	private Integer authorizationFlag;
	
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
		
}
