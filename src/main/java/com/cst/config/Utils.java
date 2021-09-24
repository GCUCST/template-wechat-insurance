package com.cst.config;

import lombok.experimental.UtilityClass;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@UtilityClass
public class Utils {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxTotal(40);

        jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
    }


    public static Jedis getJedis() throws Exception {
        if(null!=jedisPool){
            return jedisPool.getResource();
        }
        throw new Exception("jedis ..is not ok");
    }
}
