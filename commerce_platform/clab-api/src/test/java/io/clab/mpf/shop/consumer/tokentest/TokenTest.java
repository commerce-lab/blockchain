package io.clab.mpf.shop.consumer.tokentest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.clab.mpf.shop.util.token.Jwt;

public class TokenTest {

	public static void main(String[] args) {
		Map<String, Object> payload = new HashMap<String, Object>();
		Date date = new Date();
		payload.put("uid", 123456789);	// 用户ID
		//payload.put("iat", date.getTime());	// 生成时间
		payload.put("ext", date.getTime() + 1000 * 60 * 60);	// 过期时间1小时
		String token = Jwt.createToken(payload);
		
		Map<String,Object> result = Jwt.validToken(token);
		System.out.println(result.get("uid"));
	}
}
