package com.halfmoon.cloudmanager.service.check.signcheck;


import org.springframework.ui.Model;

import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.dto.ShareInfo;
/**
 * 分享的Service接口
 * @author xiaogao.XU
 *
 */
public interface ShareService {
	
	public void save(Share share);
	public Share get(int id);
	public void update(Share share);
	public void delete(int id);
	/**
	 * 创建分享-保存
	 * check_type check_id 都是通过Url获取
	 * @param share 提交的表单实体
	 * @param check_type 检查类别 
	 * @param check_id 对应的检查ID
	 * @return success Or failed
	 */
	public String save(Share share,String check_type, Integer check_id);// 重载
	// 写出来忘记干嘛的了
	public void setCheckShare(int sign_in_check_id,Model model);
	
	/**
	 * 导入检查的时候，是否匹配
	 * 如果匹配的话，将检查ID和用户ID保存在UserView中去
	 * 检查ID可以作为自增ID来使用  默认使用者为创建者
	 * @param account 分享的账号
	 * @param password 密码
	 */
	public int check(String account,String password,int user_id);
	/**
	 * 生成账号密码
	 * @param check_id 检查ID
	 * @return 账号和密码组合
	 */
	public String generate(int check_id);
	
	/**
	 * 获取分享的账号密码状态信息
	 * @param check_id 检查ID
	 * @param type 检查类型
	 * @return ShareInfo
	 */
	public ShareInfo getInfo(int check_id,int type);
	/**
	 * 更新密码
	 * @param check_id
	 * @param pwd
	 * @param type
	 */
	public void updatePwd(int check_id,String pwd,int type);
	/**
	 * 更新公开状态
	 * @param check_id
	 * @param is_open
	 * @param type
	 */
	public void updateStatus(int check_id,int is_open,int type);
	boolean deleteByCheckId(int check_id, int type);
}
