package io.clab.mpf.shop.business.service.goods.impl;

import io.clab.mpf.shop.business.entity.goods.GoodsCategory;
import io.clab.mpf.shop.business.repository.goods.GoodsCategoryMapper;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.service.goods.IGoodsCategoryService;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class GoodsCategoryServiceImpl extends BaseServiceImpl<GoodsCategory> implements IGoodsCategoryService{

	@Resource
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Override
	protected IBusBaseMapper<GoodsCategory> getMapper() {
		return goodsCategoryMapper;
	}

}
