package com.halfmoon.cloudmanager.dao.nosql.tel.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.nosql.BaseDao;
import com.halfmoon.cloudmanager.dao.nosql.tel.ITelDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 封装了对电话号码的相关操作，主要是记录短信发送的相关内容
 * @author hzq
 *
 */
@Repository
public class TelDao extends BaseDao implements ITelDao {
	
	private static final String SMS_RECORD_PREFIX 		= "sms_record"; 	// hash key 短信发送记录前缀，后接手机号
	
	private static final String SMS_SENT_COUNT_FIELD 	= "count"; 			// hash field 短信发送次数字段
	private static final String SMS_SENT_TIME_FIELD	 	= "time";			// hash field 最后一条短信发送时间字段
	
	@Override
	public ArrayList<String> getSentNumAndTime(String tel) {
		
		Jedis jedis = this.getConnection();
		
		String key = this.spliceWords(SMS_RECORD_PREFIX, tel);
		ArrayList<String> value = (ArrayList<String>) jedis.hmget(
				key, SMS_SENT_COUNT_FIELD, SMS_SENT_TIME_FIELD);
		
		jedis.close();
		
		return value;
		
	}
	
	@Override
	public void incrSentNum(String tel) {
		this.incrSentNum(tel, false);
	}

	public void incrSentNum(String tel, boolean firstTime) {
		
		Jedis jedis = this.getConnection();
		
		Transaction trans = jedis.multi();
		String key = this.spliceWords(SMS_RECORD_PREFIX, tel);
		trans.hincrBy(key, SMS_SENT_COUNT_FIELD, 1);
		long now = System.currentTimeMillis() / 1000;	// 系统当前秒数
		trans.hset(key, SMS_SENT_TIME_FIELD, String.valueOf(now));
		if(firstTime) trans.expire(key, 60*60*24);		// 发送超过限制后需等待一天时间，起始时间按第一次发送短信算起
		trans.exec();
		
		jedis.close();
		
	}

}
