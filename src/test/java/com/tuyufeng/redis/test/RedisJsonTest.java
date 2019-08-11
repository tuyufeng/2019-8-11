package com.tuyufeng.redis.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tuyufeng.redis.domain.User;
import com.tuyufeng.redis.utils.RandomUitl;
import com.tuyufeng.redis.utils.StringUtil;

/**
 * 使用的是list类型存储（key）
 * @ClassName: RedisJsonTest 
 * @Description: 使用json序列化的方式将十万个user对象保存到redis，
 *                测试所耗时间
 *                
 *       <property name="keySerializer" >    
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer" />    
        </property>    
        <property name="valueSerializer" >    
            <bean class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer" />    
        </property>    
        <property name="hashKeySerializer">    
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>    
        </property>    
        <property name="hashValueSerializer">    
            <bean class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer"/>    
        </property>          
 * @author:queen
 * @date: 2019年8月10日 上午9:29:21
 */
@ContextConfiguration(locations="classpath:spring-beans2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisJsonTest {
	@Resource
	private RedisTemplate<String,User>  redisTemplate;
	
    @Test
    public void test_jdk() {
       List<User> list = new ArrayList<>();
       //随机生成10万个user对象
       for (int i = 0; i < 100000; i++) {
		  list.add(new User(i, StringUtil.generateChineseName()+StringUtil.randomChineseString(2),RandomUitl.randomString(3) , "13"+RandomUitl.randomString(9)));
	   }
    	
       //测试所耗时间
       long start = System.currentTimeMillis();
       for (User user : list) {
    	  //将数据保存到redis数据库中 
		  redisTemplate.opsForValue().set("u_"+user.getId(), user);
	   }
       
       long end = System.currentTimeMillis();
       System.out.println("采用json序列化存储的时间:"+(end-start));
       //采用json序列化存储的时间:24890(时间不是固定的)
    }
	
}
