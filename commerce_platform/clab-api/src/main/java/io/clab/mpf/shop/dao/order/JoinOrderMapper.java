package io.clab.mpf.shop.dao.order;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.order.JoinOrder;

public interface JoinOrderMapper extends IBaseDao<JoinOrder> {
	JoinOrder selectByJoinOrderNumber(JoinOrder joinOrder);
}