package com.cst.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CycleATest {


    @Autowired
    CycleA cycleA;
    @Test
    public void testB(){
        System.out.println(cycleA.cycleB.name);
    }


}