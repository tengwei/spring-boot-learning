package org.tw.threadpool;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Jstack {
    public static void main(String[] args) {

        runnable();     // 1
           // blocked();      // 2
            //waiting();      // 3
            //timedWaiting(); // 4
    }

    public static String pid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return name.split("@")[0];
    }

    public static void runnable() {
        List<byte[]> list=new ArrayList<>();
        long i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[1000000]);
            i++;
        }
    }

    public static void blocked() {
        final Object lock = new Object();
        new Thread() {
            public void run() {
                synchronized (lock) {
                    System.out.println("i got lock, but don't release");
                    try {
                        Thread.sleep(1000L * 1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();

        try { Thread.sleep(100); } catch (InterruptedException e) {}

        synchronized (lock) {
            try {
                System.out.println("i got lock, but don't release---------------");
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void timedWaiting() {
        final Object lock = new Object();
        synchronized (lock) {
            try {
                System.out.println("i got lock, but don't release------------");
                lock.wait(30 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void waiting() {
        final Object lock = new Object();
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
            }
        }
    }
}
