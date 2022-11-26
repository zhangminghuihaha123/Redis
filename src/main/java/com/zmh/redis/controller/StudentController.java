package com.zmh.redis.controller;

import cn.hutool.core.util.RandomUtil;
import com.zmh.redis.entry.Student;
import com.zmh.redis.mapper.StudentMapper;
import com.zmh.redis.util.LowUp.LowUpChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody Student student){
        ListOperations listOperations = redisTemplate.opsForList();
        for(int i=0;i<10;i++){
            listOperations.leftPush("student",student+""+i);
        }
    }

    @GetMapping("/get")
    public List<Student> get(){
        ListOperations listOperations = redisTemplate.opsForList();
        return listOperations.range("student",0,9);
    }

    /*Redis五种数据类型存储*/
    public void test1(){
        /*字符串----->String*/ redisTemplate.opsForValue().set("string","hello String");
        /*列表----->List*/
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list","hello ！ LIst1");
        listOperations.leftPush("list","hello ！ LIst2");
        listOperations.leftPush("list","hello ！ LIst3");
        listOperations.range("list",0,2);
        /*Set集合和List类似，返回是SetOperations,对于相同数据不会存储--------->Set*/
        /*有序Set集合和Set类似，返回是zSetOperations,对于相同数据不会存储并且后面可以设定顺序--------p->Set*/
        /*哈希*//*hashMap有key和value*//*Redis中HashOperations包含key,hashkey,value*/
        /*key是每一组数据的ID，hashkey和value是一组完整的Hashmap数据，通过KEY来区分不同的HashMap*/
        HashOperations<String,String,Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("map1","key1","value1");
        hashOperations.put("map1","key2","value2");
        hashOperations.get("map1","key1");
    }


    /*设置时间，使用REDIS存取验证码*/
    @GetMapping("/yzm")
    public String yzm(){
        String code = RandomUtil.randomString(6);  /*生成随机六位包含数字和字母，但这字母属于小写*/
        String TurnCode = LowUpChar.ReturnActionUpDownSystem(code);  /*我们把小写字母一部分替换成大写，50%的概率命中*/
        redisTemplate.opsForValue().set("code",TurnCode,30, TimeUnit.SECONDS);
        return TurnCode;
    }

    /*验证验证码*/
    @GetMapping("/yzm/{code}")
    public String pick(@PathVariable  String code){
        if(code.equals(redisTemplate.opsForValue().get("code"))){
            return "验证成功!";
        }else {
            if(redisTemplate.opsForValue().get("code") == null){
                return "验证码不存在!";
            }else {
                return "验证失败!";
            }
        }
    }



}
