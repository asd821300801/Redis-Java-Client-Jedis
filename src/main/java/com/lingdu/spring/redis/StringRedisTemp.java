package com.lingdu.spring.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.lingdu.common.util.SpringUtil;

/**
 * Redis 操作 String
 * @author LingDu
 */
public class StringRedisTemp {
	private static StringRedisTemplate stringRedisTemplate;
	private static ValueOperations<String, String> stringOFV;
	
	static{
		SpringUtil.init("classpath:application.xml");
		stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");
		stringOFV = stringRedisTemplate.opsForValue();
	}
	
	public  void set(String key,String value){
		stringOFV.set(key, value);
	}
	
	public String get(String key){	
		return stringOFV.get(key);
	}
	
	public static void main(String[] args) {
		StringRedisTemp stringRedisTemp = new StringRedisTemp();
		stringRedisTemp.set("string_key", "string_value");
		System.out.println(stringRedisTemp.get("string_key"));
	}
}
