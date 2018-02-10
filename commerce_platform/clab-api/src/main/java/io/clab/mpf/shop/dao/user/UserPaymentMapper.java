package io.clab.mpf.shop.dao.user;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.user.UserPayment;

/**
 * 用户优惠
 */
public interface UserPaymentMapper extends IBaseDao<UserPayment> {
	List<UserPayment> selectByUserId(int userId);
}
