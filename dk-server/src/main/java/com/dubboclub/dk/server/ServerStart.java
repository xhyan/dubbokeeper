package com.dubboclub.dk.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @date: 2016/1/5.
 * @author:bieber.
 * @project:dubbokeeper.
 * @package:com.dubboclub.dk.server.
 * @version:1.0.0
 * @fix:
 * @description: 描述功能
 */
public class ServerStart {

    private static volatile boolean running=false;

    private static final String STOP_COMMAND="stop";

    private static final String START_COMMAND="start";

    public static void main(String[] args) throws InterruptedException {
        final ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/*.xml");
        ac.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                ac.close();
            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }

//    private static void stop(){
//        running=false;
//        ServerStart.class.notifyAll();
//    }
//
//    private static void start(){
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/*.xml");
//        classPathXmlApplicationContext.start();
//        running=true;
//        synchronized (ServerStart.class) {
//            while (running) {
//                try {
//                    ServerStart.class.wait();
//                } catch (Throwable e) {
//                }
//            }
//            classPathXmlApplicationContext.stop();
//        }
//    }

}
