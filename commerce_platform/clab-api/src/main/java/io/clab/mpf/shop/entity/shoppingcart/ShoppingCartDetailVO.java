package io.clab.mpf.shop.entity.shoppingcart;

/**
 *
 * 用于勾选购物车条目时使用
 *
 */
public class ShoppingCartDetailVO {
	/*
	 * select c.goods_id goods_id,c.goods_name goods_name,c.image image,
	 * b.price_id price_id,b.unit_name unit_name,b.buy_price
	 * buy_price,b.wholesale_price wholesale_price, a.cart_id cart_id,a.quantity
	 * quantity,a.is_buy is_buy from sxsc.t_cart as a join sxsc.t_goods_price as
	 * b on a.price_id= b.price_id join sxsc.t_goods as c on b.goods_id=
	 * c.goods_id where a.user_id='1'
	 */
	private int goodsId;// c.goods_id goods_id,
	private String goodsName;// c.goods_name goods_name,
	private String image;// c.image image,
	private int priceId;// b.price_id price_id,
	private String uniteName;// b.unit_name unit_name,
	private int retailPrice;// b.retail_price retail_price,
	private int buyPrice;// b.buy_price buy_price,
	private int wholesalePrice;// b.wholesale_price wholesale_price,
	private int cartId;// a.cart_id cart_id,
	private int quantity;// a.quantity quantity,
	private int isBuy;// a.is_buy is_buy

	
	public int getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(int retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public String getUniteName() {
		return uniteName;
	}

	public void setUniteName(String uniteName) {
		this.uniteName = uniteName;
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

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(int isBuy) {
		this.isBuy = isBuy;
	}

}
