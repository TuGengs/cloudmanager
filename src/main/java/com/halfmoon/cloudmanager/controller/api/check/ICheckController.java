package com.halfmoon.cloudmanager.controller.api.check;

import com.halfmoon.cloudmanager.response.OperationResultWithData;

/**
 * 定义了检查的控制层接口
 * @author hzq
 * 
 */
public interface ICheckController {
	
	/**
	 * 生成检查代码
	 * @param check_id 检查id
	 * @return 检查代码
	 */
	public OperationResultWithData<String> generateCode(int check_id);
	
	/**
	 * 获取单次检查下所有的DetailedCheck（即检查记录）
	 * @param single_check_id 单次检查id
	 * @return 检查记录
	 */
	public OperationResultWithData<?>
			getSingleCheckRecord(int single_check_id);
	
	/**
	 * 根据周期获取单次检查
	 * @param check_id 检查id
	 * @param cycle_time 周期次数
	 * @return 单次检查集合
	 */
	public OperationResultWithData<?>
			getSingleChecksByCycle(int check_id, int cycle_time);
	
	/**
	 * 获取该门检查的总结果
	 * @param check_id 检查id
	 * @return 总结果
	 */
	public OperationResultWithData<?>
			getRawDataOfTotalResult(int check_id);
	
	/**
	 * 获取指定用户的检查总结果
	 * @param user_id 用户id
	 * @param check_id 检查id
	 * @return 指定用户的检查总结果
	 */
	public OperationResultWithData<?>
			getRawDataOfTotalResult(int user_id, int check_id);

}
