package io.clab.mpf.shop.business.service.goods.impl;

import io.clab.mpf.shop.business.entity.goods.GoodsPrice;
import io.clab.mpf.shop.business.repository.goods.GoodsPriceMapper;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.service.goods.IGoodsPriceService;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class GoodsPriceServiceImpl extends BaseServiceImpl<GoodsPrice> implements IGoodsPriceService{

	@Resource
	private GoodsPriceMapper goodsPriceMapper;
	
	@Override
	protected IBusBaseMapper<GoodsPrice> getMapper() {
		return goodsPriceMapper;
	}

	@Override
	public GoodsPrice selectByGoodsId(Integer GoodsId) {
		return goodsPriceMapper.selectByGoodsId(GoodsId);
	}

}
