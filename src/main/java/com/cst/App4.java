package com.cst;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App4 {

    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("-----外层1");
                lock.lock();
                System.out.println("-----内层1");

            }  finally {
                lock.unlock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "hyh").start();


        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("-----外层2");
                lock.lock();
                System.out.println("-----内层2");
            }  finally {
                lock.unlock();
                lock.unlock();
            }
        }, "hyh").start();

        new Thread(() -> {
            try {

            }finally {

            }
        }, "hyh").start();

    }




}
