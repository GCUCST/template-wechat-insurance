package com.cst.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution (public int com.cst.service.impl.AopServiceImpl.*(..))")
    public void beforeNotify(){
        System.out.println("Before前置通知被执行");
    }

    @After("execution (public int com.cst.service.impl.AopServiceImpl.*(..))")
    public void afterNotify(){
        System.out.println("After被执行.....");
    }

    @AfterReturning("execution (public int com.cst.service.impl.AopServiceImpl.*(..))")
    public void afterReturning(){
        System.out.println("afterReturning.....");
    }

    @Around("execution (public int com.cst.service.impl.AopServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around......start");
        Object retValue = proceedingJoinPoint.proceed();
        System.out.println("around......end"+retValue);
        return retValue;
    }

    @AfterThrowing("execution (public int com.cst.service.impl.AopServiceImpl.*(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing.....");
    }




}
