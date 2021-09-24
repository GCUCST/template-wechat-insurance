package com.cst;


public class App3 {

    public static Object lock = new Object();

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                App3.dealData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "hyh").start();

        new Thread(() -> {
            try {
                App3.dealData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cst").start();


    }

    public static void dealData() throws InterruptedException {
        synchronized (lock) {
            System.out.println("进入..."+Thread.currentThread().getName());
            synchronized (lock){
                Thread.sleep(5000);
                System.out.println("重入了！这个是可重入锁（同一个线程可以多次获得同一把锁）："+Thread.currentThread().getName());
            }

            System.out.println("出去...");
        }
    }


}
