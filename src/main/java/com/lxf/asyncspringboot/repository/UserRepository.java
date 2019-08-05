package com.lxf.asyncspringboot.repository;

import com.lxf.asyncspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 小66
 * @Description
 * @create 2019-08-05 15:36
 **/
@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
