/**
 * 
 */
package io.clab.mpf.shop.framework.session;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.RedisFlushMode;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Documented
@Import(RedisHttpSessionConfiguration.class)
@Configuration
public @interface EnableMpfRedisSession {
	int maxInactiveIntervalInSeconds() default 1800;

	int database() default 0;
	
	String redisNamespace() default "";

	RedisFlushMode redisFlushMode() default RedisFlushMode.ON_SAVE;
}

