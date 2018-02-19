package com.halfmoon.cloudmanager.dao.nosql.check.impl;

import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.nosql.BaseDao;
import com.halfmoon.cloudmanager.dao.nosql.check.ICheckDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@Repository
public class CheckDao extends BaseDao implements ICheckDao {
	
	private static final String CODE_MAPPING_PREFIX = "code_mapping";
	private static final String ID_MAPPING_PREFIX = "id_mapping";
	
	private static final int CODE_EXPIRE_TIME = 60*60*24;
	
	@Override
	public void setCode(int check_id, String code, Class<?> clazz) {
		
		Jedis jedis = this.getConnection();
		
		Transaction trans = jedis.multi();
		
		String key1 = this.spliceWords(ID_MAPPING_PREFIX, clazz.getSimpleName(), String.valueOf(check_id));
		String value1 = code;
		String key2 = this.spliceWords(
				CODE_MAPPING_PREFIX, code);
		String value2 = this.spliceWords(clazz.getSimpleName(), String.valueOf(check_id));
		
		trans.set(key1, value1, "nx", "ex", CODE_EXPIRE_TIME);
		trans.set(key2, value2, "nx", "ex", CODE_EXPIRE_TIME);
		
		trans.exec();
		
		jedis.close();
		
	}
	
	public String getTypeAndIdByCode(String code) {
		
		Jedis jedis = this.getConnection();
		
		String key = this.spliceWords(
				CODE_MAPPING_PREFIX, code);
		String value = jedis.get(key);
		
		jedis.close();
		
		return value;
		
	}
	
	public String getCodeByTypeAndId(int check_id, Class<?> clazz) {
		
		Jedis jedis = this.getConnection();
		
		String key = this.spliceWords(ID_MAPPING_PREFIX, clazz.getSimpleName(), String.valueOf(check_id));
		String value = jedis.get(key);
		
		jedis.close();
		
		return value;
		
	}
	
}
