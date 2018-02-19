package com.halfmoon.cloudmanager.service.check;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.halfmoon.cloudmanager.dao.nosql.check.impl.CheckDao;
import com.halfmoon.cloudmanager.util.randomcode.RandomCode;

/**
 * Check的Service层，共用生成代码的方法
 * @author hzq
 *
 * @param <TotalResult> 结果类
 */
public abstract class CheckService<TotalResult> {
	
	private RandomCode randomCode = new RandomCode(4);
	
	@Autowired
	private CheckDao checkDao;
	
	/**
	 * 生成该检查的代码
	 * @param check_id 检查id
	 * @param clazz 检查类型
	 * @return 生成的代码
	 */
	public String generateCheckCode(int check_id, Class<?> clazz) {
		
		String code = checkDao.getCodeByTypeAndId(check_id, clazz);
		if(code != null) {
			return code;
		}
		
		code = randomCode.getCode();
		checkDao.setCode(check_id, code, clazz);
		
		return code;
		
	}
	
	/**
	 * 获取单次检查的所有检查记录
	 * @param single_check_id 单次检查id
	 * @return 所有检查记录
	 */
	public abstract List<?> getAllDetailsOfSingleCheck(int single_check_id);
	
	/**
	 * 按周期数获取单次检查，如果没有周期，返回空
	 * @param check_id 检查id
	 * @param cycle_time 周期次数
	 * @return 单次检查集合
	 */
	public abstract List<?> getSingleChecksByCycle(int check_id, int cycle_time);
	
	/**
	 * 获取该门检查的总结果，需要前端进一步处理
	 * @param check_id 检查id
	 * @return 总结果
	 */
	public abstract TotalResult getRawResultDataAndInfo(int check_id);

	/**
	 * 获取该门检查指定用户的总结果，需要前端进一步处理
	 * @param user_id 用户id
	 * @param check_id 检查id
	 * @return 指定用户的总结果
	 */
	public abstract TotalResult getRawResultDataAndInfo(int user_id, int check_id);

}
