/**
 * 
 */
package io.clab.mpf.shop.consumer.service.token.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import io.clab.mpf.shop.consumer.entity.user.User;
import io.clab.mpf.shop.consumer.repository.user.UserMapper;
import io.clab.mpf.shop.consumer.service.token.ITokenService;
import io.clab.mpf.shop.util.token.Jwt;
import io.clab.mpf.shop.util.token.TokenState;

/**
 * @author iceray
 *
 */
@Service
public class TokenServiceImpl implements ITokenService {

	private static Logger logger = Logger.getLogger(TokenServiceImpl.class);
	
	private static Long EXPIRE_TIME = 1000 * 60 * 60 * 12l;

	private static Long OPER_EXPIRE_TIME = EXPIRE_TIME + 1000 * 60 * 30l;
	
	private static Long MAX_STORE_USER = 10000000L;
	
	private LoadingCache<String, String> userCache = null;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UserMapper userMapper;
	
	@PostConstruct
	public void init() {
		
		userCache = CacheBuilder.newBuilder().maximumSize(MAX_STORE_USER)
				      .build(new CacheLoader<String,String>(){

						@Override
						public String load(String mobile) throws Exception {
							String token = (String)redisTemplate.opsForValue().get("token_" + mobile);
							if(StringUtils.isEmpty(token))
								return "";
							return token;
						}
				    	  
				      });
		
	}

	@Override
	public String getTokenByMobile(String mobile) {

		// 根据手机号获取用户ID
		User user = new User();
		user.setMobile(mobile);
		user = userMapper.selectOne(user);

		Date date = new Date();
		Long expireTime = date.getTime() + EXPIRE_TIME; // 过期时间12小时
		String token = Jwt.createToken(user,expireTime);
		redisTemplate.opsForValue().set("token_" + mobile, token, OPER_EXPIRE_TIME, TimeUnit.MILLISECONDS);
		
		userCache.put(mobile, token);
		return token;
	}

	/**
	 * 返回-1表示token错误
	 * 返回0表示token失效
	 */
	@Override
	public Long getUserIdByToken(String token) {
		Map<String,Object> result = Jwt.validToken(token);
		
		if(!result.containsKey("uid")) {
			if(TokenState.EXPIRED.toString().equals(result.get("state"))) {
				return 0L;
			} else {
				return -1L;
			}
		}
		
		Long userId = Long.parseLong((String)result.get("uid"));
		String mobile = (String)result.get("mobile");
		//校验缓存是否存在用户信息
		if(userId == 0L || StringUtils.isEmpty(mobile))
			return -1L;
		
		
		try {
			String storeToken = userCache.get(mobile);
			//比对token
			if(StringUtils.isEmpty(storeToken) || !storeToken.equals(token)) {
				return -1L;
			}
		} catch (ExecutionException e) {
			logger.error("get mobile from cache error:" + e);
			return -1L;
		}
		
		
		return userId;
	}

	@Override
	public Boolean removeToken(String mobile) {
		try {
			redisTemplate.delete("token_" + mobile);
			userCache.invalidate(mobile);
		} catch(Exception e) {
			logger.error("remove token error:" + e);
			return false;
		}
		
		return true;
	}

}
