package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="最新上传数据")
public class ShopNewData implements Serializable{

	@ApiModelProperty("标签")
	private String tags;
	
	@ApiModelProperty("类目")
	private String ategorys;
	
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	@ApiModelProperty("用户数")
	private Long userCount;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAtegorys() {
		return ategorys;
	}

	public void setAtegorys(String ategorys) {
		this.ategorys = ategorys;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserCount() {
		return userCount;
	}

	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	
	
}
