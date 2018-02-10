package io.clab.mpf.shop.business.repository.merchants;

import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface MerchantsMapper extends IBusBaseMapper<Merchants> , BaseMapper<Merchants>{

	
	/**
	 * 登陆
	 * */
	public Merchants longin(Merchants merchants);
}