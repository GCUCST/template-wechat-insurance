package com.cst.controller.api;


import com.cst.config.Utils;
import com.cst.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/good")
public class GoodController {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() throws InterruptedException {
        redisTemplate.opsForValue().set("good01", 200);
        System.out.println("初始化完毕...");

//        Thread.sleep(20_000);
        for (int j = 0; j < 200; j++) {
            int finalJ = j;
            new Thread(() -> {

                for (int i = 0; i < 500; i++) {
                    this.buy(finalJ);
                }

            }, "线程" + j).start();
        }

    }

    Object lock = new Object();
    Lock reentrantLock = new ReentrantLock();

    //这个无法解决分布式微服务的锁的问题
//    @GetMapping("/buy")
//    public synchronized String buy(int j) {
//
//        Integer good01 = (Integer) redisTemplate.opsForValue().get("good01");
//        int num = good01 == null ? 0 : good01;
//        if (num > 0) {
//            num = num - 1;
//            redisTemplate.opsForValue().set("good01", num);
//            System.out.println(j + "买到一个，还剩：" + num);
//            return "买到一个，还剩：" + num;
//        } else {
//            System.out.println("卖光光了！" + num);
//        }
//        return "卖光光了！！！";
//    }


    String REDIS_LOCK = "lock";

    //无法照顾到宕机的情况，走不到finally无法解锁
//    @GetMapping("/buy")
//    public synchronized String buy(int j) {
//        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
//        try {
//            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value); //setNx
//            //当前redis是否被某个线程加锁了
//            if (!aBoolean) {
//                return "抢锁失败！！！";
//            }
//            Integer good01 = (Integer) redisTemplate.opsForValue().get("good01");
//            int num = good01 == null ? 0 : good01;
//            if (num > 0) {
//                num = num - 1;
//                redisTemplate.opsForValue().set("good01", num);
//                System.out.println(j + "买到一个，还剩：" + num);
//                return "买到一个，还剩：" + num;
//            } else {
//                System.out.println("卖光光了！" + num);
//            }
//        } finally {
//            redisTemplate.delete(REDIS_LOCK);
//        }
//        return "卖光光了！！！";
//    }


    @GetMapping("/buy")
    public synchronized String buy(int j) {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10, TimeUnit.SECONDS); //setNx
            //当前redis是否被某个线程加锁了
            if (!aBoolean) {
                return "抢锁失败！！！";
            }
            Integer good01 = Integer.parseInt(stringRedisTemplate.opsForValue().get("good01"));
            int num = good01 == null ? 0 : good01;
            if (num > 0) {
                num = num - 1;
                stringRedisTemplate.opsForValue().set("good01", num + "");
                System.out.println(j + "买到一个，还剩：" + num);
                return "买到一个，还剩：" + num;
            } else {
                System.out.println("卖光光了！" + num);
            }
        } finally {
            //查和删不是原子操作，需要lua脚本去做这个事情。（过期的情况就会造成误删的情况）
//            if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)){
//                stringRedisTemplate.delete(REDIS_LOCK);
//            }


            //使用lua脚本
            Jedis jedis = null;
            try {
                jedis = Utils.getJedis();
                jedis.select(6);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String script  = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            try {
                final Object eval = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if("1".equals(eval.toString())){
                    System.out.println("delete redis ok....");
                }else{
                    System.out.println("delete redis not ok....");
                }
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }


            //使用事务才操作
//            while (true) {
//                //监听key:REDIS_LOCK，如果改变，事务提交就会失败。继续监听
//                stringRedisTemplate.watch(REDIS_LOCK);
//                if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {
//                    stringRedisTemplate.setEnableTransactionSupport(true);
//                    stringRedisTemplate.multi();
//                    stringRedisTemplate.delete(REDIS_LOCK);
//                    final List<Object> list = stringRedisTemplate.exec();
//                    if (list == null) {
//                        continue;
//                    }
//                }
//                stringRedisTemplate.unwatch();
//                break;
//            }

        }
        return "卖完了";
    }

}
