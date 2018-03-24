/**
 * 
 */
package io.clab.mpf.shop.consumer.eventbus.event;

import java.util.Date;

/**
 * @author iceray
 * 用户登录事件
 */
public class LoginEvent {

	private String mobile;
	
	private Date loginTime;
	
	public LoginEvent(String mobile,Date loginTime) {
		this.mobile = mobile;
		this.loginTime = loginTime;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public Date getLoginTime() {
		return this.loginTime;
	}
}
