package com.cst;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class App5 {

    static Object object = new Object();

    public static void main(String[] args) {


        new Thread(() -> {
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"进入..");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被叫醒..");
            }
        }, "A").start();



        new Thread(() -> {
            synchronized (object){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                object.notify();
                System.out.println(Thread.currentThread().getName()+"通知..");
            }
        }, "B").start();

    }



}
