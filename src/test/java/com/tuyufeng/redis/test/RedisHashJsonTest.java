package com.tuyufeng.redis.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tuyufeng.redis.domain.User;
import com.tuyufeng.redis.utils.RandomUitl;
import com.tuyufeng.redis.utils.StringUtil;

@ContextConfiguration(locations="classpath:spring-beans2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisHashJsonTest {
	@Resource
	private RedisTemplate<String,User>  redisTemplate;
	
	@Test
	public void test_hash_json() {
		/**
		 * 如果是Map<String,User>,数据库中的values值是user对象格式
		 *如果是HashMap<String, Serializable> ，数据库汇总的values值是乱码（序列化之后的值）
		 */
		Map<String,User>  map = new HashMap<String, User>();
		for (int i = 0; i < 100000; i++) {
			map.put("e_"+i, new User(i,StringUtil.generateChineseName()+StringUtil.randomChineseString(2),RandomUitl.randomString(3) , "13"+RandomUitl.randomString(9)));
		}
		long start = System.currentTimeMillis();
		 
		redisTemplate.opsForHash().putAll("userkey", map);
		
		long end = System.currentTimeMillis();
		
		System.out.println("采用Hash,jdk序列化存储的时间：" + (end - start));
		//采用Hash,jdk序列化存储的时间：1053
	}
}
