package edu.bit.study.Thread.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    private static final int CAPACITY = 10;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Object lock = new Object();

    class Producer implements Runnable {
        @Override
        public void run() {
            int value = 0;
            while (true) {
                synchronized (lock) {
                    while (queue.size() == CAPACITY) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Producer was interrupted");
                        }
                    }
                    queue.offer(value++);
                    System.out.println("Produced " + value);
                    lock.notifyAll(); // Notify the consumer
                    try {
                        Thread.sleep(1000); // Imitate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Consumer was interrupted");
                        }
                    }
                    int value = queue.poll();
                    System.out.println("Consumed " + value);
                    lock.notifyAll(); // Notify the producer
                    try {
                        Thread.sleep(1000); // Imitate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();
        Thread producerThread = new Thread(example.new Producer());
        Thread consumerThread = new Thread(example.new Consumer());

        producerThread.start();
        consumerThread.start();
    }
}
