package edu.bit.study.Thread.WriteAndRead;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class WriteAndRead {


    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final  ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private int value;

    public WriteAndRead() {
        value = 0;
    }

    public void increase(){
        writeLock.lock();
        value++;
        System.out.println("增加到" + value);
        writeLock.unlock();
    }

    public void getValue(){
        readLock.lock();
        System.out.println("现在是" + value);
        readLock.unlock();
    }
//    static class Thread1 implements Runnable{
//        @Override
//        public void run() {
//            System.out.println(1);
//        }
//    }
//
//    static class Thread2 implements Runnable{
//        @Override
//        public void run() {
//            System.out.println(2);
//        }
//    }

    public static void main(String[] args) throws InterruptedException {

        WriteAndRead writeAndRead = new WriteAndRead();
        Thread writer = new Thread(() -> {
            writeAndRead.increase();
        });
        Thread reader1 = new Thread(() -> {
            writeAndRead.getValue();
        });

        Thread reader2 = new Thread(() -> {
            writeAndRead.getValue();
        });

        reader1.start();;
        writer.start();
        reader2.start();

        writer.join();;
        reader1.join();
        reader2.join();

    }
}
