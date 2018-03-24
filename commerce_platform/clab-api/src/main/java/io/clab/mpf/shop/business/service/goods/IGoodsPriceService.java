package io.clab.mpf.shop.business.service.goods;

import io.clab.mpf.shop.business.entity.goods.GoodsPrice;
import io.clab.mpf.shop.business.service.base.IBaseService;

public interface IGoodsPriceService extends IBaseService<GoodsPrice>{

	GoodsPrice selectByGoodsId(Integer GoodsId);
}
