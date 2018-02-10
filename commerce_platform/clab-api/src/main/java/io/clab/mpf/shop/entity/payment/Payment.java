package io.clab.mpf.shop.entity.payment;

import java.util.Date;

/**
 * 付款方式
 */
public class Payment {
	private Integer paymentId;
	private String name; // 付款方式名称
	private String description; // 描述
	private Byte isGeneral; // 是否通用
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	private Byte state; // 状态 ：1 已删除 2 未删除

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getIsGeneral() {
		return isGeneral;
	}

	public void setIsGeneral(Byte isGeneral) {
		this.isGeneral = isGeneral;
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
}
