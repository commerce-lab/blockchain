package io.clab.mpf.shop.entity.shoppingcart;

import java.util.List;

import io.clab.mpf.shop.entity.user.User;

/**
 * 购物车Vo
 *
 */
public class ShoppingCartVo {
	private User user; // 所属用户
	private int goodsNum; // 购物车商品数，其中不同规格的商品，算另外的商品
	private int needPayAmount; // 需要支付的金额
	private int discoiuntAmount; // 优惠的金额
	private int totalAmount; // 总金额
	private List<ShoppingCartItem> shoppingCartItem;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public int getNeedPayAmount() {
		return needPayAmount;
	}

	public void setNeedPayAmount(int needPayAmount) {
		this.needPayAmount = needPayAmount;
	}

	public int getDiscoiuntAmount() {
		return discoiuntAmount;
	}

	public void setDiscoiuntAmount(int discoiuntAmount) {
		this.discoiuntAmount = discoiuntAmount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ShoppingCartItem> getShoppingCartItem() {
		return shoppingCartItem;
	}

	public void setShoppingCartItem(List<ShoppingCartItem> shoppingCartItem) {
		this.shoppingCartItem = shoppingCartItem;
	}

}
