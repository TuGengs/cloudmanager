package com.halfmoon.cloudmanager.service.check.signcheck;

import java.util.List;
import java.util.Map;

import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
/**
 * 单次检查Service
 * @author xiaogao.XU
 *
 */
public interface SingleSignService {
	
	
	/**
	 * 检查第二个界面 	/api/check/sign/single/
	 * 获取所有单项检查 按照时间逆序
	 * 返回给前端
	 * @param check_id 总检查ID
	 * @param sysnum 用来判断其是辅助还是创建者
	 * @return json
	 */
	public Map<String, Object> getAll(int check_id,int user_id);
	
	/**
	 * 创建单项新检查/api/check/sign/single/add/
	 * 重载
	 * @param check_id 检查ID
	 * @param create_time 创建时间 由前端传过来
	 * @param sysnum 创建者ID
	 */
	public int save(int check_id,String create_time,int user_id);
	
	/**
	 * 第二个界面 名单/api/check/sign/single/list/
	 * @param check_id 检查ID
	 * @param sysnum 
	 * @return
	 */
	public Map<String, Object> getUserList(int check_id,int user_id);
	/**
	 * 名单中删除 /api/check/sign/single/list/delete/
	 * 删除的是list_to_check表中的数据
	 * 本来是需要check_id 和 check_auto_id 同时对应删除的
	 * 但是是创建者进行操作 这样的话将check_id对应的都删除  保证数据的一致性
	 * @param json {“check_id”:2333,”list”:[20142323,20132333]}
	 * 采用 delete from table where id in (12,23);
	 */
	public boolean deleteUserList(Map<String,Object> map);
	
	/**
	 * 界面二设置-公开/api/check/sign/single/setting/view
	 * 当第一次点击设置公开时返回一个默认的账号密码
	 * 账号由系统统一生成
	 * 以后就是返回自己设置的密码
	 * 返回当前的公开状态
	 * @param check_id
	 * @return
	 */
	public Map<String, Object> publicView(int check_id);
	
	/**
	 * 设置-公开 密码更改/api/check/sign/single/setting/view/pwd_modify
	 * @param check_id
	 * @param password
	 */
	public boolean publicModify(int check_id,String password);
	/**
	 * 设置-公开 查看者名单
	 * 返回数据时剔除自己
	 * @param check_id
	 * @param sysnum 
	 * @return
	 */
	public Map<String, Object> getViewerList(int check_id,int user_id);
	/**
	 * 设置-公开 名单删除/api/check/sign/single/setting/view/list/delete
	 * @param check_id
	 * @param sysnum
	 */
	public boolean deleteViewer(int check_id,int user_id);
	/**
	 * 设置-公开 公开与否/api/check/sign/single/setting/view/open/
	 * @param check_id
	 * @param is_open
	 */
	public boolean updatePublic(int check_id,int is_open);
	
	public void save(SingleSignInCheck single_Sign_Check);
	public void update(SingleSignInCheck single_Sign_Check);
	public SingleSignInCheck get(int id);
	
	public boolean delete(int id);
	
	
	/**
	 * 
	 * 并且会在第三个界面表里生成被检查人员信息
	 * @param check_id 检查ID
	 */
	public void save(int check_id);
	
	
	
	/**
	 * 获取检查ID
	 * 由界面二向界面三跳转用到
	 * @param single_id 自增ID
	 * @return
	 */
	public int getCheckId(int single_id);
	
	public List<Integer> getSingleId(int check_id);

	boolean deleteByCheckId(int check_id);

	public boolean updateRemark(int single_id, String content);
	
	public String getRemark(int single_id);
}
