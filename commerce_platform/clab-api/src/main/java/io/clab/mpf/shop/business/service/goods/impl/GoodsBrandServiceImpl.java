package io.clab.mpf.shop.business.service.goods.impl;

import io.clab.mpf.shop.business.entity.goods.GoodsBrand;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.goods.GoodsBrandMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.goods.IGoodsBrandService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class GoodsBrandServiceImpl extends BaseServiceImpl<GoodsBrand> implements IGoodsBrandService{

	@Resource
	private GoodsBrandMapper goodsBrandMapper;
	
	@Override
	protected IBusBaseMapper<GoodsBrand> getMapper() {
		return goodsBrandMapper;
	}

}
