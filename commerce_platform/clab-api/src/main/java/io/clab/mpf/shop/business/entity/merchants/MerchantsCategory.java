package io.clab.mpf.shop.business.entity.merchants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="商家类目")
public class MerchantsCategory implements Serializable{

	@ApiModelProperty("自增长")
	private Integer mcId;
	
	@ApiModelProperty("上级ID")
	private Integer parentId;
	
	@ApiModelProperty("类目名称")
	private String categoryName;

	public Integer getMcId() {
		return mcId;
	}

	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
