package com.yyc.songshu.manager.util;


import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/*redis读写工具类*/
@Component
public class RedisUtil {

    // 全局过期时间(单位秒)
    private static int expireTime = 180;

    // 连接池
    private static JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        // 可用连接实例的最大数目，默认值为8；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        int MAX_ACTIVE = 1024;
        config.setMaxTotal(MAX_ACTIVE);
        //最多空闲实例
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        int MAX_IDLE = 200;
        config.setMaxIdle(MAX_IDLE);
        //超时时间
        // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        int MAX_WAIT = 10000;
        config.setMaxWaitMillis(MAX_WAIT);
        //
        config.setTestOnBorrow(false);
        // Redis服务器IP
        String ADDR = "122.114.86.158";
        // Redis的端口号
        int PORT = 6379;
        // 访问密码
        String AUTH = "EvanLoveSw.";
        pool = new JedisPool(config, ADDR, PORT, 1000, AUTH);

    }

    /**
     * 获取jedis实例
     */
    private static synchronized Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * @param key
     * @param channel
     * @return
     */
    public static long redisSaveHome(String key,String channel){
        Jedis jedis = null;
        boolean isBroken = false;
        long saveInt = 0;
        try {
            jedis = getJedis();
             saveInt =  jedis.sadd(key,channel);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return saveInt;
    }

    public static Set<String> redisGetChannel(String key){
        Jedis jedis = null;
        boolean isBroken = false;
        Set<String> stringSet = null;
        try {
            jedis = getJedis();
            stringSet =  jedis.smembers(key);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return stringSet;
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     * @param isBroken
     */
    private static void closeResource(Jedis jedis, boolean isBroken) {
        try {
            if (isBroken) {
                jedis.close();
            } else {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否存在key
     *
     * @param key
     */
    public static boolean existKey(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(0);
            return jedis.exists(key);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return false;
    }

    /**
     * 删除key
     *
     * @param key
     */
    public static void delKey(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(0);
            jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
    }

    /**
     * 取得key的值
     *
     * @param key
     */
    public static String stringGet(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            lastVal = jedis.get(key);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return lastVal;
    }

    /**
     * 添加string数据
     *
     * @param key
     * @param value
     */
    public static String stringSet(String key, String value) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            lastVal = jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            e.printStackTrace();
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return lastVal;
    }

    /**
     * 添加hash数据
     *
     * @param key
     * @param field
     * @param value
     */
    public static void hashSet(String key, String field, String value) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.select(0);
                jedis.hset(key, field, value);
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
    }
}
