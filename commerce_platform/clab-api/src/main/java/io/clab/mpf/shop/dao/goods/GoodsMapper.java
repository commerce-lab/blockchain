package io.clab.mpf.shop.dao.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.goods.Goods;
import io.clab.mpf.shop.entity.goods.QueryGoodsAdminVO;

public interface GoodsMapper extends IBaseDao<Goods> {
	public List<Goods> getAllSelectGoods(@Param("goodsIds")String goodsIds);
	/**
	 * 依据商品种类，获取所有有效商品，供前台调用,支持分页查询
	 * @param entity
	 * @return
	 */
	public List<Goods> getPageFrontByGoodsCategory(Goods entity);
	
	/**
	 * 依据商品名称模糊查询，获取分页查询结果，供前端调用，去掉了下架的商品
	 * @param entity
	 * @return
	 */
	public List<Goods> getPageFrontByGoodsName(Goods entity);
	
	/**
	 * 依据用户id查询收藏的商品，获取分页查询结果，供前端调用，去掉了下架的商品
	* @param userId 当前用户id
	 * @return
	 */
	public List<Goods> getPageFrontByMyStoreGoods(int  userId);
	
	/**
	 * 依据商品查询类，查询商品列表，不含删除状态
	 * @param entity
	 * @return
	 */
	public List<Goods> getPageByQueryGoodsAdminVO(QueryGoodsAdminVO entity);
	
	/**
	 * 分页获取推荐的上架商品，依据更新时间倒序取商品,不含删除状态，供前端调用
	 * @return
	 */
	public List<Goods> getPageFrontRecommendAndIsMarketableGoods();
	
	/**
	 * mybatis plus测试
	 * @return
	 */
	List<Goods> selectGoodList(Pagination page);
}