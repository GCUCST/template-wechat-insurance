package com.cst;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class H2O {

   static   Semaphore h = new Semaphore(2);
    static Semaphore o = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        final H2O h2O = new H2O();



        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                try {
                        h2O.oxygen(() -> System.err.println("o"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {

                    h2O.hydrogen(() -> System.err.println("h"));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }


    public H2O() {

    }

    public  void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        System.out.println("请求一个：h");
        h.acquire();

        releaseHydrogen.run();
        System.out.println("消耗h气");
        if (o.availablePermits() == 0 && h.availablePermits() == 0) {
            System.out.println("释放o气");
            o.release();
        }

    }

    //0   h
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        System.out.println("请求一个：o");
        o.acquire();
        releaseOxygen.run();
        System.out.println("消耗o气");
        if (o.availablePermits() == 0 && h.availablePermits() == 0) {
            h.release(2);
            System.out.println("释放2个h气");
        }
    }
}