package io.clab.mpf.shop.consumer.service.user;

import io.clab.mpf.shop.consumer.entity.user.User;

/**
 * 用户信息操作
 * ***/
public interface IUserInfoService {
	User get(Long userId);
	
	boolean update(User user);
}
