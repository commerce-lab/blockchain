package io.clab.mpf.shop.dao.order;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.order.OrderDetails;
import io.clab.mpf.shop.entity.order.OrderDetailsAdminSearchVO;

public interface OrderDetailsMapper extends IBaseDao<OrderDetails> {
	public Integer insertBatch(List<OrderDetails> list);
	
	/**
	 * 依据商品订单查询类，查询商品订单详细列表
	 * @param entity
	 * @return
	 */
	public List<OrderDetails> getPageByOrderDetailsAdminSearchVO(OrderDetailsAdminSearchVO entity);
	
	
	/**
	 * 依据商品订单查询类，查询商品订单详细报表
	 * @param entity
	 * @return
	 */
	public List<OrderDetails> getDetailReportByOrderDetailsAdminSearchVO(OrderDetailsAdminSearchVO entity);
	
	/**
	 * 依据商品订单查询类，获取商品销售总额
	 * @param entity
	 * @return
	 */
	public long getTotalDetailsAmountByOrderDetailsAdminSearchVO(OrderDetailsAdminSearchVO entity);
	
	/**
	 * 依据商品订单查询类，获取商品销售总数
	 * @param entity
	 * @return
	 */
	public long getTotalNumAmountByOrderDetailsAdminSearchVO(OrderDetailsAdminSearchVO entity);

}