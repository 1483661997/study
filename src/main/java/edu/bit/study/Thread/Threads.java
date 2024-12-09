package edu.bit.study.Thread;

public class Threads {


    private int a = 0;
    public void print(){
        System.out.println(a++);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.run();
    }
}
