/**
 * 
 */
package io.clab.mpf.shop.consumer.service;

/**
 * @author iceray
 * 用户登录
 */
public interface IUserLoginService {

	String getToken(String mobile,String authCode);
}
