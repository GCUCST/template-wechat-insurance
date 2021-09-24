package com.cst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CycleA {

    public String name = "AAAAA";
    @Autowired
    public   CycleB cycleB;

//    public CycleA(CycleB cycleB){
//        this.cycleB = cycleB;
//    }


}
