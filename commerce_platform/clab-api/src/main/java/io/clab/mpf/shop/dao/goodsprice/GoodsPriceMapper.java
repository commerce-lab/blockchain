package io.clab.mpf.shop.dao.goodsprice;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.goodsprice.GoodsPrice;

/**
 *
 * 商品规格价格dao
 */
public interface GoodsPriceMapper extends IBaseDao<GoodsPrice>{
	/**
	 * 依据价格id及状态查询相关记录
	 * @param priceId 价格id
	 * @param State 记录状态
	 * @return
	 */
	public GoodsPrice findGoodsPriceByPriceIdAndState(int priceId,Byte state);
	/**
	 * 根据商品id，查询所有规格（去除不包含的状态）
	 * @param goodsId 商品id
	 * @param notState 不包含的状态
	 * @return
	 */
	public List<GoodsPrice> findAllByGoodsIdAndNotState(int goodsId,Byte notState);
}
