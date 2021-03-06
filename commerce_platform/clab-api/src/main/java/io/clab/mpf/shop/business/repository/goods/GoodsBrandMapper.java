package io.clab.mpf.shop.business.repository.goods;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.business.entity.goods.GoodsBrand;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;

public interface GoodsBrandMapper extends IBusBaseMapper<GoodsBrand> , BaseMapper<GoodsBrand>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_brand
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer brandId);



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_brand
     *
     * @mbggenerated
     */
    int insertSelective(GoodsBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_brand
     *
     * @mbggenerated
     */
    GoodsBrand selectByPrimaryKey(Integer brandId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_brand
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoodsBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods_brand
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoodsBrand record);
}