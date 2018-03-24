package io.clab.mpf.shop.business.service.order;


import java.util.Date;

import io.clab.mpf.shop.business.entity.order.Order;
import io.clab.mpf.shop.business.service.base.IBaseService;

import com.baomidou.mybatisplus.plugins.Page;

public interface IOrderService extends IBaseService<Order>{

	public Page<Order> selectPageList(int pageNow, int pageSize,Order order,Date startCreateTime,Date endCreateTime);
}
