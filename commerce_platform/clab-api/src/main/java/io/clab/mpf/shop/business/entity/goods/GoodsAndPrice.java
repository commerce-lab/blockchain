package io.clab.mpf.shop.business.entity.goods;

import io.swagger.annotations.ApiModel;

@ApiModel(value="商品全部信息")
public class GoodsAndPrice {

	private Goods goods;
	
	private GoodsPrice goodsPrice;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsPrice getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(GoodsPrice goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
}
