package io.clab.mpf.shop.dao.payment;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.payment.Payment;

/**
 * 付款方式
 */
public interface PaymentMapper extends IBaseDao<Payment> {
	/**根据用户id查询用户的付款方式
	 * @param userId 用户id
	 * @return
	 */
	List<Payment> queryUserPayment(int userId);
	/**
	 * 查询不通用的付款方式
	 * @return
	 */
	List<Payment> queryPayment();
}
