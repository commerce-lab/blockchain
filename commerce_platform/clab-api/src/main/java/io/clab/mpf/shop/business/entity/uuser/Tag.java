package io.clab.mpf.shop.business.entity.uuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="标签")
public class Tag implements Serializable{

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
