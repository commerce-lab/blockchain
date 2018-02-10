package io.clab.mpf.shop.entity.shoppingcart;

import java.util.Date;

import io.clab.mpf.shop.entity.goods.Goods;
import io.clab.mpf.shop.entity.goodsprice.GoodsPrice;

/**
 * 购物车单项Vo
 */
public class ShoppingCartItem {
	/**
	 * 购物车条目id
	 */
	private Integer cartId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 商品规格价格id
	 */
	private GoodsPrice goodsPrice;
	/**
	 * 商品id
	 */
	private Goods goods;
	/**
	 * 数量
	 */
	private Integer quantity;
	/**
	 * 购买标志： 1购买 2不购买
	 */
	private Byte isBuy;

	private int totalAmount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public GoodsPrice getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(GoodsPrice goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Byte getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Byte isBuy) {
		this.isBuy = isBuy;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
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

}
