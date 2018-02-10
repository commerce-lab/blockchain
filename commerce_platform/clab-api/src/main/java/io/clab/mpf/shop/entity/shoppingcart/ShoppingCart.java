package io.clab.mpf.shop.entity.shoppingcart;

import java.util.Date;

/**
 * 购物车条目实体类
 */
public class ShoppingCart {
	/**
	 * 购物车条目id
	 */
	private int cartId;
	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 商品规格价格id
	 */
	private int priceId;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 购买标志： 1购买 2不购买
	 */
	private int isBuy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(int isBuy) {
		this.isBuy = isBuy;
	}

}