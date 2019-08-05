package com.lxf.asyncspringboot.controller;

import com.lxf.asyncspringboot.async.AsyncClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * 测试使用@Async注解方式和Executors线程池方式 完成异步调用、
 *
 * @author 小66
 * @create 2019-08-05 13:44
 **/
@RestController
public class AsyncController {

    private List<Long> longList;

    @Autowired
    private AsyncClass asyncClass;

    @GetMapping("/annotation")
    public String startAnnotationAsync() throws InterruptedException {
        System.out.println("longList = " + longList.size());
        long timeMillis = System.currentTimeMillis();
        asyncClass.asyncMethod_3();
        asyncClass.asyncMethod_2();
        asyncClass.asyncMethod_1();
        System.out.println("EndAsync totalMillis = " + (System.currentTimeMillis() - timeMillis));
        return "AnnotationAsync 耗时 :" + (System.currentTimeMillis() - timeMillis);
    }

    @GetMapping("/thread")
    public String startThreadAsync() {
        long startMillis = System.currentTimeMillis();
        List<Long> longList = getListSync();
        System.out.println("同步获取List的时间 = " + (System.currentTimeMillis()-startMillis));
        long timeMillis = System.currentTimeMillis();
        for (Long aLong : longList) {
            System.out.println("aLong = " + aLong);
            asyncClass.threadAsyncMethod(aLong);
        }

        System.out.println("EndAsync totalMillis = " + (System.currentTimeMillis() - timeMillis));

        return "ThreadAsync 耗时 :" + (System.currentTimeMillis() - timeMillis);
    }

    @GetMapping("/longList")
    public String index() {
        long startMillis = System.currentTimeMillis();
        this.longList = getListAsync();
        System.out.println("异步获取List的时间 = " + (System.currentTimeMillis()-startMillis));
        //此时longList.size()=0;其他方法再获取此longList对象时,异步操作已经生成10万数据.
        System.out.println("longList = " + longList.size());
        return "Async 耗时 :" + (System.currentTimeMillis() - startMillis);
    }

    private static List<Long> getListSync() {
        List<Long> longList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            longList.add(Math.abs(new Random().nextInt(1000)) + 2000L);
        }
        return longList;
    }

    private static List<Long> getListAsync() {
        List<Long> longList = new ArrayList<>();
        Executors.newCachedThreadPool().execute(()->{
            for (int i = 0; i < 100000; i++) {
                longList.add(Math.abs(new Random().nextInt(1000)) + 2000L);
            }
        });
        return longList;
    }
}
