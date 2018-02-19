package com.halfmoon.cloudmanager.service.check.signcheck;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.UserView;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
/**
 * 用户检查查看表Service接口
 * @author xiaogao.XU
 *
 */
public interface UserViewService {
	
	public void save(UserView userView);
	
	public void update(UserView userView);
	
	public void delete(int id);
	
	public UserView get(int id);
	/**
	 * 获取对应检查ID的sysnum
	 * @param check_id
	 * @param type
	 * @return
	 */
	public List<UUInfo> getViewerList(int check_id,int type);
	/**
	 * 删除人员
	 * @param check_id
	 * @param sysnum
	 * @param type
	 * @return 
	 */
	public boolean deleteViewer(int check_id,int user_id,int type);

	boolean deleteByCheckId(int check_id, int type);
	/**
	 * 删除一个人的查看记录
	 * @param auto_id
	 * @param user_id
	 * @param type
	 */
	public boolean delete(int auto_id, int user_id, int type);
	
	
	public List<FirstView> getCheckView(int user_id,int type);

	public boolean isExist(int user_id,int check_id,int type);
}
