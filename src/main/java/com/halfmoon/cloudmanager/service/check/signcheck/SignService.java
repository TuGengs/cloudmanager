package com.halfmoon.cloudmanager.service.check.signcheck;

import com.google.gson.JsonObject;
import com.halfmoon.cloudmanager.model.check.Sign;

public interface SignService {
	
	
	public JsonObject getCheckList(String sysnum);
	
	
	public void save(Sign sign);
	/**
	 * 关闭自身的其他辅助检查
	 * @param check_id 检查ID
	 * @param check_auto_id 检查自增ID
	 */
	public void closeSelf(int check_id,int check_auto_id);
	/**
	 * 关闭这项检查的所有辅助检查
	 * @param check_id
	 * @param single_check_id
	 */
	public void closeAll(int check_id,int single_check_id);
	/**
	 * 关闭自身这次检查
	 * @param single_check_id
	 * @param check_auto_id
	 */
	public void closeSelfOnce(int single_check_id,int check_auto_id);
	
	/**
	 * 是否有这条数据
	 * @param single_auto_id
	 * @param check_auto_id
	 * @return Sign
	 */
	public Sign get(int single_auto_id,int check_auto_id);
	
	public void update(Sign sign);
	/**
	 * 签到是否开启
	 * @param single_check_id
	 * @param check_auto_id
	 * @return 
	 */
	public boolean isOpen(int single_check_id,int check_auto_id);
	
	/**
	 * 获取Boss中开启的签到 没有则返回空
	 * @param check_id 签到id
	 * @return
	 */
	public Sign getByBossOpen(int check_id);
	/**
	 * 获取辅助人员的签到状态
	 * @param check_id
	 * @param check_auto_id
	 * @return
	 */
	public Sign getByHelperOpen(int check_id,int check_auto_id);
	/**
	 * 获取开启状态
	 * @param check_id
	 * @param check_auto_id
	 * @return
	 */
	public int getStatus(int check_id,int check_auto_id);
	
	public boolean updateAddress(int check_auto_id,int single_id,double latitude,double longitude);	
	/**
	 * 获取辅助签到的信息
	 * 当不需要某个值时 传入-1
	 * 需要考虑当情况为多的情况 并不适用
	 * @param single_id
	 * @param check_auto_id
	 * @param check_id
	 * @param is_open
	 * @return Sign
	 */
	public Sign getSignBySingleIdAndOpen(int single_id, 
			int check_auto_id,int check_id,int is_open);


	boolean deleteByCheckId(int check_id);


	public boolean deleteByAutoId(int auto_id);
}
