package com.lxf.asyncspringboot.async;

import com.lxf.asyncspringboot.entity.User;
import com.lxf.asyncspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用@Async 需要满足： 在A类中使用 B类的某个(@Async)方法,与@Transactional类似
 *
 * @author 小66
 * @Description
 * @create 2019-08-05 15:44
 **/
/*
    @Async异步：失效原因
    1.没有在@SpringBootApplication启动类当中添加注解@EnableAsync注解。
    2.异步方法使用注解@Async的返回值只能为void或者Future。-->Future不可调用get()方法、因为get()是阻塞的。
    3.没有走Spring的代理类。因为@Transactional和@Async注解的实现都是基于Spring的AOP，
    而AOP的实现是基于动态代理模式实现的。那么注解失效的原因就很明显了，
    有可能因为调用方法的是对象本身而不是代理对象，因为没有经过Spring容器。
* */
@Component
public class AsyncUserUtil {

    @Autowired
    private UserService service;


    /**
     * 如果需要异步调用,则返回的Future对象不可调用get()方法、因为get()是阻塞的。
     *
     * @param user
     * @return
     */
    @Async
    public Future<User> insertByAsyncAnnotation(User user) {

        return new AsyncResult<>(service.insertUser(user));
    }


    public void insertByThread(User user) {
        Executors.newCachedThreadPool().execute(() -> service.insertUser(user));
    }

}
