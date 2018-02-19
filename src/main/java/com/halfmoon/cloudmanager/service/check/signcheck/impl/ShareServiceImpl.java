package com.halfmoon.cloudmanager.service.check.signcheck.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.ShareDao;
import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.UserView;
import com.halfmoon.cloudmanager.model.check.dto.ShareInfo;
import com.halfmoon.cloudmanager.service.check.signcheck.ShareService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.UserViewService;
/**
 * 分享的Service
 * @author xiaogao.XU
 *
 */
@Service
public class ShareServiceImpl implements ShareService{
	@Autowired
	private ShareDao shareDao;
	@Autowired
	private UserViewService userViewService;
	@Autowired
	private SignCheckService signCheckService;
	
	
	
	@Override
	public void save(Share share) {
		shareDao.add(share);
	}
	@Override
	public String save(Share share,String check_type,Integer check_id){
		share.setSign_in_check_id(check_id);
		share.setCheck_type(check_type.equals("sign") ? 1 : 0);
		// 是否已经有相同Account存在，有就返回Error 还可以优化
		if(shareDao.getByAccount(share.getAccount()) != null){
			return "error";
		}
		save(share);
		return "success";
		
	}

	@Override
	public Share get(int id) {
		return (Share) shareDao.get(id);
	}

	@Override
	public void update(Share share) {
		shareDao.modify(share);
	}

	@Override
	public void delete(int id) {
		shareDao.delete(id);
	}

	

	@Override
	public void setCheckShare(int sign_in_check_id, Model model) {
		
	}
	
	@Override
	public int check(String account, String password,int user_id) {
		Share share = shareDao.getByAccount(account);
		if(share != null){
			if(password.equals(share.getPassword())){
				// 导入检查成功，然后保存到 user_view 中{
				if(userViewService.isExist(
						user_id, share.getSign_in_check_id(), share.getCheck_type())){
					return -3;// 已经导入了
				}
				UserView userView = new UserView();
				int sign_in_check_id = share.getSign_in_check_id();
				userView.setSign_in_check_id(sign_in_check_id);
				
				userView.setCreator_id(user_id);
				userView.setCheck_type(share.getCheck_type());
				
				userViewService.save(userView);
				return 1;
			}else{
				return -2;//密码错误
			}
			
			
		}else{
			return -1; // 账号不存在
		}
	}
	
	@Override
	public String generate(int check_id) {
		char letters[] = {'A','C','5','2','6','8','a','z','G','b'};
		String str = "1"+String.valueOf(check_id);
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			builder.append(letters[str.charAt(i)-'0']);
			//System.out.println(builder.toString());
		}
		return builder.toString();
	}
	@Override
	public ShareInfo getInfo(int check_id, int type) {
		return shareDao.getInfo(check_id, type);
	}
	@Override
	public void updatePwd(int check_id, String pwd, int type) {
		shareDao.updatePwd(check_id, pwd, type);
	}
	@Override
	public void updateStatus(int check_id, int is_open, int type) {
		shareDao.updateStatus(check_id, is_open, type);
	}
	@Override
	public boolean deleteByCheckId(int check_id,int type){
		return shareDao.deleteByCheckId(check_id,type);
	}

}
