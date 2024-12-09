package edu.bit.study.Reflection;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Client {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        System.out.println("123");
        String str;
        str = "String";
        str = "1234";
        System.out.println(str);

    }
}
