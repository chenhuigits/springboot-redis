package com.hui.redisdemo.entity;

import java.util.Date;

public class Mytest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("张三");
        student.setId(1);
        student.setScore(95.5);
        student.setBirthday(new Date());
    }
}
