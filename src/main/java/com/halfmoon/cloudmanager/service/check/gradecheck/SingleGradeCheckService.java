package com.halfmoon.cloudmanager.service.check.gradecheck;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl.SingleGradeCheckDao;
import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;

/**
 * @author LITAO
 * @time 下午2:15:43  2017年3月11日
 * @info 
 * @修改 重新导包，修改了一个参数
 */
@Service
public class SingleGradeCheckService {

	@Autowired
	public SingleGradeCheckDao singleGradeCheckDao;

	public SingleGradeCheckDao getSingleGradeCheckDao() {
		return singleGradeCheckDao;
	}
	@Autowired
	public void setSingleGradeCheckDao(SingleGradeCheckDao singleGradeCheckDao) {
		this.singleGradeCheckDao = singleGradeCheckDao;
	}

	/**
	 * 添加运行日志，替代Sysout.out.println();
	 */
	private Logger logger = Logger.getLogger(SingleGradeCheckService.class);
	
	/**
	 * @param grade_check_id
	 * @param create_time
	 * @return
	 */
	public boolean addNewRecord(int grade_check_id){
		logger.info("enter SingleGradeCheckService.addNewRecord");
		
		boolean isAdd = false;
		System.out.println("in SingleGradeCheckService");
		try{
			if (singleGradeCheckDao == null){
				System.out.println("singleGradeCheckDao is null");
			}
			isAdd = singleGradeCheckDao.addNewRecord(grade_check_id);
			System.out.println("isAdd:" + isAdd);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return isAdd;
	}
	
	public boolean deleteRecord(int id){
		boolean isDelete = false;
		logger.info("enter SingleGradeCheckService.deleteRecord");
		
		System.out.println("in SingleGradeCheckService");
		try{
			if (singleGradeCheckDao == null){
				System.out.println("singleGradeCheckDao is null");
			}
			isDelete = singleGradeCheckDao.deleteRecord(id);
			System.out.println("isDelete:" + isDelete);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(this.getClass().getName()+".deleteRecord执行出错");
			return false;
		}
		
		return isDelete;
	}
	
	@Transactional   //这里要强调在一个transaction里面
	public ArrayList<SingleGradeCheck> searchRecord(int grade_check_id){
		ArrayList<SingleGradeCheck> result = null;
		try{
			if (singleGradeCheckDao == null){
				System.out.println("singleGradeCheckDao is null");
			}
			result = singleGradeCheckDao.searchRecord(grade_check_id);
		}catch(Exception e){
			e.printStackTrace();
			//null表示什么都返回不了，在controller中同样需要做出判断是否为空
			return null;
		}
		System.out.println(result == null); 
		System.out.println(result);
		
//		for (int i=0;i<result.size();i++){
//			System.out.println(result.get(i).getCreate_time());
//			System.out.println(result.get(i).getId());
//			System.out.println(result.get(i).getGrading_check_id());  //已修改，原：grade_check_id
//			System.out.println(result.get(i));
//		}
		return result;
	}
	
	public int getLastInsertId(){
		int result = -1;
		try{
		result = singleGradeCheckDao.getLastInsertId();
		}catch (Exception e){
			throw new RuntimeException("service");
		}
		return result;
	}
	
	
	
}
