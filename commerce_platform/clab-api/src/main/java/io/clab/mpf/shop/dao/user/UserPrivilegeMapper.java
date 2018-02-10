package io.clab.mpf.shop.dao.user;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.user.UserPrivilege;

/**
 * 用户优惠
 *
 */
public interface UserPrivilegeMapper extends IBaseDao<UserPrivilege> {
	/**
	 * 查询用户优惠
	 * 
	 * @param userId 用户id
	 * @return
	 */
	UserPrivilege selectByUserId(int userId);
	
	UserPrivilege selectAllByUserId(int userId);
}
