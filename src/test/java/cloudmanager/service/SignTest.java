package cloudmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.DetailedSignCheckDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.ListCheckDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.ShareDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SignCheckDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SignDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SingleSignDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.UserViewDao;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignService;
import com.halfmoon.cloudmanager.service.check.signcheck.SingleSignService;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
import com.halfmoon.cloudmanager.util.encrypt.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:framework/springmvc/webApiConfig.xml",
		"classpath:framework/mybatis/database-config.xml"})
@WebAppConfiguration
public class SignTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private UserService userService;
	@Autowired
	private SingleSignService singleService;
	@Autowired
	private DetailedSignCheckService detailedSignCheckService;
	@Autowired
	private DetailedSignCheckDao detailedSignCheckDao;
	@Autowired
	private ListCheckDao listCheckDao;
	@Autowired
	private ShareDao shareDao;
	@Autowired
	private SignCheckDao signCheckDao;
	@Autowired
	private SignDao signDao;
	@Autowired
	private SignCheckService checkService;
	@Autowired
	private SignService signService;
	
	@Autowired
	private SingleSignDao singleDao;
	@Autowired
	private ListCheckService listService;
	@Autowired
	private UserViewDao userViewDao;
	
	@Test
	public void TestLan(){
		System.out.println(MD5Util.encrypt("112211"));
		
	}
	
	@Test
	public void TestSingle(){
		/*System.out.println(singleDao.getAll(21));
		System.out.println(singleDao.getCheck_id(1));*/
		//System.out.println(singleService.getViewerList(21, "20142365"));
		/*ListCheck listCheck = new ListCheck();
		listCheck.setAuto_id(21);
		listCheck.setCheck_id(21);
		listCheck.setCheck_type(1);
		listCheck.setUser_id(20140001);
		System.out.println(listCheckDao.add(listCheck));*/
		//System.out.println(signService.validateCode("1234", "20140003"));
		//System.out.println(detailedSignCheckService.getUserList(46, 1));
		/*List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		System.out.println(detailedSignCheckDao.getUserIdSortByLackTimes(21, list));*/
		//System.out.println(detailedSignCheckService.sorted_Info(2, 48, 1));
		//System.out.println(checkService.join(34, 11));
		//System.out.println(listService.getSignInfo(11));
		//System.out.println(detailedSignCheckService.getUserList(48, 1));
		//System.out.println(userViewDao.getAllCheckView(1, 1));
		//System.out.println(listCheckDao.getUserByView(46));
		//System.out.println(checkService.join(46, 13));
		//System.out.println(checkService.join(46, 14));
		/*List<UInfo> list = new ArrayList<UInfo>();
		UInfo u1 = new UInfo();
		u1.setUser_id(11);
		UInfo u2 = new UInfo();
		u2.setUser_id(12);
		System.out.println(detailedSignCheckDao.getUserIdSortByLackTimes(46, list));*/
		//System.out.println(singleService.getUserList(46,1));
		//System.out.println(detailedSignCheckService.sorted_Info(3, 19, 1));
		List<Integer> array = new ArrayList<Integer>();
		array.add(20142365);
		listService.updateAutoId(46, 65, 1, array);
		
	}
	
	@Test
	public void TestSign(){
		//System.out.println(signDao.get(1, 21));
		//signDao.closeSelf(21, 21);
		//signDao.closeAll(21, 1);
		/*System.out.println(signDao.getStatus(1, 21));
		System.out.println(signDao.getByBossOpen(21));
		System.out.println(signDao.getByHelperOpen(21, 21));*/
		/*JSONArray array = new JSONArray();
		array.put("dsad");
		
		System.out.println(array);*/
		//System.out.println(listService.getSignInfo("20142365"));
		List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		System.out.println(detailedSignCheckService.toJSON(userService.getUserInfoByStatus(19, list),19));
	}
	
	
	@Test
	public void TestSignCheck(){
		//signCheckDao.updateCheckId(21, 21);
		//System.out.println(signCheckDao.getAutoId(21, "20142365"));
		//System.out.println(signCheckDao.getAllByUserId("20142365"));
		//System.out.println(signCheckDao.getCheckIDByCode("22"));
		//System.out.println(signCheckDao.getNameByID(21));
		/*System.out.println(signCheckDao.getSignHelps(21));*/
		//System.out.println(signService.getData("20142365"));
		//System.out.println(signService.getAutoID(22, "20142365"));
		//System.out.println(checkService.getRule(21).toString());
		//System.out.println(detailedSignCheckDao.test(21, 1));
		//System.out.println(listCheckDao.getAllIds(21,-1,1,-1,1));
		//System.out.println(listCheckDao.getAllIds(46, -1, Constant.SIGN_IN_CHECK, -1, -1));
		//System.out.println(listCheckDao.getUserByView(46, 1));
		//System.out.println(listService.getSignInfo(12));
		//detailedSignCheckService.updateOughtTimes(19);
		//System.out.println(detailedSignCheckService.handleOpenHelp(7, 0, 1, 12));
		//System.out.println(singleService.getUserList(46,1));
		//System.out.println(userViewDao.getViewerList(46, 1));
		//System.out.println(checkService.getManagerList(46));
		System.out.println(detailedSignCheckDao.getUserDetail(12, 19));
		
	}
	
	@Test
	public void TestShare(){
		//System.out.println(shareDao.getByAccount("root"));
		//System.out.println(shareDao.getInfo(21, 1));
		//shareDao.updatePwd(21, "testetst", 1);
		//shareDao.updateStatus(21, 1, 1);
		//System.out.println(checkService.getHelpers(21));
		//System.out.println(singleService.getAll(21, 11));
		//System.out.println(listService.getSignInfo(11));
		System.out.println(detailedSignCheckService.sorted_Info(3, 11, 1));
	}
	
	
	@Test
	public void TestList(){
		/**
		List<Integer> list = new ArrayList<Integer>();
		list.add(20140001);
		list.add(20140002);
		listCheckDao.updateAutoId(21, 25, 1, list);
		*/
		//System.out.println(listCheckDao.getAvilList(21, 1));
		//System.out.println(listCheckDao.getAllIds(21, 1));
		//System.out.println(listCheckDao.getByAutoId(21, 25, 1));
		/*
		List<Integer> array = new ArrayList<Integer>();
		array.add(20140001);array.add(20140002);
		listCheckDao.deleteUserList(21, array, 1);
		*/
		//System.out.println(listCheckDao.getCount(21,21,1));
		//System.out.println(detailedSignCheckService.getUserList(19, "20142365"));
		/*JSONArray array = new JSONArray();
		array.put(1);
		array.put(0,2);
		System.out.println(array);*/
		//System.out.println(singleService.getUserList(21, 0));
		System.out.println(checkService.join(21, 15));
		
	}
	
	
	@Test
	public void TestDetailed(){
		//System.out.println(detailedSignCheckDao.getLackTimes(3, 20140001));
		//detailedSignCheckDao.updateOughtTimes(3);
		//System.out.println(detailedSignCheckDao.is_Here(3, 20140001));
		//System.out.println(detailedSignCheckDao.getOughtTimes(3, 20140001));
		//detailedSignCheckDao.updateStatus(3, 20140001, 1, "222", 0);
		//System.out.println(detailedSignCheckService.getUserList(3, "20142365", 0));
		/*DetailedSignCheck detailedSignCheck = new DetailedSignCheck();
		detailedSignCheck.setIcon_url("sd");
		detailedSignCheckService.save(detailedSignCheck);*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", "3");
		map.put("sort_id", "1");
		List<Integer> list = new ArrayList<Integer>();
		list.add(20140003);
		list.add(20140004);
		list.add(20140002);
		list.add(20140001);
		map.put("sysnums", list);
	}
	
	@Test
	public void TestUser(){
		//System.out.println(userService.getUserNames("20142365"));
		/**
		List<Integer> list = new ArrayList<Integer>();
		list.add(20140001);list.add(20140002);
		System.out.println(userService.getUserName(list));
		*/
		System.out.println(userService.getUserInfoByTel("18942247086"));
		System.out.println(userService.getUserIcons(1));
	}
}


