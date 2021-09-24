package com.cst.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AopServiceImplTest {


    @Autowired
    AopServiceImpl aopService;

    @Test
    void test1(){
        Assertions.assertThrows(ArithmeticException.class, ()->{aopService.div(1,0);});
    }

    @Test
    void test2(){
        aopService.div(15,6);
    }


}