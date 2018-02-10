package io.clab.mpf.shop.business.service.merchants.impl;


import io.clab.mpf.shop.business.entity.merchants.MerchantsCategory;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.merchants.MerchantsCategoryMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.merchants.IMerchantsCategoryService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MerchantsCategoryServiceImpl extends BaseServiceImpl<MerchantsCategory> implements IMerchantsCategoryService {

	@Resource
	private MerchantsCategoryMapper merchantsCategoryMapper;

	@Override
	protected IBusBaseMapper<MerchantsCategory> getMapper() {
		return merchantsCategoryMapper;
	}


	
}
