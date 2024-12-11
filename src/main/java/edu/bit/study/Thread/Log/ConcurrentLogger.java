package edu.bit.study.Thread.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发日志服务
 * 题目2：并发日志服务
 * 问题描述
 * 设计一个多线程的日志记录系统，它可以从多个源并发接收日志消息，并将它们写入到一个共享的日志文件中。系统应确保日志消息的顺序性和完整性。
 * 要求
 * - 使用 `ReentrantLock` 或 `synchronized` 实现同步。
 * - 考虑使用 `BlockingQueue` 来存储待写入的日志消息。
 * - 实现一个高效的策略，以确保即使在高负载下，日志写入操作也不会成为瓶颈。
 */
public class ConcurrentLogger {
    /**
     * 使用ReentrantLock来同步访问共享资源（日志文件），以确保日志写入的顺序性和完整性。
     * 另外，考虑使用BlockingQueue来存储待写入的日志消息，
     * 这样可以有效地解耦消息的接收和写入过程，提高系统的整体性能。
     */
    private final BlockingDeque<String> deque = new LinkedBlockingDeque<>();
    private final Lock lock = new ReentrantLock();
    private final String logFilePath = "./src/main/java/edu/bit/study/Thread/Log/logs.txt";
    public ConcurrentLogger(){
        //启动线程去消费日志消息然后写到文件中
        Thread logWriterThread = new Thread(() ->{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))){
                while (true){
                    try{
                        String logMsg = deque.take();
                        lock.lock();
                        try {
                            writer.write(logMsg);
                            writer.newLine();
                            writer.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }finally {
                            lock.unlock();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        logWriterThread.start();
    }

    public void log(String message){
        try {
            deque.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ConcurrentLogger logger = new ConcurrentLogger();
        for(int i = 0; i < 10; i++){
            int threadId = i;
            new Thread(() -> {
                for(int j = 0; j < 10; j++){
                    logger.log("Thread" + threadId + "log message" + j);
                }
            }).start();
        }
    }
}
