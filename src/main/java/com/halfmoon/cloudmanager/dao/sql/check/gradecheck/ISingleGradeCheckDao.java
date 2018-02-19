package com.halfmoon.cloudmanager.dao.sql.check.gradecheck;

import java.util.ArrayList;

import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;



public interface ISingleGradeCheckDao {

	/**
	 * 添加新的记录
	 * @param grade_check_id   这个是auto_id
	 * @return
	 */
	public boolean addNewRecord(int grade_check_id);
	
	/**
	 * 这个由于会在页面上显示所有的时间记录，对应的也就有了id
	 * 
	 * 这里delete一下，后面的detailed_grade_check表需要跟着一起把数据清除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteRecord(int id);
	
	/**
	 * 传参查询所有相关信息
	 * @param grade_check_id
	 * @return
	 */
	public ArrayList<SingleGradeCheck> searchRecord(int grade_check_id);
	
	
	/**
	 * 得到当前最后一次id（方便传到detailed中）
	 * @return
	 */
	public int getLastInsertId();
}
