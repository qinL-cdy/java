package com.demo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     * 编号
     */
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;
}
