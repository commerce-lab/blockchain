package io.clab.mpf.shop.entity.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserPayment {
	private Integer userPaymentId;
	private Integer userId;
	private Integer paymentId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private Integer adminId;

	public Integer getUserPaymentId() {
		return userPaymentId;
	}

	public void setUserPaymentId(Integer userPaymentId) {
		this.userPaymentId = userPaymentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
}
