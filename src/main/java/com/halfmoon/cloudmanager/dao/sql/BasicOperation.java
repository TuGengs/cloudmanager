package com.halfmoon.cloudmanager.dao.sql;

/**
 * 
 * 此接口定义了数据库的四个基本操作：
 * 增、删、改、查
 * @author hzq
 *
 * @param <Bean> JavaBean类
 */
public interface BasicOperation<Bean> {

	/**
	 * 添加一条记录
	 * @param bean JavaBean对象
	 * @return 是否成功
	 */
	public boolean add(Bean bean);
	
	/**
	 * 根据id删除一条记录
	 * @param id 自增id
	 * @return 是否成功
	 */
	public boolean delete(int id);
	
	/**
	 * 修改该id对应的一条记录
	 * @param bean JavaBean对象（包含自增id ）
	 * @return 是否成功
	 */
	public boolean modify(Bean bean);
	
	/**
	 * 查询该id对应的一条记录（不一定返回对象本身）
	 * @param id 自增id
	 * @return 按查到的记录封装好的JavaBean对象
	 */
	public Object get(int id);
	
}
