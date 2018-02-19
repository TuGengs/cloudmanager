package com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.ISingleGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;

/**
 * @author LITAO
 *
 * 几个service、bean跟dao中的参数是不是有问题？？检查~~
 */
@Repository
public class SingleGradeCheckDao extends BaseDao<SingleGradeCheck> implements ISingleGradeCheckDao {

	SqlSession sqlSession;
	
	@Override
	public boolean addNewRecord(int grade_check_id) {
		// TODO Auto-generated method stub
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
				
		try {
			sqlSession.update(this.NAMESPACE.concat("addNewRecord"), grade_check_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteRecord(int id) {
		// TODO Auto-generated method stub
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		SingleGradeCheck singleGradeCheck = new SingleGradeCheck();
		singleGradeCheck.setId(id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("deleteRecord"), singleGradeCheck);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* 这里要在mapper中处理时间
	 * @see com.halfmoon.cloudmanager.dao.sql.gradecheck.ISingleGradeCheckDao#searchRecord(int)
	 */
	@Override
	public ArrayList<SingleGradeCheck> searchRecord(int grade_check_id) {
		// TODO Auto-generated method stub
		List<SingleGradeCheck> result = null;
		
		try{
			sqlSession = this.getSqlSession();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		SingleGradeCheck singleGradeCheck = new SingleGradeCheck();
		singleGradeCheck.setGrading_check_id(grade_check_id);
		
		try {
			result = sqlSession.selectList(this.NAMESPACE.concat("searchRecord"), singleGradeCheck);
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return (ArrayList<SingleGradeCheck>)result;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		int result = -1;
		sqlSession = this.getSqlSession();			
		result = sqlSession.selectOne(this.NAMESPACE.concat("getLastInsertId"));	
		return result;
	}

}
