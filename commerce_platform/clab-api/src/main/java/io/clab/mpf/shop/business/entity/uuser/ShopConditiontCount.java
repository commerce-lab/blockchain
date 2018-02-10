package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="超市筛选统计")
public class ShopConditiontCount implements Serializable{

	@ApiModelProperty("用户数")
	private Long userCount;
	
	@ApiModelProperty("标签数")
	private Long tagCount;
	
	@ApiModelProperty("商家数")
	private Long merchantsCount;
	
	@ApiModelProperty("类目数")
	private Long categoryCount;

	public Long getUserCount() {
		return userCount;
	}

	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	public Long getTagCount() {
		return tagCount;
	}

	public void setTagCount(Long tagCount) {
		this.tagCount = tagCount;
	}

	public Long getMerchantsCount() {
		return merchantsCount;
	}

	public void setMerchantsCount(Long merchantsCount) {
		this.merchantsCount = merchantsCount;
	}

	public Long getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(Long categoryCount) {
		this.categoryCount = categoryCount;
	}
	
	
}
