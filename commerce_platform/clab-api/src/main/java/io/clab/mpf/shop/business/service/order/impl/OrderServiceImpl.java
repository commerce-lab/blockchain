package io.clab.mpf.shop.business.service.order.impl;

import io.clab.mpf.shop.business.entity.order.Order;
import io.clab.mpf.shop.business.entity.order.OrderDetails;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.order.OrderDetailsMapper;
import io.clab.mpf.shop.business.repository.order.OrderMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.order.IOrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements IOrderService{

	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private OrderDetailsMapper orderDetailsMapper;
	
	@Override
	protected IBusBaseMapper<Order> getMapper() {
		return orderMapper;
	}

	
	public Page<Order> selectPageList(int pageNow, int pageSize,Order order,Date startCreateTime,Date endCreateTime){
		Page<Order> page = new Page<Order>(pageNow,pageSize == 0?10:pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantsId", order.getMerchantsId());
		paramMap.put("orderNumber", order.getOrderNumber());
		paramMap.put("startCreateTime", startCreateTime);
		paramMap.put("endCreateTime", endCreateTime);
		paramMap.put("orderState", order.getOrderState());
		paramMap.put("orderType", order.getOrderType());

		List<Order> list = orderMapper.selectPageList(page,paramMap);
		if(list != null && list.size() > 0){
			for(int i = 0;i<list.size() ; i++){
				List<OrderDetails> orderDetails = orderDetailsMapper.selectByOrderId(list.get(i).getOrderId());
				list.get(i).setOrderDetails(orderDetails);
			}
		}
		
		page.setRecords(list);
		
		return page;
	}
}
