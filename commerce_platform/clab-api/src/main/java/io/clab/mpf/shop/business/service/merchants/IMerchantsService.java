package io.clab.mpf.shop.business.service.merchants;

import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.base.IBaseService;

public interface IMerchantsService extends IBaseService<Merchants>{

	/**
	 * 登陆
	 * */
	public Merchants longin(Merchants merchants);
}
