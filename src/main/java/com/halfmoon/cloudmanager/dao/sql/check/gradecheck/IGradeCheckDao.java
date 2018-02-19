package com.halfmoon.cloudmanager.dao.sql.check.gradecheck;

import java.util.ArrayList;
import java.util.Date;

import com.halfmoon.cloudmanager.model.check.GradeCheck;

/**
 * @author LITAO
 * 对grade_check表的一系列操作
 */
public interface IGradeCheckDao {

	
	/**
	 * boss在创建一个新记录的使用使用这个方法，创建时里面是没有emp_id的信息的
	 * 注意：处理check_id，使得check_id = id 
	 * 注意：在mapper中用now()表达当前系统时间
	 * 
	 * @param check_id
	 * @param name
	 * @param creator_id
	 * @param create_time
	 * @return
	 */
	public boolean addNewRecord(String name, int creator_id);
	
	
	/**
	 * 添加一个employee的时候调用
	 * 注意：要先处理check_id，先要获得check_id
	 * 注意：在mapper中处理create_time
	 * 
	 * @param check_id
	 * @param creator_id
	 * @param create_time
	 * @param checker_type 把type固定为1（employee）
	 * @param emp_id
	 * @param person_list
	 * @return
	 */
	public boolean addEmpRecord(int check_id, String name, int creator_id, byte checker_type, int emp_id);
	
	
	/**
	 * 查看自己管理的人，boss对应全部人员，employee对应自己检查的人员
	 * 
	 * @param creator_id
	 * @param emp_id
	 * @return
	 */
	//方法弃用，因为暂时放弃了person_list
	//public String searchPersons(int creator_id, int emp_id);
	
	
	/**
	 * 通过check_id和empId可以唯一确定分配给该人的人员
	 * 只有empId，可能会查出多条记录；（empId+creatorId同）
	 * 
	 * 这个方法暂时放弃，XU好像也没用person_list，是用list_to_check代替
	 * 
	 * @param checkId
	 * @param empId
	 * @return
	 */
	//public JSONObject getPersonList(int checkId, int empId);
	
	
	/**
	 * 将id，check_id，name打包显示到前端上
	 * 不要create_time，create_time单独查？？！！(避免json重数据量太大)
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<GradeCheck> getMainInfo(ArrayList<Integer> id_list);
	
	
	/**
	 * 通过id去查找该条记录创建时间
	 * 
	 * @param id
	 * @return
	 */
	public Date getCreateTime(int id);
	
	
	
	
	/**
	 * 得到当前最后一次插入的id
	 * @return
	 */
	public int getLastInsertId();
}
