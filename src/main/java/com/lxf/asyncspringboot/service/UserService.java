package com.lxf.asyncspringboot.service;

import com.lxf.asyncspringboot.entity.User;
import com.lxf.asyncspringboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 小66
 * @Description
 * @create 2019-08-05 15:38
 **/
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User insertUser(User user) {
        User save = repository.save(user);
        if (save==null)
            throw new RuntimeException("插入数据失败");
        log.info("插入数据成功 :{}",save);
        return save;
    }
}
