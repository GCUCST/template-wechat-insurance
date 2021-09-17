package com.cst.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cst.entities.User;
import com.cst.mapper.UserMapper;
import com.cst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

//    private final UserMapper userMapper;
//
//    public UserServiceImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }


}
