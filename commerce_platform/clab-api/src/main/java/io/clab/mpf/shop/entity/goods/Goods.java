package io.clab.mpf.shop.entity.goods;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_goods")
public class Goods {
	/**
	 * 主键，自增1
	 */
	@TableId("goods_id")
	private int goodsId;
	/**
	 * 对应商品种类表的id
	 */
	private int categoryId;
	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 别名
	 */
	@TableField("nickname")
	private String nickName;
	/**
	 * 商品主图
	 */
	private String image;
	/**
	 * 状态 ：1.未删除 2.已删除
	 */
	private Byte delState;

	/**
	 * 简要描述
	 */
	private String simpleDescribe;

	/**
	 * 详细描述
	 */
	private String detailDescribe;

	/**
	 * 上架标志 1 已上架 0 未上架
	 */
	private int isMarketable;
	/**
	 * 推荐 1是 2否 (保留，暂未使用)
	 */
	private Byte recommend;

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

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Byte getDelState() {
		return delState;
	}

	public void setDelState(Byte delState) {
		this.delState = delState;
	}

	public String getSimpleDescribe() {
		return simpleDescribe;
	}

	public void setSimpleDescribe(String simpleDescribe) {
		this.simpleDescribe = simpleDescribe;
	}

	public String getDetailDescribe() {
		return detailDescribe;
	}

	public void setDetailDescribe(String detailDescribe) {
		this.detailDescribe = detailDescribe;
	}

	public int getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(int isMarketable) {
		this.isMarketable = isMarketable;
	}

	public Byte getRecommend() {
		return recommend;
	}

	public void setRecommend(Byte recommend) {
		this.recommend = recommend;
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