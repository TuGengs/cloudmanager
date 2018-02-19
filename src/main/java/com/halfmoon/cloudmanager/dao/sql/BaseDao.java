package com.halfmoon.cloudmanager.dao.sql;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

/**
 * 实现了数据库的四个基本操作
 * Mybatis的Mapper里的namespace为该类的子类的全名，比如
 * com.halfmoon.cloudmanager.dao.user.impl.UserDao
 * @author hzq
 *
 * @param <Bean> JavaBean类
 */
public class BaseDao<Bean> implements BasicOperation<Bean> {
	
	private static final String ADD_SUFFIX    = "add";
	private static final String DELETE_SUFFIX = "delete";
	private static final String MODIFY_SUFFIX = "modify";
	private static final String GET_SUFFIX    = "get";
	
	// 命名空间（已经在末尾加上"."）
	protected String NAMESPACE = this.getClass().getName().concat(".");

	/**
	 * 获取MyBatis的SqlSession，已在配置文件中配置好
	 */
	@Resource
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
	@Override
	public boolean add(Bean bean) {
		
		return sqlSession.insert(
				this.NAMESPACE
				.concat(ADD_SUFFIX), bean) > 0;
				
	}

	@Override
	public boolean delete(int id) {
		
		return sqlSession.delete(
				this.NAMESPACE
				.concat(DELETE_SUFFIX), id) > 0;
				
	}

	@Override
	public boolean modify(Bean bean) {
		
		return sqlSession.update(
				this.NAMESPACE
				.concat(MODIFY_SUFFIX), bean) > 0;
				
	}

	@Override
	public Bean get(int id) {
		
		return sqlSession.selectOne(
				NAMESPACE
				.concat(GET_SUFFIX), id);

	}

}
