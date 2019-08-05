package com.lxf.asyncspringboot.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 小66
 * @Description
 * @create 2019-08-05 15:35
 **/
@Data
@Table
@Entity
public class User {

    /*
    *   如果使用自增主键,异步批量插入时,数据只能插入一条。
    * */
    @Id
    private String id;

    private String name;

    private Integer age;
}
