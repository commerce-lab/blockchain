package io.clab.mpf.shop.consumer.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.consumer.entity.user.User;
import io.clab.mpf.shop.consumer.repository.user.UserMapper;
import io.clab.mpf.shop.consumer.service.user.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public User get(Long userId) {
		User user = userMapper.selectById(userId);
		return user;
	}
	@Override
	public boolean update(User user) {
		return userMapper.updateById(user) == 1;
	}

}
