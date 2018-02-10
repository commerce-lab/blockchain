package io.clab.mpf.shop.entity.order;

/**
 *
 * 商品统计查询条件对象
 *
 */
public class OrderDetailsAdminSearchVO {
	/**
	 * 搜索商品名称
	 */
	private String goodsName;

	/**
	 * 搜索商品订单金额,即商品金额上限
	 */
	private String detailsAmount_le;

	/**
	 * 搜索商品订单金额,即商品金额下限
	 */
	private String detailsAmount_ge;

	/**
	 * 搜索商品销量上限值
	 */
	private String num_le;

	/**
	 * 搜索商品销量下限值
	 */
	private String num_ge;
	/**
	 * 搜索商品订单时间上限值
	 */
	private String createTime_le;

	/**
	 * 搜索商品订单时间下限值
	 */
	private String createTime_ge;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getDetailsAmount_le() {
		return detailsAmount_le;
	}

	public void setDetailsAmount_le(String detailsAmount_le) {
		this.detailsAmount_le = detailsAmount_le;
	}

	public String getDetailsAmount_ge() {
		return detailsAmount_ge;
	}

	public void setDetailsAmount_ge(String detailsAmount_ge) {
		this.detailsAmount_ge = detailsAmount_ge;
	}

	public String getNum_le() {
		return num_le;
	}

	public void setNum_le(String num_le) {
		this.num_le = num_le;
	}

	public String getNum_ge() {
		return num_ge;
	}

	public void setNum_ge(String num_ge) {
		this.num_ge = num_ge;
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

}