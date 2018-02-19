package com.halfmoon.cloudmanager.dao.sql.check;

import java.util.List;

public interface ICheckDao {

	/**
	 * 获取单次检查中所有的学生签到记录
	 * @param single_check_id 单次检查的id
	 * @return 所有签到记录
	 */
	public List<?> getAllDetailsOfSingleCheck(int single_check_id);
	
	/**
	 * 获取指定周期内的单次检查
	 * @param cycleTime 周期数
	 * @return 该周期内的所有单次检查
	 */
	public List<?> getSingleChecksByCycle(int check_id, int cycle_time);
	
	/**
	 * 获取该项检查的总信息（按日期归档）
	 * 前端怕是要打死我哦。。。
	 * @param check_id 检查id
	 * @return 总信息
	 */
	public List<?> getRawResultData(int check_id);
	
	/**
	 * 查看个人在该项检查中的总信息
	 * @param user_id 用户id
	 * @param check_id 检查id
	 * @return
	 */
	public List<?> getRawResultData(int user_id, int check_id);
	
}
