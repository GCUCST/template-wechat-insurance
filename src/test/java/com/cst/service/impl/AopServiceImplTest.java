package com.cst.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AopServiceImplTest {


    @Autowired
    AopServiceImpl aopService;

    @Test
    void aop5Throw(){
        System.out.println(SpringVersion.getVersion());
        System.out.println(SpringBootVersion.getVersion());
        Assertions.assertThrows(ArithmeticException.class, ()->{aopService.div(1,0);});
    }

    @Test
    void aop5(){
        aopService.div(15,6);
    }


    @Test
    void aopAdd(){
        aopService.add(15,6);
    }


}