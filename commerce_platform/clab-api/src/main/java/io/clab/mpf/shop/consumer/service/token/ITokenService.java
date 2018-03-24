/**
 * 
 */
package io.clab.mpf.shop.consumer.service.token;

/**
 * @author iceray
 *
 */
public interface ITokenService {

	String getTokenByMobile(String mobile);
	
	Long getUserIdByToken(String token);
	
	Boolean removeToken(String mobile);
}
