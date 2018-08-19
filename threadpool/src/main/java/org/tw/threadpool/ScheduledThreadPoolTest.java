package org.tw.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledThreadPoolTest {
    private static Logger logger = LoggerFactory.getLogger(ScheduledThreadPoolTest.class);

    public static void main(String[] args) throws InterruptedException {

        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(50);
        for (int i = 0; i < 1; i++) {
            Task worker = new Task("task-" + i);
            // 只执行一次
          //scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
            // 周期性执行，每5秒执行一次
            scheduledThreadPool.scheduleAtFixedRate(worker, 0, 2, TimeUnit.SECONDS);
            //scheduledThreadPool.submit(worker);
        }
        Thread.sleep(100000);
        System.out.println("Shutting down executor...");
        // 关闭线程池
        scheduledThreadPool.shutdown();
        boolean isDone;
        // 等待线程池终止
        do {
            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("awaitTermination...");
        } while (!isDone);
        System.out.println("Finished all threads");
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    private static Logger logger = LoggerFactory.getLogger(ScheduledThreadPoolTest.class);

    @Override
    public void run() {
        logger.info("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("name = " + name + ", endTime = " + new Date());
    }
}