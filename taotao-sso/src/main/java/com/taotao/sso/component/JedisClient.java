package com.taotao.sso.component;

public interface JedisClient {
	
	/** 添加缓存  */ 
	String set(String key, String value);
	
	/** 取出缓存  */ 
	String get(String key);
	
	/** 删除缓存  */
	Long del(String key);
	
	/** 添加Hash缓存  */
	Long hset(String key, String item, String value);
	
	/** 取出Hash缓存  */
	String hget(String key, String item);
	
	/** 删除Hash缓存  */ 
	Long hdel(String key, String item);
	
	Long incr(String key);
	
	Long decr(String key);
	
	Long expire(String key, int second);
	
	Long ttl(String key);
	
}
