package io.clab.mpf.shop.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
/**
 * redis 通用操作工具类
 * by chenzhenhua
 * 
 * ***/
public class RedisUtils {
	private JedisPool jedisPool;
	
	public RedisUtils(JedisPool jedisPool) {
		// TODO Auto-generated constructor stub
		this.jedisPool = jedisPool;
	}

	/*
	 * starting from Jedis 3.0 returnResource() is deprecated, use Jedis.close()
	 * instead
	 */
	public String get(String key) {
		// value sent to redis cannot be null
		if (key == null) {
			return null;
		}

		Jedis jedis = null;
		String val = null;
		try {
			jedis = jedisPool.getResource();
			val = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return val;
	}

	public void put(String key, String val) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			jedis.set(key, val);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	public void remove(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

	public void txPut(String[][] pairs) {
		Jedis jedis = null;
		Transaction tx = null;
		try {
			jedis = jedisPool.getResource();
			tx = jedis.multi();

			for (String[] p : pairs) {
				tx.set(p[0], p[1]);
			}
			tx.exec();
		} catch (Exception e) {
			if (tx != null) {
				tx.discard();
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	

	public void expire(String key, int seconds) {
		if (key == null) {
			return;
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	public void sadd(String key,String... values){
		Jedis jedis = null;
		jedis = jedisPool.getResource();
		jedis.sadd(key, values);
		jedis.close();
	}
	public long scard(String key){
		Jedis jedis = null;
		jedis = jedisPool.getResource();
		long count = jedis.scard(key);
		jedis.close();
		return count;
	}
}
