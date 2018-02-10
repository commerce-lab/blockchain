package io.clab.mpf.shop.conf;

import org.springframework.context.annotation.Configuration;

import io.clab.mpf.shop.framework.session.EnableMpfRedisSession;

@Configuration
@EnableMpfRedisSession(database=1)
public class RedisSessionConfig {
}