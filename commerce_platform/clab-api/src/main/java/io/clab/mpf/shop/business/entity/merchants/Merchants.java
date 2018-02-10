package io.clab.mpf.shop.business.entity.merchants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value="商家信息")
public class Merchants implements Serializable{

	@ApiModelProperty("自增长")
	private Long merchantsId;
	
	@ApiModelProperty("商家名称")
	private String merchantsName;
	
	@ApiModelProperty("商家类型（1：品牌商，2：广告商，3：零售商）")
	private Integer merchantsType;
	
	@ApiModelProperty("联系人姓名")
	private String contactName;
	
	@ApiModelProperty("联系人手机号码")
	private String contactPhone;
	
	@ApiModelProperty("联系人邮箱")
	private String contactEmail;
	
	@ApiModelProperty("密码")
	private String password;
	
	@ApiModelProperty("随机码")
	private String rand ;
	
	@ApiModelProperty("门店所在大区")
	private Integer storeArea;
	
	@ApiModelProperty("门店所在省")
	private Integer storeProvince;
	
	@ApiModelProperty("门店所在城市")
	private Integer storeCity;
	
	@ApiModelProperty("门店所在县/区")
	private Integer storeCounty;
	
	@ApiModelProperty("门店所在详细地址")
	private String storeAdress;
	
	@ApiModelProperty("经营范围(多个用逗号隔开)")
	private String busScope;
	
	@ApiModelProperty("简介")
	private String description;
	
	@ApiModelProperty("审核状态（0：审核中，1：审核通过，2：审核不通过）")
	private Integer state = 0;
	
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public Long getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Long merchantsId) {
		this.merchantsId = merchantsId;
	}

	public String getMerchantsName() {
		return merchantsName;
	}

	public void setMerchantsName(String merchantsName) {
		this.merchantsName = merchantsName;
	}

	public Integer getMerchantsType() {
		return merchantsType;
	}

	public void setMerchantsType(Integer merchantsType) {
		this.merchantsType = merchantsType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}



	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStoreArea() {
		return storeArea;
	}

	public void setStoreArea(Integer storeArea) {
		this.storeArea = storeArea;
	}

	public Integer getStoreProvince() {
		return storeProvince;
	}

	public void setStoreProvince(Integer storeProvince) {
		this.storeProvince = storeProvince;
	}

	public Integer getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(Integer storeCity) {
		this.storeCity = storeCity;
	}

	public Integer getStoreCounty() {
		return storeCounty;
	}

	public void setStoreCounty(Integer storeCounty) {
		this.storeCounty = storeCounty;
	}

	public String getStoreAdress() {
		return storeAdress;
	}

	public void setStoreAdress(String storeAdress) {
		this.storeAdress = storeAdress;
	}

	public String getBusScope() {
		return busScope;
	}

	public void setBusScope(String busScope) {
		this.busScope = busScope;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}



}
