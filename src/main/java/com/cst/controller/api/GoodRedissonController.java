package com.cst.controller.api;


import com.cst.config.Utils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/good")
public class GoodRedissonController {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    RedissonClient redissonClient;

    @PostConstruct
    public void init() throws InterruptedException {
        redisTemplate.opsForValue().set("good01", 500);
        System.out.println("初始化完毕...");

//        Thread.sleep(20_000);
        for (int j = 0; j < 400; j++) {
            int finalJ = j;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 200; i++) {
                    this.buy(finalJ);
                }

            }, "线程" + j).start();
        }

    }

    String REDIS_LOCK = "lock";

    @GetMapping("/buy")
    public synchronized String buy(int j) {
        RLock rLock  = redissonClient.getLock(REDIS_LOCK);;
        try {
            //当前redis是否被某个线程加锁了
            rLock.lock();
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
            if(rLock.isLocked()&&rLock.isHeldByCurrentThread()){
                rLock.unlock();
            }
        }
        return "卖完了";
    }

}
