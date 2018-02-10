package io.clab.mpf.shop.entity.user;

import java.util.Date;

/**
 * 高级支付方式Vo类
 */
public class HigherPaymentVo {
	private Integer paymentId;
	private String name; // 付款方式名称
	private String description; // 描述
	private Byte isOwnered; // 是否拥有 1 拥有 2 不拥有
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

	public Byte getIsOwnered() {
		return isOwnered;
	}

	public void setIsOwnered(Byte isOwnered) {
		this.isOwnered = isOwnered;
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
