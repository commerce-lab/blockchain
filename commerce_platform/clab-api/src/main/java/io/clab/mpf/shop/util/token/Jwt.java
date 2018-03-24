package io.clab.mpf.shop.util.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifier.BaseVerification;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.clab.mpf.shop.consumer.entity.user.User;

public class Jwt {

	/**
	 * 秘钥
	 */
	private static final byte[] SECRET = "3d990d2276917dfac04467df11fff26d".getBytes();
	
	private static final String ISSUER = "lab_mpf";
	
	private static Algorithm ALGORITHM_HS = Algorithm.HMAC256(SECRET);
	
	private static JWTVerifier VERIFIER = ((BaseVerification)JWT.require(ALGORITHM_HS).withIssuer(ISSUER)).build();


	
	
	/**
	 * 生成token，该方法只在用户登录成功后调用
	 * 
	 * @param Map集合，可以存储用户id，token生成时间，token过期时间等自定义字段
	 * @return token字符串,若失败则返回null
	 */
	
	public static String createToken(User user,Long expireTime) {
		
		// 创建一个 JWS object
		
		return JWT.create().withIssuer(ISSUER).withExpiresAt(new Date(expireTime))
			.withIssuedAt(new Date())
		     .withClaim("uid", user.getId().toString())
		      .withClaim("uphone", user.getMobile().toString())
		       .withClaim("crt", System.currentTimeMillis())
		       .sign(ALGORITHM_HS);
		
	}

	/**
	 * 校验token是否合法，返回Map集合,集合中主要包含 state状态码 data鉴权成功后从token中提取的数据
	 * 该方法在过滤器中调用，每次请求API时都校验
	 * 
	 * @param token
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> validToken(String token) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			DecodedJWT jwt = VERIFIER.verify(token);
			
			// token校验成功（此时没有校验是否过期）
			resultMap.put("state", TokenState.VALID.toString());
			resultMap.put("uid", jwt.getClaim("uid").asString());
			resultMap.put("mobile", jwt.getClaim("uphone").asString());
			
		} catch (TokenExpiredException e) {
			resultMap.clear();
			resultMap.put("state", TokenState.EXPIRED.toString());
		} catch(Exception ex) {
			resultMap.clear();
			resultMap.put("state", TokenState.INVALID.toString());
		}
		return resultMap;
	}

}