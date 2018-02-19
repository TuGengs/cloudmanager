package com.halfmoon.cloudmanager.dao.sql.check.signcheck;


import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.Sign;

public interface ISignDao {
	
	/**
	 * 获取Sign
	 * @param single_check_auto_id 单项检查自增ID
	 * @param check_auto_id 检查自增ID
	 * @return Sign
	 */
	public Sign get(@Param("single_id")int single_check_auto_id,
			@Param("check_id")int check_auto_id);
	
	/**
	 * 关闭自身这项检查的所有开启状态
	 * 根据检查ID和检查自增ID来判断
	 * @param check_id 
	 * @param check_auto_id
	 */
	public boolean closeSelf(@Param("check_id")int check_id
			,@Param("check_auto_id")int check_auto_id);
	public boolean closeAll(@Param("check_id")int check_id
			,@Param("single_auto_id")int single_check_id);
	
	public boolean closeSelfOnce(@Param("single_check_id")int single_check_id,
			@Param("check_auto_id")int check_auto_id);
	
	/**
	/**
	 * 获取
	 * @param single_check_id 
	 * @param check_auto_id
	 * @return 1 0
	 */
	public int getStatus(@Param("single_check_id")int single_check_id
			,@Param("check_auto_id")int check_auto_id);
	/**
	 * 获取Boss中开启的签到 没有则返回空
	 * @param check_id 签到id
	 * @return
	 */
	public Sign getByBossOpen(int check_id);
	
	public Sign getByHelperOpen(@Param("check_id")int check_id,
			@Param("check_auto_id")int check_auto_id);
	
	public int getIs_open(int check_id,int check_auto_id);
	
	public boolean updateAddress(int check_auto_id,int single_id,double latitude,double longitude);
	
	public Sign getSignBySingleIdAndOpen(int single_id,
			int check_auto_id,int check_id,int is_open);

	boolean deleteByCheckId(int check_id);

	boolean deleteByAutoId(int auto_id);
}
