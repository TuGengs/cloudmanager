package com.halfmoon.cloudmanager.dao.sql.check.signcheck;


import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.dto.ShareInfo;
/**
 * 分享表Dao
 * @author xiaogao.XU
 *
 */
public interface IShareDao {
	
	/**
	 * 根据账号获取分享实体
	 * @param account 账号
	 * @return Share实体
	 */
	public Share getByAccount(String account);
	/**
	 * 获取账号信息 
	 * 账号密码开关
	 * @param check_id
	 * @param type
	 * @return
	 */
	public ShareInfo getInfo(@Param("check_id")int check_id,
			@Param("type")int type);
	/**
	 * 更新密码
	 * @param check_id
	 * @param pwd
	 * @param type
	 * @return
	 */
	public boolean updatePwd(@Param("check_id")int check_id,
			@Param("pwd")String pwd,@Param("type")int type);
	/**
	 * 更新开关的状态
	 * @param check_id
	 * @param is_open
	 * @param type
	 * @return
	 */
	public boolean updateStatus(
			@Param("check_id")int check_id,
			@Param("is_open")int is_open,
			@Param("type")int type);
	boolean deleteByCheckId(int check_id, int type);
}
