package com.cst;


import lombok.val;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class App7 {

    public static void main(String[] args) throws InterruptedException {

        val a = new Thread(() -> {
            System.out.println("进入..." + Thread.currentThread().getName());
            LockSupport.park();
            System.out.println("被唤醒..." + Thread.currentThread().getName());

        }, "A");
        a.start();


        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println("通知..."+Thread.currentThread().getName());

        }, "B").start();




    }



}
