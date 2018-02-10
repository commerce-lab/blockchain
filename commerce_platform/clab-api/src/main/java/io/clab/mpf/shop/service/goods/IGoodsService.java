package io.clab.mpf.shop.service.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.clab.mpf.shop.entity.goods.Goods;
import io.clab.mpf.shop.entity.goods.QueryGoodsAdminVO;
import io.clab.mpf.shop.service.base.IBaseService;
import util.PageResult;

public interface IGoodsService extends IBaseService<Goods> {
	public List<Goods> getAllSelectGoods(String goodsIds);
	/**
	 * 依据对象除goodsid外的所有属性作为查询条件
	 */
	public  Goods getOne(Goods goods);
	
	/**
	 * 依据商品种类，获取所有有效商品，供前台调用
	 * @param entity
	 * @return
	 */
	public List<Goods> getPageFrontByGoodsCategory(Goods entity);
	
	/**
	 * 依据商品分类id，获取分页查询结果，供前端调用，去掉了下架的商品
	 * @param t
	 * @param entity
	 * @return
	 */
	public PageResult<Goods> getPageFrontByGoodsCategory(@Param("t")PageResult<Goods> t,@Param("entity")Goods entity);
	
	/**
	 * 依据商品名称模糊查询，获取分页查询结果，供前端调用，去掉了下架的商品
	 * @param t
	 * @param entity
	 * @return
	 */
	public PageResult<Goods> getPageFrontByGoodsName(@Param("t")PageResult<Goods> t,@Param("entity")Goods entity);
	
	
	/**
	 * 依据用户id查询收藏的商品，获取分页查询结果，供前端调用，去掉了下架的商品
	 * @param t
	 * @param userId 当前用户id
	 * @return
	 */
	public PageResult<Goods> getPageFrontByMyStoreGoods(@Param("t")PageResult<Goods> t,@Param("userId")int  userId);
	
	
	
	/**
	 * 依据商品查询类，查询商品列表，不含删除状态
	 * @param entity
	 * @return
	 */
	public PageResult<Goods> getPageByQueryGoodsAdminVO(@Param("t")PageResult<Goods> t,@Param("entity")QueryGoodsAdminVO entity);
	
	/**
	 * 分页获取推荐的上架商品，依据更新时间倒序取商品,不含删除状态，供前端调用
	 * @param t
	 * @return
	 */
	public PageResult<Goods> getPageFrontRecommendAndIsMarketableGoods(@Param("t")PageResult<Goods> t);
	
	public Goods selectOneGood(Integer goodId);
	
}
