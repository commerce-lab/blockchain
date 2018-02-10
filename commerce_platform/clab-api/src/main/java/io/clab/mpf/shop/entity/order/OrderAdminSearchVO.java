package io.clab.mpf.shop.entity.order;

import java.util.Map;

/**
 *
 * 管理平台商品订单搜索对象
 */
public class OrderAdminSearchVO {
	/**
	 * 全选
	 */
	private String checkAll;
	/**
	 * 订单号
	 */
	private String orderNumber;
	
	/**
	 * 结束下单时间
	 */	
	private String createTime_le;
	
	/**
	 * 起始下单时间
	 */
	private String createTime_ge;
	
	/**
	 * 会员手机user.phone
	 */
	private String phone;
	
	/**
	 * 是否开发票
	 */
	private String invoiceTag; 
	
	/**
	 * 付款方式 payment.paymentId
	 */
	private String paymentId;
	
	/**
	 * 支付状态   payment.payState
	 */
	private String payState;
	
	/**
	 * 配送状态
	 */
	private String logisticsState;
	
	/**
	 * 需要导出的id拼成的语句
	 */
	private Map<Integer,Integer> selectIdList;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCreateTime_le() {
		return createTime_le;
	}

	public void setCreateTime_le(String createTime_le) {
		this.createTime_le = createTime_le;
	}

	public String getCreateTime_ge() {
		return createTime_ge;
	}

	public void setCreateTime_ge(String createTime_ge) {
		this.createTime_ge = createTime_ge;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInvoiceTag() {
		return invoiceTag;
	}

	public void setInvoiceTag(String invoiceTag) {
		this.invoiceTag = invoiceTag;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getLogisticsState() {
		return logisticsState;
	}

	public void setLogisticsState(String logisticsState) {
		this.logisticsState = logisticsState;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public Map<Integer, Integer> getSelectIdList() {
		return selectIdList;
	}

	public void setSelectIdList(Map<Integer, Integer> selectIdList) {
		this.selectIdList = selectIdList;
	}

	

	
}