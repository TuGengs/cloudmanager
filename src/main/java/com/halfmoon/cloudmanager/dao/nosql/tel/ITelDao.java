package com.halfmoon.cloudmanager.dao.nosql.tel;

import java.util.ArrayList;

/**
 * 电话号码相关的数据库操作（Redis）
 * @author hzq
 *
 */
public interface ITelDao {
	
	/**
	 * 获取该手机号发送的短信总数和最后一次发送短信的时间
	 * @param tel 手机号
	 * @return 发送的短信总数和最后一次发送短信的时间
	 */
	public ArrayList<String> getSentNumAndTime(String tel);
	
	/**
	 * 该手机号发送的短信次数+1
	 * @param tel 手机号
	 */
	public void incrSentNum(String tel);
	
}
