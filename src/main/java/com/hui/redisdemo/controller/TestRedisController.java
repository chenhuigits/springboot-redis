package com.hui.redisdemo.controller;

import com.hui.redisdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class TestRedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/set")
    public void set(@RequestBody Student stu){
        redisTemplate.opsForValue().set("student2",stu);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        Object o = redisTemplate.opsForValue().get(key);
        return (Student) o;
    }
    /**
     * Redis 5 种数据类型
     */
    // 字符串
    @GetMapping("/string")
    public String stringTest(){
        redisTemplate.opsForValue().set("str","Hello World");
        String str = (String) redisTemplate.opsForValue().get("str");
        return str;
    }
    // 列表
    @GetMapping("/list")
    public List<String> listTest(){
        ListOperations<String,String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list","Hello");
        listOperations.leftPush("list","World");
        listOperations.leftPush("list","Java");
        Long listLen = listOperations.size("list");
        List<String> list = listOperations.range("list", 0, listLen-1);
        return list;
    }
    // 集合
    @GetMapping("/set")
    public Set<String> setTest(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("set","Hello");
        setOperations.add("set","Hello");
        setOperations.add("set","World");
        setOperations.add("set","World");
        setOperations.add("set","Java");
        setOperations.add("set","Java");
        Set<String> set = setOperations.members("set");
        return set;
    }

    // 有序集合
    @GetMapping("/zset")
    public Set<String> zsetTest(){
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","Hello",1);
        zSetOperations.add("zset","World",2);
        zSetOperations.add("zset","Java",3);
        Set<String> zset = zSetOperations.range("zset", 0, 2);
        return zset;
    }
    // 哈希
    @GetMapping("/hash")
    public String hashTest(){
        HashOperations<String,String,String>
                hashOperations = redisTemplate.opsForHash();
        hashOperations.put("hashMap1","key1","hello");
        hashOperations.put("hashMap2","key2","world");
        hashOperations.put("hashMap3","key3","!");
        String s = hashOperations.get("hashMap2", "key2");
        return s;
    }


}
