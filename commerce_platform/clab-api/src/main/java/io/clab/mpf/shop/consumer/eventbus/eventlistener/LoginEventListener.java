/**
 * 
 */
package io.clab.mpf.shop.consumer.eventbus.eventlistener;

import io.clab.mpf.shop.consumer.eventbus.event.LoginEvent;

import com.google.common.eventbus.Subscribe;

/**
 * @author iceray
 * 登录事件监听器
 */

public class LoginEventListener {

	@Subscribe
	public void listener(LoginEvent loginEvent) {
		
	}
}
