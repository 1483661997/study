package edu.bit.study.Thread.ExecutorService;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolExample {
    public static void main(String[] args) {
        //创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //用于存储未来结果的Future列表
        Future<Integer>[] results = new Future[10];

        //提交10个任务到线程池
        for(int i = 0; i < 10; i++){
            final  int taskId = i;
            results[i] = executor.submit(() -> {
                Thread.sleep(new Random().nextInt(500));
                System.out.println("Task " + taskId + " completed.");
                return taskId;
            });
        }
        executor.shutdown();

        try {
            for(int i = 0; i < results.length; i++){
                //获取任务执行的结果，会堵塞直到任务完成
                Integer result = results[i].get();
                System.out.println("Result of task "  + i + ": " + result );
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
