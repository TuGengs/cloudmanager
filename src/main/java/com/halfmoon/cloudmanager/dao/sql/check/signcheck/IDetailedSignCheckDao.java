package com.halfmoon.cloudmanager.dao.sql.check.signcheck;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.DetailInfo;
import com.halfmoon.cloudmanager.model.check.dto.LackTimes;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UserDetail;

public interface IDetailedSignCheckDao{
	
	
	public boolean updateOughtTimes(int single_check_id);
	
	public int is_Here(int single_id,int user_id);
	
	public int getLackTimes(int check_id,int user_id);
	
	public int getOughtTimes(int single_id,int user_id);
	
	public boolean updateStatus(int single_id,int user_id,int ought,int status,String tip);
	
	public boolean updateStatusWithoutOught(int single_id,int user_id,int status,String tip);
	
	public boolean handleSign(int single_id,int user_id,int status);
	
	public boolean saveList(List<DetailedSignInCheck> list);
	/**
	 * 获取单项检查的缺到次数 = 应到次数-签到次数 
	 * @param single_id
	 * @param sysnum
	 * @return
	 */
	public int getSingleLackTimes(int single_id,int user_id);

	boolean deleteByCheckId(int check_id);
	/**
	 * 通过缺到次数对id进行排序
	 * @param check_id
	 * @param list
	 * @return
	 */
	public List<LackTimes> getUserIdSortByLackTimes(int check_id,List<UInfo> list);
	
	public List<DetailInfo> test(int check_id,int type);

	List<Integer> getSingleIdByUserIdAndCheckId(int user_id, int check_id);

	boolean deleteBySingleId(int single_id);

	boolean saveSingleRemark(int single_id, int user_id, String remark);

	UserDetail getUserDetail(int user_id, int single_id);
}