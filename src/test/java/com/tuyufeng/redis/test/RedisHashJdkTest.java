package com.tuyufeng.redis.test;

import java.io.Serializable;
import java.util.HashMap;

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
 * 
 * @ClassName: RedisHashJdkTest 
 * @Description: 使用redis的hash类型保存十万个user对象，测试所耗时间
 * @author:queen
 * @date: 2019年8月10日 上午9:30:27
 */
@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisHashJdkTest {
	@Resource
	private RedisTemplate<String,User>  redisTemplate;
	
	@Test
	public void test_hash_jdk() {
		HashMap<String, Serializable>  map = new HashMap<>();
		//随机生出100000个数据
		for (int i = 0; i < 100000; i++) {
			map.put("e_"+i, new User(i,StringUtil.generateChineseName()+StringUtil.randomChineseString(2),RandomUitl.randomString(3) , "13"+RandomUitl.randomString(9)));
		}
		
		long start = System.currentTimeMillis();
		
		//存储数据
		redisTemplate.opsForHash().putAll("userkey", map);
		
		long end = System.currentTimeMillis();
		System.out.println("使用redis的hash类型采用jdk序列化存储的时间:"+(end-start));
		//使用redis的hash类型采用jdk序列化存储的时间:1232(不固定)
	}
}
