package com.halfmoon.cloudmanager.service.check.signcheck;

import java.util.List;
import java.util.Map;

import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;

public interface DetailedSignCheckService {
	
	
	/**
	 * 第三个界面/api/check/sign/single/detail/
	 * 传递给第三个界面的数据
	 * 按班级分
	 * ！！！！！！应该是从本表获取信息而不是从list_to_check 表中获取
	 * @param check_id
	 * @param check_auto_id
	 * @param sysnum
	 * @return {"data":[{"class_name":[{"sysnum":"20142323","name":"张三","icon_url":"url"}]}]}
	 */
	public Map<String, Object> getUserList(int single_id,int user_id);
	
	
	/**
	 * 点击列表签到/api/check/sign/single/detail/sign
	 * 签到表点击事件 
	 * @param json 前台传递的数据 
	 * 
	 * 如果status是到了 就把应到次数赋值给签到次数 并且is_here 为1
	 * 如果没到 将应到次数和签到次数改为0 is_here 为 0
	 */
	public boolean handleSignSubmit(Map<String,Object> map);
	
	
	/**
	 * 点击头像显示详细信息/api/check/sign/single/detail/more_info
	 * @param single_id 
	 * @param sysnum
	 * @return
	 */
	public Map<String, Object> getUserDetail(int user_id, int single_id);
	
	/**
	 * /api/check/sign/single/detail/help
	 * 处理辅助签到事件 根据前台的数据来判断是关闭还是开启
	 * @return
	 */
	public boolean handleOpenHelp(int single_id,int is_face,int is_open,int user_id);
	
	
	public void save(DetailedSignInCheck detailedSignCheck);
	/**
	 * 处理 一键全到
	 * @param map
	 * @return
	 */
	public boolean handleSignAll(Map<String,Object> map);
	
	
	
	/**
	 * 开启辅助签到
	 * 首先要判断开启辅助签到者的类别 是创建者还是辅助者
	 * 然后判断能否开启，把自身其他的检查全部关掉
	 * 之后查看是否已经开启过了，意思是表里是否已经有这条数据 在这个表基础上进行更新
	 * 还有经纬度的添加 暂时空着，可以再写一个方法或是改下参数列表
	 * @param check_id 检查ID
	 * @param single_check_auto_id 单项检查自增ID
	 * @param check_auto_id 检查自增ID
	 * @return 成功信息 错误信息
	 */
	public boolean openHelp(int check_id,int single_check_auto_id,int check_auto_id,int is_open);
	/**
	 * 关闭辅助签到
	 * @param check_id
	 * @param single_check_auto_id
	 * @param check_auto_id
	 */
	public boolean closeHelp(int check_id,int single_check_auto_id,int check_auto_id);
	/**
	 * 将应到次数加一
	 * @param single_check_id
	 */
	public void updateOughtTimes(int single_check_id);
	
	
	
	
	/**
	 * 处理学生自主签到事件
	 * 首先对应的那次签到 即得到single_sign_check_id
	 * 用户 user_id
	 * 对应用户的签到次数加1 对比应到次数 相同状态就是到了，返回给前端就是已签
	 * 小于就是迟到早退  
	 * 未签到成功？要能得到自己对应检查人员的所有这次检查 即在single_sign_check中
	 * check_id check_auto_id 一定，所有的检查最近开启的那次，进而去detailed_sign_check表
	 * 中获得签到状态 这个东西也要给 ListCheckService中getCheckInfo
	 */
	
	public int handleUserSign(int check_auto_id,
			int single_id,
			int check_id,
			double latitude,
			double longitude,Integer serverId,int user_id);
	/**
	 * 查看是否签到状态
	 * @param single_id 单项检查自增ID
	 * @param user_id 被检查人的sysnum
	 * @return 状态
	 */
	public int is_Here(int single_id,int user_id);
	/**
	 * 返回排序的数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> sorted_Info(int sort_id,int single_id,int user_id);
	/**
	 * 中转方法
	 * @param list
	 * @param single_id
	 * @return
	 */
	public Map<String, Object> toJSON(List<UInfo> list,int single_id);

	/**
	 * 保存经纬度信息
	 * @param single_id
	 * @param latitude
	 * @param longitude
	 * @param sysnum
	 * @return
	 */
	public boolean saveAddress(int single_id, double latitude,
			double longitude, int user_id);
	/**
	 * 保存多条
	 * @param list
	 * @return
	 */
	public boolean saveList(List<DetailedSignInCheck> list);
	/**
	 * 二维码签到
	 * @param single_id
	 * @param sysnum
	 * @return
	 */
	public boolean handleDimension(int single_id,int user_id);

	boolean deleteByCheckId(int check_id);

	/**
	 * 根据check_id和user_id获取所有的single_id
	 * @param user_id
	 * @param check_id
	 * @return
	 */
	public List<Integer> getSingleIdByUserIdAndCheckId(int user_id, int check_id);
	
	public boolean deleteBySingleId(int single_id);

	/**
	 * 对单个人添加备注
	 * @param single_id
	 * @param user_id
	 * @param remark
	 * @return
	 */
	public boolean saveSingleRemark(int single_id, int user_id, String remark);
}
