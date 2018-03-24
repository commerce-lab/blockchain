/**
 * 
 */
package io.clab.mpf.shop.consumer.service.user;

/**
 * @author iceray
 * 用户登录
 */
public interface IUserLoginService {

	String getToken(String mobile,String authCode);
	
	Boolean register(String mobile,String password,String authCode);
	
	Boolean logOut(String mobile);
}
