package com.lxf.asyncspringboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 小66
 * @create 2019-08-05 13:41
 **/

@Component
public class AsyncClass {

    @Async
    public void asyncMethod_1() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        System.out.println("asyncMethod_1 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    @Async
    public void asyncMethod_2() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        System.out.println("asyncMethod_2 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    @Async
    public void asyncMethod_3() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        System.out.println("asyncMethod_3 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    /**
     *  利用spring的异步操作,必须使用@Async和@EnableAsync注解
     * @param sleep
     */
    @Async
    public void asyncMethod_4(Long sleep) {
        long timeMillis = System.currentTimeMillis();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("asyncMethod_" + sleep + " totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    /**
     *  利用线程池进行异步操作时,不需要@Async和@EnableAsync注解
     * @param sleep
     */
    public void threadAsyncMethod(Long sleep) {
        Executors.newCachedThreadPool().execute(() -> {//没有线程池的时候,即属于Sync的操作、
            long timeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadAsyncMethod_" + sleep + " totalMillis = " + (System.currentTimeMillis() - timeMillis));
        });
    }

    /**
     *   进行异步操作,并获取返回值、
     * @return
     */
    @Async
    public Future<String> asyncCallback(){

        return new AsyncResult<>("异步操作执行完成");
    }

}
