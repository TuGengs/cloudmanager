package com.halfmoon.cloudmanager.dao.sql.check.signcheck;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.SignHelp;
/**
 * 签到型检查DAO
 * @author xiaogao.XU
 *
 */
public interface ISignCheckDao {
	
	/**
	 * 更新检查ID 使check_id 和 id 保持一致
	 * @param checkId 检查ID
	 * @param id 对应ID
	 */
	public boolean updateCheckId(@Param("check_id")int checkId,@Param("id")int id);
	
	public int getAutoId(@Param("check_id")int check_id,
			@Param("sysnum")int user_id);
	
	/**
	 * 根据创建人ID获取所有检查
	 * @param userId 创建者sysnum 
	 * @return FirstView List
	 */
	public List<FirstView> getAllByUserId(int user_id);
	

	/**
	 * 获取check_id
	 * 使用了MySQL的IFNULL和MAX方法使可能返回的NULL值转换为数字
	 * select IFNULL(MAX(check_id),-1) as check_id from table
	 * @param code 四位码
	 * @return 检查ID
	 */
	public int getCheckIDByCode(String code);
	
	public String getNameByID(int check_auto_id);
	
	
	public List<SignHelp> getSignHelps(int check_id);
	
	public int getUserId(int check_auto_id);

	FirstView getDName(int check_id);

	boolean updateCheckName(int check_id, String check_name);
	
	boolean updateSubName(int check_id,String sub_name);

	boolean deleteByCheckId(int check_id);
	
	boolean updateCycle(Map<String,Object> map);
	
}
