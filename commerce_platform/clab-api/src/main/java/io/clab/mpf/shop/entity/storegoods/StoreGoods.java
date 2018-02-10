package io.clab.mpf.shop.entity.storegoods;

import java.util.Date;

/**
 * 用户收藏表实体
 *
 */
public class StoreGoods {
	/**
	 * 收藏id 主键，自增1
	 */
	private int storeId;
	/**
	 * 对应用户表的id
	 */
	private int userId;
	/**
	 * 对应商品id
	 */
	private int goodsId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 不表示状态,暂时不用
	 */
	private int state;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
