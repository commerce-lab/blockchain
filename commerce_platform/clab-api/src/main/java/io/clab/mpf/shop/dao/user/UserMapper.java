package io.clab.mpf.shop.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserVo;

public interface UserMapper extends IBaseDao<User>{
	public User getOneByOpenid(String openid);
	public User getOneByPhone(String phone);
	public int updatePhone(@Param("phone")String phone,@Param("openid")String openid);
	public int getCountByPhone(String phone);
	
	String getAllFrontUser();
	
	List<User> getUsersByPage(String userIds);
	List<User> queryUserByPage(UserVo entity);
}