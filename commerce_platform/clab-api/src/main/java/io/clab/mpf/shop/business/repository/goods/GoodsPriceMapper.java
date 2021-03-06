package io.clab.mpf.shop.business.repository.goods;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.business.entity.goods.GoodsPrice;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;

public interface GoodsPriceMapper extends IBusBaseMapper<GoodsPrice> , BaseMapper<GoodsPrice>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer priceId);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    int insertSelective(GoodsPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    GoodsPrice selectByPrimaryKey(Integer priceId);

    
    GoodsPrice selectByGoodsId(Integer GoodsId);
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoodsPrice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_price
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoodsPrice record);
}