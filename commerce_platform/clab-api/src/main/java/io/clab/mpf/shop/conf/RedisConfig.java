/**
 * 
 */
package io.clab.mpf.shop.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author iceray
 *
 */
@Configuration
public class RedisConfig {

	@Bean  
    @ConfigurationProperties(prefix="spring.redis.pool")  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
      
    @Bean  
    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory getConnectionFactory(JedisPoolConfig config){  
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(config);  
        return factory;  
    }  
      
      
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> getRedisTemplate(JedisConnectionFactory connFactory){  
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connFactory);
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);
        return template;  
    }  
}
