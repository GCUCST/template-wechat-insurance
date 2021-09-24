package com.cst.service;

import com.cst.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;


@SpringBootTest
class MyServiceTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate re;


    @Test
    public void test1() {
        re.opsForValue().set("name", "CST");
        final String s = (String) re.opsForValue().get("name");
        System.out.println(s);
    }

    //用于抽奖
    @Test
    public void set() {
        re.opsForSet().add("userId", "1");
        re.opsForSet().add("userId", "2");
        re.opsForSet().add("userId", "3");
        re.opsForSet().add("userId", "3");
    }

    @Test
    public void set1() {
        final List<String> userId = re.opsForSet().pop("userId", 3);
        System.out.println(userId);
    }

    @Test
    public void check() {
        final Long userId = re.opsForSet().size("userId");
        System.out.println(userId);
    }


    //用于购物车  Map<String,Map<String,Object>>
    @Test
    public void hash() {
          re.opsForHash().put("user:1001购物车","商品1001","5件");
          re.opsForHash().put("user:1001购物车","商品1002","3件");
          re.opsForHash().put("user:1002购物车","商品1003","6件");
    }
    @Test
    public void hash1() {
        final Map<Object, Object> entries = re.opsForHash().entries("user:1001购物车");
        entries.forEach((k,v)->{
            System.out.println(k+"--->"+v);
        });
    }


    //用于购物车  Map<String,Map<String,Object>>
    @Test
    public void list() {
        re.opsForList().leftPush("user:cst","1001");
        re.opsForList().leftPush("user:cst","1003");
        re.opsForList().leftPush("user:cst","1005");
    }
    @Test
    public void list1() {
        final List<String> range = re.opsForList().range("user:cst", 0, -1);
        range.forEach(e->{
            System.out.println(e);
        });
    }


    //zset带着排序的
    @Test
    public void zset() {
        re.opsForZSet().add("电动车","艾玛小糖豆2020",99);
        re.opsForZSet().add("电动车","艾玛小糖豆2019",20);
        re.opsForZSet().add("电动车","艾玛小糖豆2021",80);
        re.opsForZSet().add("电动车","艾玛小糖豆2026",160);
    }
    @Test
    public void zset1() {
        final Set<String> car = re.opsForZSet().rangeByScore("电动车", 40, 1200, 0, 3);
        for ( String stringTypedTuple : car) {
            System.out.println(stringTypedTuple);
        }
    }




    @Test
    public void setJson() {
        re.opsForValue().set("user", User.builder().name("chenst").id("1002").build());
    }

    @Test
    public void getJson() {
        final User user = (User) re.opsForValue().get("user");
        System.out.println(user.toString());
    }






}