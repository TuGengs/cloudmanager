package com.halfmoon.cloudmanager.dao.nosql;

import javax.annotation.Resource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BaseDao {

	@Resource
	private JedisPool jedisPool;
	
	public static final String SEPARATOR = ":";	// 分隔符
	
	// 坑爹的SpringMVC在filter里没法用注解获取bean，只能用这种方式了
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	protected Jedis getConnection() {
		return jedisPool.getResource();
	}
	
	protected String spliceWords(String prefix, String... keyNames) {
		
		StringBuffer newKey = new StringBuffer(prefix);
		for(String keyName : keyNames) {
			newKey.append(SEPARATOR).append(keyName);
		}
		
		return newKey.toString();
	
	}
	
}
