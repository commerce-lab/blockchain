package util;



//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
@Repository("jedisPools")
public class JedisPools {
    private static JedisPool pool = null;

    @Value("${jedis.host}")
    private String host;

    @Value("${jedis.port}")
    private Integer port;

    @Value("${jedis.pwd}")
    private String pwd;

    @Value("${jedis.timeout}")
    private Integer timeout;

    @Value("${jedis.continuetime}")
    private Integer continuetime;

    private JedisPools jedisPool = null;

    public JedisPools getInstance() {
        if (jedisPool == null) {
            jedisPool = new JedisPools();
        }
        return jedisPool;
    }

    /**
     * 构建redis连接池
     */
    public JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);

            // 是否启用后进先出, 默认true
            config.setLifo(true);

            // 最大连接数, 默认8个
            config.setMaxTotal(100);

            // 最小空闲连接数, 默认0
            config.setMinIdle(0);

            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(300);

            // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
            // 默认-1
            config.setMaxWaitMillis(-1);

            // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(1800000);

            // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            config.setNumTestsPerEvictionRun(3);

            // 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数
            // 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
            config.setSoftMinEvictableIdleTimeMillis(1800000);

            // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            config.setTimeBetweenEvictionRunsMillis(-1);

            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);

            if (!"".equals(pwd)) {
                pool = new JedisPool(config, host, port, timeout, pwd);
            } else {
                pool = new JedisPool(config, host, port, timeout);
            }
        }
        return pool;
    }

    /**
     * 返还到连接池
     *
     * @param redis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    /**
     * 保存String数据
     *
     * @param key        标识符
     * @param jsonString 保存的参数
     * @param mtimeout   保存时间
     * @return
     */
    public boolean setString(String key, String jsonString, int mtimeout) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();

            jedis.set(key, jsonString);
            if (mtimeout != -1) {
                if (mtimeout == 0) {
                    jedis.expire(key, continuetime);
                } else {
                    jedis.expire(key, mtimeout);
                }
            }
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return true;
    }

    /**
     * 保存Object数据
     */
    public boolean setObject(String key, Object object, int mtimeout) {
        /*JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();

            JSONArray testjson = JSONArray.fromObject(object);
            jedis.set(key, testjson.toString());
            if (mtimeout != -1) {
                if (mtimeout == 0) {
                    jedis.expire(key, continuetime);
                } else {
                    jedis.expire(key, mtimeout);
                }
            }
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }*/
        return true;
    }

    /**
     * 获取json数据
     */
    public String getString(String key) {
        String value = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 更新自动释放内存时间
     */
    public boolean updatetime(String key, int time) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            if (time != 0) {
                jedis.expire(key, time);
            } else {
                jedis.expire(key, continuetime);
            }
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return true;
    }

    /**
     * 删除数据
     */
    public boolean delString(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return false;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return true;
    }

    /**
     * 取<T> T数据
     */
   /* public <T> T getObject(String key, T t) {
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("storeRange", Store.class);
        classMap.put("partnerRange", Partner.class);
        classMap.put("regionRange", Region.class);
        classMap.put("menu", MemberMenuDTO.class);
        classMap.put("privileges", PrivilegesDTO.class);
        return getObject(key, t, classMap);
//		return getObject(key, t, 0);
    }*/

    /**
     * 从redis中取key对应的对象，同时更新对象的超时销毁时间
     *
     * @param key
     * @param t
     * @param time 超时时间
     * @return
     */
/*    public <T> T getObject(String key, T t, int time) {
        return getObject(key, t, time, null);
    }*/

/*    public <T> T getObject(String key, T t, Map<String, Class> classMap) {
        return getObject(key, t, 0, classMap);
    }*/

/*    public <T> T getObject(String key, T t, int time, Map<String, Class> classMap) {
        try {
            String loginjson = getString(key);
            if (loginjson == null) {
                return null;
            }
            // 如果设置有超时时间，则更新
            if (time > 0) {
                updatetime(key, time);
            }
            JSONObject json = JSONObject.fromObject(loginjson.substring(1,
                    loginjson.length() - 1));
            if (CollectionUtil.isEmpty(classMap)) {
                classMap = new HashMap<String, Class>();
            }
            t = (T) JSONObject.toBean(json, t.getClass(), classMap);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/




    /**
     * 设置登录用户会话信息的有效时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expireSession(String key, int time) {
        return updatetime(key, time);
    }


    /**
     * 获取自增长序列
     *
     * @param key
     * @return
     * @author chensb
     */
    public Long getIncrId(String key) {
        Long id = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            id = jedis.incr(key);
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return id;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     * @author chensb
     */
    public Boolean exists(String key) {
        JedisPool pool = null;
        Jedis jedis = null;
        Boolean isExist = false;
        try {
            pool = getPool();
            jedis = pool.getResource();
            isExist = jedis.exists(key);
        } catch (Exception e) {
            // 释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return isExist;
    }


}
