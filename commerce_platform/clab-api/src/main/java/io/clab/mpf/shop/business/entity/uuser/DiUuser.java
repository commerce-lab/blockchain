package io.clab.mpf.shop.business.entity.uuser;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="数据项与上传用户关系")
public class DiUuser implements Serializable{

	@ApiModelProperty("自增长")
	private Long diUuserId;
	
	@ApiModelProperty("上传用户ID")
	private Long uuserId;
	
	@ApiModelProperty("商家ID")
	private Long merchantsId;
	
	@ApiModelProperty("数据项ID")
	private Integer diId;
	
	@ApiModelProperty("数据项来源(1:上传,2:购买)")
	private Integer diSource;
	
	@ApiModelProperty("上传为支付给用户积分；购买为支付商家积分")
	private Long payPoints;
	
	@ApiModelProperty("支付状态（0：未支付，1：已支付）")
	private Integer payState;

	public Long getDiUuserId() {
		return diUuserId;
	}

	public void setDiUuserId(Long diUuserId) {
		this.diUuserId = diUuserId;
	}



	public Long getUuserId() {
		return uuserId;
	}

	public void setUuserId(Long uuserId) {
		this.uuserId = uuserId;
	}

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

	public Long getPayPoints() {
		return payPoints;
	}

	public void setPayPoints(Long payPoints) {
		this.payPoints = payPoints;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	

}
