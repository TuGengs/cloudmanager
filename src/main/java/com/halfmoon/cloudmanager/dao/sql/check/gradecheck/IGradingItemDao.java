package com.halfmoon.cloudmanager.dao.sql.check.gradecheck;



import java.util.ArrayList;
import com.halfmoon.cloudmanager.model.check.GradingItem;

public interface IGradingItemDao {
	
	
	/**
	 * 向表单中存储扣分项
	 * @param gradingCheckId 检查id
	 * @param name 扣分项名称
	 * @param grades 增加/减少的分数
	 * @return 是否存储成功
	 */
	public boolean addItem(int grade_check_id, String name, float score);
	
//	/**
//	 * 使用检查项的名称去删除
//	 * 
//	 * @param name 检查项名称
//	 * @return 是否删除成功
//	 */
//	public boolean deleteItem(String name);
	
	/**
	 * 使用自增id去删除
	 * 
	 * @param id 检查项自增id
	 * @return 是否删除成功
	 */
	public boolean deleteItemById(int id);
	
	
	/**
	 * 使用gradingCheckId去查找对应的扣分项
	 * 这里得到的id，也可以直接传到前端去，就看前端需要哪种数据了
	 * 
	 * @param gradingCheckId
	 * @return 查找到的该检查会用到的所有扣分项
	 */
	public ArrayList<Integer> getItemId(int rade_check_id);
	
	
	/**
	 * 更新（修改）某一条扣分项的性质
	 * 提供这种方法，但是不一定使用
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateItem(int id, String name, float score);
	
	/**
	 * 这个方法在这儿做个预留，用不上~~~
	 * 只更新分数~
	 * @return
	 */
	//public boolean updateGrades();
	
	
	/**
	 * 通过这个函数去查找对应的auto_id，name和grades
	 * 最后在service中组成json传到前端
	 * 
	 * @param gradingCheckId
	 * @return
	 */
	public ArrayList<GradingItem> getItem(int rade_check_id);
}
