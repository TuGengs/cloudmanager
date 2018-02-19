package com.halfmoon.cloudmanager.service.check.gradecheck;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl.GGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.GradeCheck;


/**
 * @author LITAO
 * @time 下午2:05:12  2017年3月11日
 * @info 由于部分字段（model）被修改了，在dao层跟service做出对应的改变
 * @修改 尤其强调，由于项目中有两个GradeCheckDao，注意导包
 * @修改 checker_type
 * @修改 由于重名，把这个类名称改了
 */
@Service
public class GGradeCheckService {
	
	@Autowired
	private GGradeCheckDao gGradeCheckDao;
	public GGradeCheckDao getgGradeCheckDao() {
		return gGradeCheckDao;
	}
	@Autowired
	public void setgGradeCheckDao(GGradeCheckDao gGradeCheckDao) {
		this.gGradeCheckDao = gGradeCheckDao;
	}



	/**
	 * 注意在mapper中处理create_time
	 * @param name
	 * @param creator_id
	 * @return
	 */
	public boolean addNewRecord(String name, int creator_id){
		
		boolean isAdd = false;
		
		try{
			if (gGradeCheckDao == null){
				System.out.println("gradeCheckDao is null");
			}
			isAdd = gGradeCheckDao.addNewRecord(name, creator_id);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return isAdd;
	}
	
	
	
	public boolean addEmpRecord(int check_id, String name, int creator_id,
			byte checker_type, int emp_id) {
		
		boolean isAdd = false;
		
		try{
			if (gGradeCheckDao == null){
				System.out.println("gradeCheckDao is null");
			}
			isAdd = gGradeCheckDao.addEmpRecord(check_id, name, creator_id,checker_type, emp_id);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return isAdd;
	}
	
	
	public ArrayList<GradeCheck> getMainInfo(ArrayList<Integer> id_list){
		ArrayList<GradeCheck> result = new ArrayList<GradeCheck>();
		try{
			result = gGradeCheckDao.getMainInfo(id_list);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	
	public Date getCreateTime(int id){
		Date date = null;
		
		try{
			date = gGradeCheckDao.getCreateTime(id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return date;
	}
	
	public int getLastInsertId(){
		int result = -1;
		try{
			result = gGradeCheckDao.getLastInsertId();
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	
	
//  由于放弃person_list，改方法弃用	
//	public String searchPersons(int creator_id, int emp_id) {
//	
//	String result = null;
//	
//	try{
//		if (gradeCheckDao == null){
//			System.out.println("gradeCheckDao is null");
//		}
//		result = gradeCheckDao.searchPersons(creator_id, emp_id);
//	}catch (Exception e){
//		e.printStackTrace();
//	}
//	
//	return result;
//}
	
	
	/*----------Dragon be here!----------/
	/* 　 　┏┓　　　┏┓
	/* 　　┏┛┻━-━━┛┻┓
	/* 　　┃　　　   　　┃
	/* 　　┃　　   ━　 　┃
	/* 　　┃　┳┛　┗┳　┃
	/* 　　┃　　　　　　┃
	/* 　　┃　　　┻　 　┃
	/* 　　┃　　　　　　┃
	/* 　　┗━┓　　　┏━┛
	/*　    　　　┃　　　┃神兽保佑
	/* 　  　┃　　　┃代码无BUG！
	/* 　　　┃　　　┗━━━┓
	/* 　　　┃　　　　　　┣┓
	/*　    　　　┃　　　　　　┏┛
	/* 　　　┗┓┓┏━-┳┓┏┛
	/* 　　 　┃┫┫　  ┃┫┫
	/* 　 　　┗┻┛　  ┗┻┛
	* ━━━━━━神兽出没━━━━━━*/
	
}
