package edu.bit.study.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private static volatile int count = 0; // 共享变量
    private final static Lock lock = new ReentrantLock(); // 创建一个锁对象

    public void printNextCount_volatile(){
        count++;  // 增加计数
        System.out.println("Current count is: " + count);
    }
    public synchronized void printNextCount_syn() {
        count++;  // 增加计数
        System.out.println("Current count is: " + count);
    }

    public static synchronized void increment() {
        count++;
    }
    public void printNextCount() {
        lock.lock();  // 获取锁
        try {
            count++;  // 增加计数
            System.out.println("Current count is: " + count);
        } finally {
            lock.unlock();  // 释放锁
        }

    }

    public static void main1(String[] args) {
        Counter counter = new Counter();
        // 创建并启动多个线程
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
//                counter.printNextCount();
//                counter.printNextCount_syn();
                counter.printNextCount_volatile();
            }).start();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increment_reentrantLock();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join(); // 等待所有线程完成
        }
        System.out.println("Expected: " + (100 * 1000));
        System.out.println("Actual: " + count);
    }

    public static void increment_reentrantLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }


}
