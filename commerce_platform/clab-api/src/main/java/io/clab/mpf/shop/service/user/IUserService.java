package io.clab.mpf.shop.service.user;

import java.util.List;

import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserPayment;
import io.clab.mpf.shop.entity.user.UserPrivilege;
import io.clab.mpf.shop.entity.user.UserVo;
import io.clab.mpf.shop.service.base.IBaseService;
import util.PageResult;

public interface IUserService extends IBaseService<User> {
	public User getOneByOpenid(String openid);

	public User getOneByPhone(String phone);

	public int updatePhone(String tel, String openid);

	public int getCountByPhone(String phone);

	void grantPrivilege(UserPrivilege userPrivilege, List<UserPayment> userPayments) throws Exception;

	String getAllFrontUser();

	PageResult<User> getUsersByUserIds(PageResult<User> page, String userIds);

	PageResult<User> queryUserByPage(PageResult<User> page, UserVo userVo);

}
