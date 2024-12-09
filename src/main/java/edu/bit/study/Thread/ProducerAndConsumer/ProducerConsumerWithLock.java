package edu.bit.study.Thread.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private static final int CAPACITY = 10;
    private final Queue<Integer> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    class Producer implements Runnable {
        @Override
        public void run() {
            int value = 0;
            while (true) {
                lock.lock();
                try {
                    // Wait until the buffer has space
                    while (queue.size() == CAPACITY) {
                        bufferNotFull.await();
                    }
                    // Add value to the buffer
                    System.out.println("Produced " + value);
                    queue.offer(value++);
                    // Signal the consumer that there is data available
                    bufferNotEmpty.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
//                try {
//                    Thread.sleep(1000); // Imitate work
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    return;
//                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    // Wait until there is data to read
                    while (queue.isEmpty()) {
                        bufferNotEmpty.await();
                    }
                    // Read the data from the buffer
                    int value = queue.poll();
                    System.out.println("Consumed " + value);
                    // Signal the producer that there is space available
                    bufferNotFull.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
//                try {
//                    Thread.sleep(1000); // Imitate work
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    return;
//                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerWithLock main = new ProducerConsumerWithLock();
        Thread producerThread = new Thread(main.new Producer());
        Thread consumerThread = new Thread(main.new Consumer());
        producerThread.start();
        consumerThread.start();
    }
}
