package com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.IGradingItemDao;
import com.halfmoon.cloudmanager.model.check.GradingItem;


/**
 * @author LITAO
 *
 */
@Repository("gradingItemDao")
public class GradingItemDao extends BaseDao<GradingItem> implements IGradingItemDao{
	
//	@Resource
//	private SqlSession sqlSession;
	/**
	 * 从配置文件中获取sqlsession
	 * 在这里供service层去调用
	 * sqlsession每次打开之后需要提交commit、关闭close
	 */
//	@Resource
//	public SqlSession getSqlSession() {
//		return this.getSqlSession();
//	}
	SqlSession sqlSession;
	@Override
	public boolean addItem(int grade_check_id, String name, float score) {
		// TODO Auto-generated method stub
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		System.out.println("get sqlsession");
		GradingItem gradingItem = new GradingItem();
		gradingItem.setGrade_check_id(grade_check_id);
		gradingItem.setName(name);
		gradingItem.setScore(score);
		try{
			System.out.println(this.NAMESPACE.concat("addItem"));
			sqlSession.insert(this.NAMESPACE.concat("addItem"), gradingItem);
			
			//sqlSession.commit();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("insert失败");
			return false;		
		}
//		finally{
//			if (sqlSession != null){
//				System.out.println("sqlSession不为空");
//				sqlSession.close();
//			}		
//		}  //不需要关闭，SqlSessionTemplate你不可以手动关闭。SqlSessionTemplate是一个代理类，内部他会为每次请求创建线程安全的sqlsession,并与Spring进行集成.在你的方法调用完毕以后他会自动关闭的。
		return true;
	}


	@Override
	public boolean deleteItemById(int id) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		GradingItem gradingItem = new GradingItem();
		gradingItem.setId(id);
		
		try{
			sqlSession.delete(this.NAMESPACE.concat("deleteItemById"), gradingItem);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	@Override
	public ArrayList<Integer> getItemId(int grading_check_id) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		GradingItem gradingItem = new GradingItem();
		gradingItem.setGrade_check_id(grading_check_id);
		
		List<Integer> result = null;
		try{
			result = sqlSession.selectList(this.NAMESPACE.concat("getItemId"), gradingItem);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	 
		return (ArrayList<Integer>)result;
	}


	@Override
	public ArrayList<GradingItem> getItem(int grade_check_id) {
		// TODO Auto-generated method stub
		//ArrayList<GradingItem> result = new ArrayList<GradingItem>();
		List<GradingItem> result = null;
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		GradingItem gradingItem = new GradingItem();
		gradingItem.setGrade_check_id(grade_check_id);
		
		try{
			result = sqlSession.selectList(this.NAMESPACE.concat("getItem"), gradingItem);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		return (ArrayList<GradingItem>)result;
	}


	@Override
	public boolean updateItem(int id, String name, float score) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		GradingItem gradingItem = new GradingItem();
		gradingItem.setId(id);
		gradingItem.setScore(score);
		gradingItem.setName(name);
		try{
			sqlSession.update(this.NAMESPACE.concat("updateItem"), gradingItem);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	

}
