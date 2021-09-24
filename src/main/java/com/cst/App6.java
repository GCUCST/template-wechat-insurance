package com.cst;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App6 {

    static Lock lock = new ReentrantLock();
    static Condition condition =lock.newCondition();


    public static void main(String[] args) {

        new Thread(() -> {

            try{
                lock.lock();
                System.out.println("进入..."+Thread.currentThread().getName());
                condition.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            System.out.println("被唤醒..."+Thread.currentThread().getName());

        }, "A").start();

        new Thread(() -> {

            try{
                lock.lock();
                System.out.println("唤醒..."+Thread.currentThread().getName());
                condition.signal();
            }  finally {
                lock.unlock();
            }

        }, "B").start();




    }



}
