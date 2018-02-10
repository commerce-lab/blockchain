/**
 * 
 */
package io.clab.mpf.shop.consumer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.consumer.service.IUserLoginService;
import io.clab.mpf.shop.util.SmsAuthCodeUtils;
import io.clab.mpf.shop.util.token.Jwt;

/**
 * @author iceray
 *
 */
@Service
public class UserLoginService implements IUserLoginService {

	private static Long EXPIRE_TIME = 1000 * 60 * 60 * 12l;
	
	private static Long OPER_EXPIRE_TIME = EXPIRE_TIME + 1000 * 60 * 30l; 
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	public String getToken(String mobile, String authCode) {
		//TODO:接口还没好，目前直接返回token
		/*String verifyCode = SmsAuthCodeUtils.getAututhCode(mobile);
		if(StringUtils.isEmpty(verifyCode) || !verifyCode.equals(authCode)) {
			return "";
		}*/
		
		Map<String, Object> payload = new HashMap<String, Object>();
		Date date = new Date();
		payload.put("uid", mobile);	// 用户ID
		payload.put("ext", date.getTime() + EXPIRE_TIME);	// 过期时间12小时
		String token = Jwt.createToken(payload);
		redisTemplate.opsForValue().set("token_"+mobile, token, OPER_EXPIRE_TIME, TimeUnit.MILLISECONDS);
		return token;
	}

}
