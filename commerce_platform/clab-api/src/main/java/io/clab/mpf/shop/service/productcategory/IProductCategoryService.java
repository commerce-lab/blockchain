package io.clab.mpf.shop.service.productcategory;

import java.util.List;

import io.clab.mpf.shop.entity.productcategory.ProductCategory;
import io.clab.mpf.shop.service.base.IBaseService;

public interface IProductCategoryService extends IBaseService<ProductCategory> {
	/**
	 * 通过商品种类id，查询所有一级子种类，不包含删除状态
	 * 
	 * @param categoryId
	 *            商品种类id
	 * @return 一级子商品种类列表
	 */
	public List<ProductCategory> getAllChildCategoryByCategoryId(int categoryId);
	
	/**
	 * 通过商品种类id，查询所有一级子种类，只包含正常开启的子类
	 * 
	 * @param categoryId
	 *            商品种类id
	 * @return 一级子商品种类列表
	 */
	public List<ProductCategory> getAllNormalChildCategoryByCategoryId(int categoryId);

	/**
	 * 通过商品种类id，查询该商品种类的父级种类
	 * 
	 * @param categoryId
	 *            商品种类id
	 * @return
	 */
	public ProductCategory getParentCategoryByCategoryId(int categoryId);

	/**
	 * 获取所有有效的一级商品分类，不包含已删除的一级分类
	 * 
	 * @return
	 */
	public List<ProductCategory> getAllFirstLevelCategory();
	/**
	 * 获取所有有效的一级商品分类，只包含已开启的一级子分类
	 * 
	 * @return
	 */
	public List<ProductCategory> getAllNormalFirstLevelCategory();

	/**
	 * 获取首页的一级商品分类，按照更新时间倒序取前9个推荐的一级分类
	 * 
	 * @return
	 */
	public List<ProductCategory> getHomePageCategory();
}
