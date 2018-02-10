package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="上传用户标签情况")
public class AssetsCount implements Serializable{

	@ApiModelProperty("上传次数")
	private Integer uploadTimes;
	
	@ApiModelProperty("上传数")
	private Integer uploadNum;
	
	@ApiModelProperty("购买次数")
	private Integer buyTimes;
	
	@ApiModelProperty("购买数")
	private Integer buyNum;

	public Integer getUploadTimes() {
		return uploadTimes;
	}

	public void setUploadTimes(Integer uploadTimes) {
		this.uploadTimes = uploadTimes;
	}

	public Integer getUploadNum() {
		return uploadNum;
	}

	public void setUploadNum(Integer uploadNum) {
		this.uploadNum = uploadNum;
	}

	public Integer getBuyTimes() {
		return buyTimes;
	}

	public void setBuyTimes(Integer buyTimes) {
		this.buyTimes = buyTimes;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	
}
