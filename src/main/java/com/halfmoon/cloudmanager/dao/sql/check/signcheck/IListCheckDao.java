package com.halfmoon.cloudmanager.dao.sql.check.signcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.halfmoon.cloudmanager.model.check.ListCheck;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
/**
 * 有三种获取数据的形式
 * 1.根据check_id和auto_id获取   
 * 	1.1 check_id和auto_id一样  // 创建者获取未被分配的信息
 * 	1.2 check_id和auto_id不一样 // 辅助者获取对应的信息
 * 2.根据check_id 获取 //获取所有的信息
 * 
 * 取数据的时候 按照学号=1 姓名=2 状态=3的方式
 * @author Administrator
 *
 */
public interface IListCheckDao {
	
	
	/**
	 * 更新界面一自增ID
	 */
	public boolean updateAutoId(
			@Param("check_id")int check_id,
			@Param("check_auto_id")int check_auto_id,
			@Param("type")int type,
			@Param("list")List<Integer> list);
	/**
	 * 获取未被分配的sysnum
	 * 这个要看最后自增ID的默认值了，我认为默认设为check_id也可以
	 * @param check_id 检查ID
	 * @param type 检查类型
	 * @return List
	 */
	/*public List<String> getAvilList(
			@Param("check_id")int check_id,@Param("type")int type);*/
	/**
	 * 获取对应所有的人员信息 并可排序
	 * @param check_id !NULL
	 * @param auto_id	可为空
	 * @param type		非空
	 * @param single_id	默认为-1 只有按照状态排序才会用到
	 * @param order		默认为0 不排序
	 * @return
	 */
	public List<UInfo> getAllIds(int check_id,
								 int auto_id,
								 int type,
								 int single_id,
								 int order);
	
	/**
	 * 通过检查ID和检查自增ID获取人员编号
	 * @param check_id
	 * @param check_auto_id
	 * @param type
	 * @return
	 */
	public List<UUInfo> getByAutoId(@Param("check_id")int check_id,
			@Param("check_auto_id")int check_auto_id,@Param("type")int type);
	/**
	 * 这个好像没什么卵用
	 * @param sysnum
	 * @return
	 */
	public List<ListCheck> getChecksBySys(int user_id);
	/**
	 * 删除人员--多条
	 * @param check_id
	 * @param array
	 * @param type
	 * @return
	 */
	public boolean deleteUserList(@Param("check_id")int check_id,
			@Param("array")List<Integer> array,@Param("type")int type);
	
	/**
	 * 简化版的getCount 
	 * 查询一个人对应的检查人数
	 * @param check_id
	 * @param check_auto_id
	 * @param type
	 * @return
	 */
	public int getCount(@Param("check_id")int check_id,
			@Param("check_auto_id")int check_auto_id,
			@Param("type")int type);
	
	public List<ListCheck> getSignInfo(int user_id);
	
	public int isExists(ListCheck listCheck);
	boolean deleteByCheckId(int check_id, int type);
	boolean updateAutoIdForCheckId(int check_id, int auto_id, int type);
	
	/**
	 * 获取查看界面数据
	 * user_id name class lack_times
	 * @param check_id
	 * @return
	 */
	List<UUInfo> getUserByView(int check_id,int sort_id);
	
	/**
	 * 获取 user_id name class专用
	 * @param auto_id
	 * @param check_id
	 * @param type
	 * @return
	 */
	List<UUInfo> getList(int auto_id, int check_id, int type);
	
}
