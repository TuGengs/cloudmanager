package com.halfmoon.cloudmanager.service.check.gradecheck;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl.DetailedGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.DetailedGradeCheck;

/**
 * @author LITAO
 * @time 下午2:14:07  2017年3月11日
 * @info 
 * @修改 重新导包
 */
@Service
public class DetailedGradeCheckService {
	
	@Autowired
	private DetailedGradeCheckDao detailedGradeCheckDao;

	public DetailedGradeCheckDao getDetailedGradeCheckDao() {
		return detailedGradeCheckDao;
	}
	@Autowired
	public void setDetailedGradeCheckDao(DetailedGradeCheckDao detailedGradeCheckDao) {
		this.detailedGradeCheckDao = detailedGradeCheckDao;
	}
	
	/**
	 * 在创建表2记录的时候，将被检查的人员信息都拉到这个表中
	 * @param singleGradeCheckId
	 * @param userId
	 * @param managerId
	 * @return 是否存入成功
	 * @throws Exception 
	 */
	public boolean addUsers(int single_grade_check_id, int user_id){
		boolean isAdd = false;

			if (detailedGradeCheckDao == null){
				System.out.println("detailedGradeCheckDao is null");
			}
			isAdd = detailedGradeCheckDao.addUsers(single_grade_check_id, user_id);

		return isAdd;
	}
	
	
	/**
	 * @param singleGrade
	 * @return 是否修改成功
	 */
	public boolean updateGrades(int id, float single_grade) {
		boolean isUpdate = false;
		try{
			if (detailedGradeCheckDao == null){
				System.out.println("detailedGradeCheckDao is null");
			}
			isUpdate = detailedGradeCheckDao.updateGrades(id, single_grade);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return isUpdate;
	}
	
	/**
	 * @param grading_items
	 * @param id
	 * @return
	 */
	public boolean updateItems(JsonArray grading_items, int id){
		boolean isUpdate = false;
		try{
			if (detailedGradeCheckDao == null){
				System.out.println("detailedGradeCheckDao is null");
			}
			isUpdate = detailedGradeCheckDao.updateItems(grading_items, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return isUpdate;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public JsonArray searchItems(int id) {
		JsonArray result = null;
		try{
			if (detailedGradeCheckDao == null){
				System.out.println("detailedGradeCheckDao is null");
			}
			result = detailedGradeCheckDao.searchItems(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param check_id
	 * @return
	 */
	public ArrayList<DetailedGradeCheck> showDetailedGradeCheck(int check_id){
		ArrayList<DetailedGradeCheck> result = new ArrayList<DetailedGradeCheck>();
		try{
			result = detailedGradeCheckDao.showDetailedGradeCheck(check_id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		int result = -1;
		try{
			result = detailedGradeCheckDao.getLastInsertId();
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
}
