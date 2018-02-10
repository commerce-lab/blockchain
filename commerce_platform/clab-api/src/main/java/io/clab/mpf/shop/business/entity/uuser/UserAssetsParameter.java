package io.clab.mpf.shop.business.entity.uuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="用户资产传参")
public class UserAssetsParameter {

	@ApiModelProperty("商家ID")
	private Integer merchantsId;
	
	@ApiModelProperty("数据项ID")
	private Integer diId;
	
	@ApiModelProperty("更新开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startCreateTime;
	
	@ApiModelProperty("更新结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endCreateTime;
	
	@ApiModelProperty("数据项来源(1:上传,2:购买)")
	private Integer diSource;
	
	@ApiModelProperty("性别(0:女,1:男)")
	private Integer sex;
	
	@ApiModelProperty("生日开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startBirthdayTime;
	
	@ApiModelProperty("生日结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endBirthdayTime;
	
	@ApiModelProperty("手机号码")
	private Integer phone ;
	
	@ApiModelProperty("用户姓名")
	private Integer name ;
	
	@ApiModelProperty("标签集")
	private List<Tag> tags ;

	public Integer getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Integer merchantsId) {
		this.merchantsId = merchantsId;
	}

	public Integer getDiId() {
		return diId;
	}

	public void setDiId(Integer diId) {
		this.diId = diId;
	}

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Integer getDiSource() {
		return diSource;
	}

	public void setDiSource(Integer diSource) {
		this.diSource = diSource;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getStartBirthdayTime() {
		return startBirthdayTime;
	}

	public void setStartBirthdayTime(Date startBirthdayTime) {
		this.startBirthdayTime = startBirthdayTime;
	}

	public Date getEndBirthdayTime() {
		return endBirthdayTime;
	}

	public void setEndBirthdayTime(Date endBirthdayTime) {
		this.endBirthdayTime = endBirthdayTime;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
