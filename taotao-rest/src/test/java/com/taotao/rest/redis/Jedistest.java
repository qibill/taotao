package com.taotao.rest.redis;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.taotao.rest.component.JedisClient;


public class Jedistest {

	//单机版测试
	@Test
	public void testJedisSingle() throws Exception {
		//创建一个Jedis对象
		Jedis jedis = new Jedis("192.168.245.128", 6379);
		jedis.set("test", "hello jedis");
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
	}

	//使用连接池
	@Test
	public void testJedisPool() throws Exception {
		//创建一个连接池对象
		//系统中应该是单例的。
		JedisPool jedisPool = new JedisPool("192.168.245.128", 6379);
		//从连接池中获得一个连接
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get("test");
		System.out.println(result);
		//jedis必须关闭
		jedis.close();
		
		//系统关闭时关闭连接池
		jedisPool.close();
		
	}

	@Test
	public void testJedisClientSpring() throws Exception {
		//创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从容器中获得JedisClient对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		//jedisClient操作redis
		jedisClient.set("cliet1", "1000");
		String string = jedisClient.get("cliet1");
		System.out.println(string);
	}

}
