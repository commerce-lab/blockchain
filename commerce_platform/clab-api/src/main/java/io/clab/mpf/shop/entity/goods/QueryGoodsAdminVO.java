package io.clab.mpf.shop.entity.goods;


public class QueryGoodsAdminVO {
	/**
	 * 对应商品种类表的id,查询该分类及子分类的所有商品
	 */
	private String categoryId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 上架标志 1 已上架 0 未上架
	 */
	private String isMarketable;
	/**
	 * 创建时间_小于等于,即创建时间上限
	 */
	private String createTime_le;

	/**
	 * 创建时间_大于等于，即创建时间下限
	 */
	private String createTime_ge;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(String isMarketable) {
		this.isMarketable = isMarketable;
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