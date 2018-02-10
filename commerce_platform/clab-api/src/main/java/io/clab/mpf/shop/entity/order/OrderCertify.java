package io.clab.mpf.shop.entity.order;

import java.util.Date;

import io.clab.mpf.shop.entity.admin.Admin;

/**
 * 订单图片凭证DO类
 */
public class OrderCertify {
	private Integer orderCertifyId;
	private Integer orderId;
	private String orderNumber;
	private String imageUrl;
	private Date createTime;
	private Date updateTime;
	private Admin admin;

	public Integer getOrderCertifyId() {
		return orderCertifyId;
	}

	public void setOrderCertifyId(Integer orderCertifyId) {
		this.orderCertifyId = orderCertifyId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


}
