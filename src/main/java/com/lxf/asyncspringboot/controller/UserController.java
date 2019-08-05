package com.lxf.asyncspringboot.controller;

import com.lxf.asyncspringboot.async.AsyncUserUtil;
import com.lxf.asyncspringboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * @author 小66
 * @Description
 * @create 2019-08-05 15:53
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private AsyncUserUtil asyncUser;

    @GetMapping("/insertAll")
    public String insertAll() {
        /*
         *   //TODO: 当user对象在循环外时,只有annotation方式可以正常的异步插入数据、
         *   User user = new User();
         * */
        for (int i = 0; i < 10; i++) {
            User user = new User();//当user对象在循环内时,thread和annotation都可以正常的异步插入数据、
            int nextInt = new Random().nextInt(30);
            user.setId(UUID.randomUUID().toString());
            user.setAge(nextInt);
            user.setName("jack-" + nextInt);
            log.info("user is:{}", user);
            asyncUser.insertByAsyncAnnotation(user);
//            asyncUser.insertByThread(user);
        }
        return "success";
    }

}
