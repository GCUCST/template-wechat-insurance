package com.cst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CycleB {


    public String name = "BBBB";
    @Autowired


    public   CycleA cycleA;
//    public CycleB(CycleA cycleA){
//        this.cycleA = cycleA;
//    }

}
