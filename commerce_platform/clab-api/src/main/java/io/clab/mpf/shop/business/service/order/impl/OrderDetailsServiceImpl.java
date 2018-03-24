package io.clab.mpf.shop.business.service.order.impl;

import io.clab.mpf.shop.business.entity.order.OrderDetails;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.order.OrderDetailsMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.order.IOrderDetailsService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl extends BaseServiceImpl<OrderDetails> implements IOrderDetailsService{
	
	@Resource
	private OrderDetailsMapper orderDetailsMapper;
	
	@Override
	protected IBusBaseMapper<OrderDetails> getMapper() {
		return orderDetailsMapper;
	}


}
