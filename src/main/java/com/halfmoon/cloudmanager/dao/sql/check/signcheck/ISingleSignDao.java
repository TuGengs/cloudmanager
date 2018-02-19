package com.halfmoon.cloudmanager.dao.sql.check.signcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
/**
 * 
 * @author xiaogao.XU
 *
 */
/**
 * 
 * @author xiaogao.XU
 *
 */
public interface ISingleSignDao{

	
	/**
	 * 获取所有的单项检查
	 * @param check_id 对应的总检查ID
	 * @return List 
	 */
	public List<SingleSignInCheck> getAll(@Param("sign_in_check_id")int check_id);
	/**
	 * 获取检查ID
	 * @param single_id
	 * @return
	 */
	public int getCheck_id(int single_id);
	
	public List<Integer> getSingleId(int check_id);
	boolean deleteByCheckId(int check_id);
	boolean updateRemark(int single_id, String content);
	String getRemark(int single_id);
	
}