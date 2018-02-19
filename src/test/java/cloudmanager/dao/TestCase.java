package cloudmanager.dao;

import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
import com.halfmoon.cloudmanager.util.encrypt.MD5Util;

public class TestCase {
	
	public static void main(String args[]) throws Exception {
		
//		HashMap<String, String> data = new HashMap<String, String>();
//		data.put("UserName", "20142043");
//		data.put("PassWord", "360101");
//		
//		Response resp = Jsoup.connect("http://jwgl.jxau.edu.cn/User/CheckLogin")
//				.method(Connection.Method.POST)
//				.data(data)
//				.execute();
//		
//		System.out.println(resp.url().getPath());
		User use = new User();
		Byte b = new Byte("11");
		use.setId(1);
		use.setAcademy("123");
		use.setBuilding("123");
		use.setClazz("123");
		use.setGender(b);
		use.setIcon_id(1);
		use.setOpenid("1");
		use.setIs_teacher(b);
		use.setName("tugeng111");
		use.setPassword("123456789");
		use.setPhoto_id(12);
		use.setTel("11111111");
		use.setRoom("1");
		use.setSchool_id(1);
		use.setSysnum("11");
//		
////	IUserDao dd = new UserDao();
//		UserService us = new UserService();
//		us.register(use);
//		System.out.println(MD5Util.encrypt("123456"));
//		System.out.println(Byte.valueOf("123",10));
	}
	
}
//