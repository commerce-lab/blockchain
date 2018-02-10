package io.clab.mpf.shop.entity.user;

import java.util.Date;

/**
 * 用户优惠类
 */
public class UserPrivilege {
	private Integer privilegeId;
	private Integer userId;
	private Byte isWholeSalePrice;
	private Byte isDiscount;
	private Integer discount;
	private Date createTime;
	private Date updateTime;
	private Byte state;
	private Integer adminId;

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getIsWholeSalePrice() {
		return isWholeSalePrice;
	}

	public void setIsWholeSalePrice(Byte isWholeSalePrice) {
		this.isWholeSalePrice = isWholeSalePrice;
	}

	public Byte getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Byte isDiscount) {
		this.isDiscount = isDiscount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
