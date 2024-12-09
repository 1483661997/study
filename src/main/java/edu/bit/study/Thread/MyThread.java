package edu.bit.study.Thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        // 任务代码
        System.out.println("Thread is running...");
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();

        t.start();  // 启动线程
    }
}
