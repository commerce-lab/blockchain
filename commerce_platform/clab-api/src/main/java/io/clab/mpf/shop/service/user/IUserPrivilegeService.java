package io.clab.mpf.shop.service.user;


import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserPrivilege;
import io.clab.mpf.shop.service.base.IBaseService;

/**
 * 用户优惠接口定义
 */
public interface IUserPrivilegeService extends IBaseService<UserPrivilege> {
	/**
	 * 查询用户优惠
	 * 
	 * @param user 用户
	 * @return
	 */
	UserPrivilege queryUserPrivilege(User user);
	
	/**
	 * 获取用户是否享受批发价和折扣数（可使用批发价参数和折扣参数进行价格计算，其他参数不保证有值）
	 * `is_wholeSalePrice` tinyint(4) DEFAULT '0' COMMENT '享受批发价标志 1 享受批发价 0 不享受批发价',
	 *`discount` int(11) DEFAULT NULL COMMENT '商品折扣 使用整数表示折扣数，比如98，表示98折',
	 * @param user
	 * @return
	 */
	UserPrivilege getUserWholeSalePriceAndDiscountPrivate(User user);
}
