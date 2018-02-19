package com.halfmoon.cloudmanager.dao.nosql.check;

/**
 * 检查相关的数据库操作（Redis）
 * @author hzq
 *
 */
public interface ICheckDao {
	
	/**
	 * 将一个检查和对应编码存入数据库
	 * @param check_id 检查id
	 * @param code 代码
	 * @param clazz	检查类型
	 */
	public void setCode(int check_id, String code, Class<?> clazz);

}
