package io.clab.mpf.shop.entity.goodsprice;

import java.util.Date;

/**
 * 商品规格价格表实体类
 *
 */
public class GoodsPrice {
	/**
	 * 商品规格价格id
	 */
	private int priceId;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 计量单位名称
	 */
	private String unitName;
	/**
	 * 零售价
	 */
	private int retailPrice;
	/**
	 * 采购价
	 */
	private int buyPrice;
	/**
	 * 批发价
	 */
	private int wholesalePrice;
	/**
	 * 状态：1.启用 2.未启用
	 */
	private Byte state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 创建者id
	 */
	private int adminId;

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(int wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}
