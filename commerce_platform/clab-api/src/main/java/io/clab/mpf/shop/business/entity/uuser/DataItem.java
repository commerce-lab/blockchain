package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="数据项")
public class DataItem implements Serializable{
	
	@ApiModelProperty("自增长")
	private Integer diId;
	
	@ApiModelProperty("商家ID")
	private Long merchantsId;
	
	@ApiModelProperty("数据项来源(1:上传,2:购买)")
	private Integer diSource;
	
	@ApiModelProperty("数据类型（1:用户标签数据）")
	private Integer diType;
	
	@ApiModelProperty("数据项名称")
	private String diName;
	
	@ApiModelProperty("描述")
	private String description;
	
	@ApiModelProperty("每个用户赠送积分数")
	private Long sendPoints;
	
	@ApiModelProperty("上传文件名")
	private String uploadFileName;
	
	@ApiModelProperty("有效记录数")
	private Integer rightNum;
	
	@ApiModelProperty("无效记录数")
	private Integer errorNum;
	
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	
	@ApiModelProperty("总支出")
	private Long totalPay;
	
	@ApiModelProperty("总收入")
	private Long totalIncome;

	public Integer getDiId() {
		return diId;
	}

	public void setDiId(Integer diId) {
		this.diId = diId;
	}

	public Long getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Long merchantsId) {
		this.merchantsId = merchantsId;
	}

	public Integer getDiSource() {
		return diSource;
	}

	public void setDiSource(Integer diSource) {
		this.diSource = diSource;
	}

	public Integer getDiType() {
		return diType;
	}

	public void setDiType(Integer diType) {
		this.diType = diType;
	}

	public String getDiName() {
		return diName;
	}

	public void setDiName(String diName) {
		this.diName = diName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSendPoints() {
		return sendPoints;
	}

	public void setSendPoints(Long sendPoints) {
		this.sendPoints = sendPoints;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Long totalPay) {
		this.totalPay = totalPay;
	}

	public Long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Long totalIncome) {
		this.totalIncome = totalIncome;
	}
	
}
