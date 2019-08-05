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
@Component
public class AsyncUserUtil {

    @Autowired
    private UserService service;

    @Async
    public Future<User> insertByAsyncAnnotation(User user) {

        return new AsyncResult<>(service.insertUser(user));
    }


    public void insertByThread(User user) {
        Executors.newCachedThreadPool().execute(() -> service.insertUser(user));
    }

}
