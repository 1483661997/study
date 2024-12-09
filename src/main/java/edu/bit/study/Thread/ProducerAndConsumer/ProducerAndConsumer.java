package edu.bit.study.Thread.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {

    static LinkedList  list;
    public  static int num;
    public static final ReentrantLock lock = new ReentrantLock();

    static final Condition notFull = lock.newCondition();
    static final Condition notEmpty = lock.newCondition();

    static class Producer implements Runnable{
        @Override
        public void run() {
            int value = 0;
            while (true){
                lock.lock();
                try {
                    while(num >= 10){
                        notFull.await();
                    }
                    System.out.println("生产:"+value);

                    list.add(value++);
                    num++;
                    notEmpty.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }

            }
        }

    }

    static class Consumer implements Runnable{
        @Override
        public void run() {
            while (true){
                lock.lock();
                try {
                    while ( num <= 0)
                        notEmpty.await();

                    System.out.println("消费" + list.removeFirst());
                    num--;
                    notFull.signal();;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public ProducerAndConsumer(){
        list = new LinkedList();
    }



    public static void main(String[] args) {
        ProducerAndConsumer main = new ProducerAndConsumer();

        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
