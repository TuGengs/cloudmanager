package com.halfmoon.cloudmanager.dao.sql.check.signcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;

/**
 * 用户检查查看表DAO
 * @author xiaogao.XU
 *
 */
public interface IUserViewDao {
	public List<UUInfo> getViewerList(
			@Param("check_id")int check_id,
			@Param("type")int type);
	public boolean deleteViewer(
			@Param("check_id")int check_id,
			@Param("sysnum")int user_id,
			@Param("type")int type);
	boolean deleteByCheckId(int check_id, int type);
	
	boolean deleteByAutoIdAndUserId(int auto_id, int user_id, int type);
	
	List<FirstView> getAllCheckView(int user_id,int type);
	
	public int isExist(int user_id,int check_id,int type);
}
