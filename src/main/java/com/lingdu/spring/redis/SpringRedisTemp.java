package com.lingdu.spring.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.lingdu.common.util.SpringUtil;

/**
 * RedisTemplate 的简单使用
 * @author LingDu
 */
@SuppressWarnings("unchecked")
public class SpringRedisTemp {
	
	private static RedisTemplate<String,Object> redisTemplate;
	private static ValueOperations<String, Object> valueOper;
	
	static{
		SpringUtil.init("classpath:application.xml");
		redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
		valueOper =  redisTemplate.opsForValue();
	}
	
	//设置K-V
	public void set(String key,String value){
		valueOper.set(key, value);
	}
	
	//获取V
	public String get(String key){
		return (String) valueOper.get(key);
	}
	
	//通过K删除对应的V
	public void remove(String key){
		redisTemplate.delete(key);
	}
	
	public static void main(String[] args) {
		SpringRedisTemp springRedisTemp = new SpringRedisTemp();
		springRedisTemp.set("key", "value1");
		System.out.println(springRedisTemp.get("key"));
		
		//删除
		springRedisTemp.remove("key");
		System.out.println("删除之后：" + springRedisTemp.get("key"));
	}
	
}
