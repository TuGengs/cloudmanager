package com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.IGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.GradeCheck;

/**
 * @author LITAO
 * @time 下午5:20:46  2017年3月11日
 * @info 
 * @修改 由于两个类重名，无法注入，所以在这儿改名称为GGradeCheckDao
 */
@Repository
public class GGradeCheckDao extends BaseDao<GradeCheck>implements IGradeCheckDao{

	SqlSession sqlSession;

	@Override
	@Transactional
	public boolean addNewRecord(String name, int creator_id) {
		//java.util.Date create_time
		// TODO Auto-generated method stub
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		GradeCheck gradeCheck = new GradeCheck();
		gradeCheck.setName(name);
		gradeCheck.setCreator_id(creator_id);
		//gradeCheck.setCreate_time(create_time);
		
		try{
			sqlSession.insert(this.NAMESPACE.concat("addNewRecord"), gradeCheck);
			sqlSession.update(this.NAMESPACE.concat("updateCheckId"));
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean addEmpRecord(int check_id, String name, int creator_id,
			byte checker_type, int emp_id) {//java.util.Date create_time, 
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		GradeCheck gradeCheck = new GradeCheck();
		gradeCheck.setCheck_id(check_id);
		gradeCheck.setName(name);
		gradeCheck.setCreator_id(creator_id);
		//gradeCheck.setCreate_time(create_time);
		gradeCheck.setChecker_type(checker_type);
		gradeCheck.setEmp_id(emp_id);
		
		try{
			System.out.println("in");
			sqlSession.insert(this.NAMESPACE.concat("addEmpRecord"), gradeCheck);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public ArrayList<GradeCheck> getMainInfo(ArrayList<Integer> id_list) {
		// TODO Auto-generated method stub
		List<GradeCheck> result = null;// new List<GradeCheck>();
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
//		ArrayList<GradeCheck> param = new ArrayList<GradeCheck>();
//		for (int i=0;i<id_list.size();i++){
//			GradeCheck gc = new GradeCheck();
//			gc.setId(id_list.get(i));
//			param.add(i, gc);
//		}
		
		try{
			/**
			 * 很奇怪，在这里不能直接强转类型为ArrayList<GradeCheck>
			 * 只能在下面return的时候强转类型.....
			 */
			result = sqlSession.selectList(this.NAMESPACE.concat("getMainInfo"), id_list);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return (ArrayList<GradeCheck>)result;
	}

	@Override
	public Date getCreateTime(int id) {
		// TODO Auto-generated method stub
		Date result = null;
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		GradeCheck gradeCheck = new GradeCheck();
		gradeCheck.setId(id);
		try{
			/**
			 * 挺方便的，mybatis可以直接在配置文件中组装成ArrayList<GradingItem>的形式
			 */
			result = (Date)sqlSession.selectOne(this.NAMESPACE.concat("getCreateTime"), gradeCheck);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return result;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		int result = -1;
		
		try{
			result = sqlSession.selectOne(this.NAMESPACE.concat("getLastInsertId"));
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	


}
