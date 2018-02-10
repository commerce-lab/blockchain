package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="数据项与标签关系")
public class DiTag implements Serializable{

	@ApiModelProperty("自增长")
	private Long DiTagId;
	
	@ApiModelProperty("标签ID")
	private Integer tagId;
	
	@ApiModelProperty("数据项ID")
	private Integer diId;
	
	@ApiModelProperty("上传用户ID")
	private Long uuserId;
	
	@ApiModelProperty("商家ID")
	private Long merchantsId;

	public Long getDiTagId() {
		return DiTagId;
	}

	public void setDiTagId(Long diTagId) {
		DiTagId = diTagId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getDiId() {
		return diId;
	}

	public void setDiId(Integer diId) {
		this.diId = diId;
	}

	public Long getUuserId() {
		return uuserId;
	}

	public void setUuserId(Long uuserId) {
		this.uuserId = uuserId;
	}

	public Long getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Long merchantsId) {
		this.merchantsId = merchantsId;
	}


	

}
