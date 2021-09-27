package com.cst;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class Solution {
    private int n;
    private int cur;
    public Lock lock = new ReentrantLock( );
    private Condition condition = lock.newCondition();


    public Solution(int n) {
        this.n = n;
        cur = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        while (cur<=n) {
            if (cur % 3 == 0&&cur % 5 != 0) {
                printFizz.run();
                cur++;
                condition.signalAll();
            }else{
                condition.await();
            }
        }
        lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        while (cur<=n) {
            if (cur % 3 != 0&&cur % 5 == 0) {
                printBuzz.run();
                cur++;
                condition.signalAll();
            }else{
                condition.await();
            }
        }
        lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        while (cur<=n) {
            if (cur % 3 == 0&&cur % 5 == 0) {
                printFizzBuzz.run();
                cur++;
                condition.signalAll();
            }else{
                condition.await();
            }
        }
        lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        while (cur<=n) {
            if (cur % 3 != 0&&cur % 5 != 0) {
                printNumber.accept(cur);
                cur++;
                condition.signalAll();
            }else{
                condition.await();
            }
        }
        lock.unlock();
    }
}