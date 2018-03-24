/**
 * 
 */
package io.clab.mpf.shop.conf;

import io.clab.mpf.shop.consumer.eventbus.eventlistener.LoginEventListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

/**
 * @author iceray
 *
 */
@Configuration
public class EventConfig {

	
	@Bean
	public LoginEventListener getLoginEventListener() {
		LoginEventListener loginEventListener = new LoginEventListener();
		
		return loginEventListener;
	}
	
	@Bean
	public EventBus getLoginEventBus(LoginEventListener loginEventListener) {
		
		EventBus eventBus = new EventBus("loginevent");
		
		eventBus.register(loginEventListener);
		
		return eventBus;
	}
}
