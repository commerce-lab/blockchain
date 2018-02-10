package io.clab.mpf.shop.business.service.merchants.impl;


import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.merchants.MerchantsMapper;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.merchants.IMerchantsService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MerchantsServiceImpl extends BaseServiceImpl<Merchants> implements IMerchantsService {

	@Resource
	private MerchantsMapper merchantsMapper;

	@Override
	protected IBusBaseMapper<Merchants> getMapper() {
		return merchantsMapper;
	}

	/**
	 * 登陆
	 * */
	public Merchants longin(Merchants merchants){
		return merchantsMapper.longin(merchants);
	}
	
}
