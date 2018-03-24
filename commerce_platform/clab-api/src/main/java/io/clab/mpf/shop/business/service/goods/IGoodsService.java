package io.clab.mpf.shop.business.service.goods;

import io.clab.mpf.shop.business.entity.goods.Goods;
import io.clab.mpf.shop.business.entity.goods.GoodsDetail;
import io.clab.mpf.shop.business.service.base.IBaseService;

import com.baomidou.mybatisplus.plugins.Page;

public interface IGoodsService extends IBaseService<Goods>{

	public Page<GoodsDetail> selectPageList(int pageNow, int pageSize,Goods goods,Long startStrikePrice,Long endStrikePrice);
}
