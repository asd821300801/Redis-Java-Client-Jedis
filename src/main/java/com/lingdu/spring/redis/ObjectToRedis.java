package com.lingdu.spring.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.lingdu.common.util.SpringUtil;

/**
 * 存储对象到Redis
 * 
 * @author LingDu
 */
@SuppressWarnings("unchecked")
public class ObjectToRedis {
	private static RedisTemplate<String, Object> redisTemplate;
	private static ValueOperations<String, Object> valueOper;

	static {
		SpringUtil.init("classpath:application.xml");
		redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
		valueOper = redisTemplate.opsForValue();
	}

	// 设置K-V
	public void setObject(String key, Object value) {
		valueOper.set(key, value);
	}

	// 获取V
	public String getObject(String key) {
		return (String) valueOper.get(key);
	}

	// 通过K删除对应的V
	public void removeObject(String key) {
		redisTemplate.delete(key);
	}

	public static void main(String[] args) {
		User u = new User();
		u.setUserId("x101");
		u.setUsername("LingDu");
		u.setPassword("123456");

		valueOper.set("user", u);
		System.out.println(valueOper.get("user"));

		ObjectToRedis objectToRedis = new ObjectToRedis();
		objectToRedis.removeObject("user");
		System.out.println("删除之后的对象：" + valueOper.get("user"));
	}

}

/**
 * 用于测试的对象,必须实现序列化接口
 * 
 * @author LingDu
 */
class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

}
