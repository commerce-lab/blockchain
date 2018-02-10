package io.clab.mpf.shop.util;

/**
 * 短信验证码操作类
 * by chenzhenhua
 * ***/
public class SmsAuthCodeUtils {
	/**
	 * 发送验证码
	 * ***/
	public static boolean sendAuthCode(String mobile){
		return true;
	}
	/**
	 * 获取改手机号接收到的验证码
	 * 如果不存在相应验证码则返回空(null)
	 * **/
	public static String getAututhCode(String mobile){
		return "123456";
	}
}