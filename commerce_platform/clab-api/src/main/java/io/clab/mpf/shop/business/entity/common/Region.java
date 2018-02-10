package io.clab.mpf.shop.business.entity.common;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="地区")
public class Region implements Serializable{

	@ApiModelProperty("自增长")
	private Integer regionId;
	
	@ApiModelProperty("行政区划代码")
	private Integer regionCode;
	
	@ApiModelProperty("上级代码")
	private Integer parentCode;
	
	@ApiModelProperty("名称")
	private String regionName;
	
	@ApiModelProperty("英文名称")
	private String regionNameEn;
	
	@ApiModelProperty("英文简名称")
	private String regionNameEnShort;
	
	@ApiModelProperty("层级")
	private Integer level;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionNameEn() {
		return regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	public String getRegionNameEnShort() {
		return regionNameEnShort;
	}

	public void setRegionNameEnShort(String regionNameEnShort) {
		this.regionNameEnShort = regionNameEnShort;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	

}
