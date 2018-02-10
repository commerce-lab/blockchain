package io.clab.mpf.shop.entity.order;

/**
 * 订单合并实体
 * 
 */
public class JoinOrder {
	private int joinOrderId;
	private String joinOrderNumber;
	private String orderIds;
	private String orderNumbers;
	private Integer totalPrice;

	public int getJoinOrderId() {
		return joinOrderId;
	}

	public void setJoinOrderId(int joinOrderId) {
		this.joinOrderId = joinOrderId;
	}

	public String getJoinOrderNumber() {
		return joinOrderNumber;
	}

	public void setJoinOrderNumber(String joinOrderNumber) {
		this.joinOrderNumber = joinOrderNumber;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
