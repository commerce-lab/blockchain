package io.clab.mpf.shop.entity.productcategory;

import java.util.Date;

public class ProductCategory {
	/**
	 * 商品种类id
	 */
	private int categoryId;
	/**
	 * 父种类id
	 */
	private int parentId;
	/**
	 * 商品种类名称
	 */
	private String categoryName;
	/**
	 * 商品种类主图
	 */
	private String image;
	/**
	 * 状态:1.启用 2.未启用 3.删除
	 */
	private int state;
	/**
	 * 简要描述
	 */
	private String simpleDescribe;
	/**
	 * 推荐标志 :1是 2否
	 */
	private int recommend;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSimpleDescribe() {
		return simpleDescribe;
	}

	public void setSimpleDescribe(String simpleDescribe) {
		this.simpleDescribe = simpleDescribe;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
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