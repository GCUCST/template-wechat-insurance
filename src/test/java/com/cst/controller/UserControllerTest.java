package com.cst.controller;

import com.cst.entities.User;
import com.cst.mapper.UserMapper;
import com.cst.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest

//@RunWith(SpringRunner.class)
@DisplayName("UserControllerTest测试类")
class UserControllerTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    @Test
    public void testSelect1() {
        System.out.println(("----- selectAll method test ------"));
        int i = userMapper.insert(User.builder().name("哈哈哈吧").openid("测试123456").gender("FEMALE").contactPhone("哈哈哈哈哈哈").build());
        System.out.println(i);
    }

    @Test
    public void testSelect2() {
        System.out.println(("----- selectAll method test ------"));
        long count = userService.count();
        System.out.println(count);
    }


}
