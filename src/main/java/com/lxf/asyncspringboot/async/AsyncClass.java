package com.lxf.asyncspringboot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 尽量使用@Aysnc的方式进行异步处理、(必须要有@EnableAysnc注解)
 * <p>
 * 使用@Async 需要满足： 在A类中使用 B类的某个(@Async)方法,与@Transactional类似
 *
 * @author 小66
 * @create 2019-08-05 13:41
 **/

@Component
@Slf4j
public class AsyncClass {

    @Async(value = "taskExecutor")
    public void asyncMethod_1() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        log.info("asyncMethod_1 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    @Async(value = "taskExecutor")//是否指定具体的线程池,都会使用config中配置的线程池。
    public void asyncMethod_2() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        log.info("asyncMethod_2 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    @Async
    public void asyncMethod_3() throws InterruptedException {
        long timeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        log.info("asyncMethod_3 totalMillis = " + (System.currentTimeMillis() - timeMillis));
    }

    /**
     * 利用spring的异步操作,必须使用@Async和@EnableAsync注解
     *
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
     * 利用线程池进行异步操作时,不需要@Async和@EnableAsync注解
     *
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
     * 进行异步操作,并获取返回值、
     *
     * @return Future<T>、可以通过future.get() 获取T对象、
     */
    @Async
    public Future<String> asyncCallback() {

        return new AsyncResult<>("异步操作执行完成");
    }

}
