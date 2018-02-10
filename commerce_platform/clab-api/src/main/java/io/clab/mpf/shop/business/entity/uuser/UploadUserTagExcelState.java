package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="上传用户标签情况")
public class UploadUserTagExcelState implements Serializable{

	@ApiModelProperty("进度百分比")
	private float percent;
	
	@ApiModelProperty("有效记录数")
	private Integer rightNum;
	
	@ApiModelProperty("无效记录数")
	private Integer errorNum;

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
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
	
	
}
