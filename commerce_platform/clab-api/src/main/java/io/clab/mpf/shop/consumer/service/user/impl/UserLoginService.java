/**
 * 
 */
package io.clab.mpf.shop.consumer.service.user.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.consumer.entity.user.User;
import io.clab.mpf.shop.consumer.repository.user.UserMapper;
import io.clab.mpf.shop.consumer.service.token.ITokenService;
import io.clab.mpf.shop.consumer.service.user.IUserLoginService;
import io.clab.mpf.shop.util.UserIdWroker;
import io.clab.mpf.shop.util.encryp.MD5Util;

/**
 * @author iceray
 *
 */
@Service
public class UserLoginService implements IUserLoginService {

	private static Logger logger = Logger.getLogger(UserLoginService.class);
	
	private static final Integer NOT_ACTIVITE = 0;
	
	private static final Integer ACTIVITE = 1;
	
	@Autowired
	private ITokenService tokenService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String getToken(String mobile, String authCode) {
		
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(authCode)) {
			return "";
		}
		
		//TODO:接口还没好，目前直接返回token
		/*String verifyCode = SmsAuthCodeUtils.getAututhCode(mobile);
		if(StringUtils.isEmpty(verifyCode) || !verifyCode.equals(authCode)) {
			return "";
		}*/
		
		return tokenService.getTokenByMobile(mobile);
	}

	@Override
	public Boolean register(String mobile, String password, String authCode) {
		// 上层接口做校验
		//TODO:校验验证码,短信接口未完善，先跳过
		//1.查询是否存在待激活的用户信息
		try {
			User user = new User();
			user.setMobile(mobile);
			user.setState(NOT_ACTIVITE);
			
			user = this.userMapper.selectOne(user);
			//用户不存在，新增
			if(user == null) {
				user = new User();
			}
			
			String salt = MD5Util.getRand();
			String encodepwd = MD5Util.encoder(password, salt);
			user.setPwd(encodepwd);
			user.setSalt(salt);
			user.setState(ACTIVITE);
			
			if(user.getId() == null) {
				
				user.setCreateTime(new Date());
				user.setId(UserIdWroker.getConsumerId());
				user.setMobile(mobile);
				userMapper.insert(user);
				return true;
			}
			
			userMapper.updateById(user);
			return true;
						
		} catch(Exception e) {
			logger.error("register user error:",e);
			return false;
		}
	}

	@Override
	public Boolean logOut(String mobile) {
		//清理token
		return tokenService.removeToken(mobile);
	}

}
