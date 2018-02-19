package com.halfmoon.cloudmanager.dao.sql.check.gradecheck;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.halfmoon.cloudmanager.model.check.DetailedGradeCheck;

/**
 * @author LITAO
 *
 */
public interface IDetailedGradeCheckDao {
	
	
	/**
	 * 传入表中所示single_grade_check_id得到相关的信息
	 * @param id
	 * @return
	 */
	public ArrayList<DetailedGradeCheck> showDetailedGradeCheck(int check_id);
	
	/**
	 * @param singleGradeCheckId
	 * @param userId
	 * @param managerId
	 * @return
	 */
	public boolean addUsers(int single_grade_check_id, int user_id);
	
	/**
	 * 更新某人被扣的总分
	 * @param single_grade
	 * @return
	 */
	public boolean updateGrades(int id, float single_grade);
	
	/**
	 * 更新某人被扣分的项目
	 * @param grading_items
	 * @param id
	 * @return
	 */
	public boolean updateItems(JsonArray grading_items, int id);
	
	//测试下能不能直接添加json数据JSONArray json
	public boolean testAddItems();
	
	/**
	 * 查看某人被扣分的项目
	 * @param id
	 * @return
	 */
	public JsonArray searchItems(int id);
	
	
	/**
	 * 查看全寝扣的总分-这个应该是service层的方法
	 * @param id
	 * @return
	 */
	//public float getRoomGrade(int id);
	
	/**
	 * 这个是要删除grading_items里面的一些扣分项，但是貌似上面的updateItems就够了
	 * @return
	 */
	//public boolean deleteItems();
	
	
	/**
	 * 这个删除的方法，是配合single_grade_check记录被删除时使用的
	 * 在single_grade_check记录被删除的时候，detailed_grade_check对应的数据也是没有意义的
	 * 
	 * @param single_grade_check_id
	 * @return
	 */
	public boolean deleteRecord(int single_grade_check_id);

	/**
	 * 得到当前最后一次产生的id
	 * @return
	 */
	public int getLastInsertId();
}
