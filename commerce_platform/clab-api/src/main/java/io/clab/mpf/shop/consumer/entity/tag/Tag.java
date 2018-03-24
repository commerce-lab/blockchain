package io.clab.mpf.shop.consumer.entity.tag;

import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="标签")
@TableName("t_tag")
public class Tag{

	@ApiModelProperty("自增长")
	private Integer tagId;
	
	@ApiModelProperty("标签名称")
	private String tagName;

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
}
