package com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.IDetailedGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.DetailedGradeCheck;


/**
 * @author LITAO
 * 
 * 应该有一部分还需要修改的（尤其是最后两个方法）
 */
@Repository
public class DetailedGradeCheckDao extends BaseDao<DetailedGradeCheck> implements IDetailedGradeCheckDao {

	SqlSession sqlSession;
	
	@Override
	public boolean addUsers(int single_grade_check_id, int user_id) {
		// TODO Auto-generated method stub
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setSingle_grade_check_id(single_grade_check_id);
		detailedGradeCheck.setUser_id(user_id);		
		try{
			sqlSession.insert(this.NAMESPACE.concat("addUsers"), detailedGradeCheck);
		}catch (Exception e){
			throw new RuntimeException("123");
		}
		return true;
	}

	@Override
	public boolean updateGrades(int id, float single_grade) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setId(id);
		detailedGradeCheck.setSingle_grade(single_grade);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateGrades"), detailedGradeCheck);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateItems(JsonArray grading_items, int id) {
		// TODO Auto-generated method stub
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setGrading_items(grading_items);
		detailedGradeCheck.setId(id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateItems"), detailedGradeCheck);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.halfmoon.cloudmanager.dao.sql.gradecheck.IDetailedGradeCheckDao#searchItems(int)
	 * 
	 * 先记下来，这个貌似在service中要检查null和""两种情况~~
	 */
	@Override
	public JsonArray searchItems(int id) {
		// TODO Auto-generated method stub
		JsonArray result = null;
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setId(id);
		
		try {
			result = sqlSession.selectOne(this.NAMESPACE.concat("searchItems"), detailedGradeCheck);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return result;
	}

	@Override
	public boolean deleteRecord(int single_grade_check_id) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
		}
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setSingle_grade_check_id(single_grade_check_id);
		
		try{
			sqlSession.delete(this.NAMESPACE.concat("deleteRecord"), detailedGradeCheck);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/* 这要注意，传的值，实际为single_grade_check表的自增id
	 * @see com.halfmoon.cloudmanager.dao.sql.gradecheck.IDetailedGradeCheckDao#showDetailedGradeCheck(int)
	 */
	@Override
	public ArrayList<DetailedGradeCheck> showDetailedGradeCheck(int check_id){
		
		List<DetailedGradeCheck> result = new ArrayList<DetailedGradeCheck>();
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		try{
			result = sqlSession.selectList(this.NAMESPACE.concat("showDetailedGradeCheck"), check_id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return (ArrayList<DetailedGradeCheck>)result;
		
	}
	
	
	
	
	
	/* 
	 * 这个方法具有重要的纪念意义，在此类的伴随下，终于解决了JsonTypeHandler的问题
	 * (non-Javadoc)
	 * @see com.halfmoon.cloudmanager.dao.sql.gradecheck.IDetailedGradeCheckDao#testAddItems()
	 */
	@Override//JSONArray json
	public boolean testAddItems(){
		String str = "[{'grade':'123'},{ 'grade':'234'}]";
		
		JsonArray jsonArray = new JsonParser().parse(str).getAsJsonArray();
		System.out.println(jsonArray);
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
		}
		DetailedGradeCheck detailedGradeCheck = new DetailedGradeCheck();
		detailedGradeCheck.setGrading_items(jsonArray);
		
		try{
			sqlSession.insert(this.NAMESPACE.concat("testAddItems"), detailedGradeCheck);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		int result=-1;
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		
		try{
			result = sqlSession.selectOne(this.NAMESPACE.concat("getLastInsertId"));
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
	
	
	
}
