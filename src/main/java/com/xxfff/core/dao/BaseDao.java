package com.xxfff.core.dao;

import java.util.List;
import java.util.Map;

import com.xxfff.core.exception.DaoException;
import com.xxfff.core.model.BaseEntity;

/**
 * 底层基本的dao的接口
 * 
 * @author Jeek(www.pf.net)
 * 
 * @param <T>
 */
public interface BaseDao<T extends BaseEntity> {
	/*------------------------------- start  增加数据 -----------------------------*/
	/**
	 * 保存对象
	 * 
	 * @param sqlId
	 * @param params
	 * @return 插入了几行数据
	 * @throws DaoException 
	 */
	public int save(String sqlId, Map<String, Object> params) throws DaoException;

	/**
	 * 保存对象
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException 
	 */
	public int save(String sqlId, T entity) throws DaoException;

	/*----------------------------- end  增加数据 -----------------------------*/

	/*----------------------------- start  更新数据 -----------------------------*/

	/**
	 * 根据sqlId更新对象
	 * 
	 * @param sqlId
	 * @param params
	 * @throws DaoException 
	 */
	public int update(String sqlId, Map<String, Object> params) throws DaoException;

	/**
	 * 根据sqlId更新对象
	 * 
	 * @param sqlId
	 * @param entity
	 * @throws DaoException 
	 */
	public int update(String sqlId, T entity) throws DaoException;

	/*-------------------   end  更新数据  ----------------------*/

	/*-------------------   start  删除数据  ----------------------*/
	/**
	 * 根据sqlId删除数据
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException 
	 */
	public int delete(String sqlId, T entity) throws DaoException;

	/**
	 * 根据条件删除数据
	 * 
	 * @param sqlId
	 * @param params
	 * @return 返回删除数据的个数
	 * @throws DaoException 
	 */
	public int delete(String sqlId, Map<String, Object> params) throws DaoException;
	
	/**
	 * 根据sqlId删除数据
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException 
	 */
	public int delete(String sqlId, List<String> ids) throws DaoException;
	
	/**
	 * 根据sqlId删除数据
	 * 
	 * @param sqlId
	 * @param ids
	 * @return
	 * @throws DaoException 
	 */
	public int delete(String sqlId, String[] ids) throws DaoException;

	/*-------------------   end  删除数据  ----------------------*/

	/*-------------------   start  返回单条数据  ----------------------*/
	/**
	 * 根据条件，返回一个对象
	 * 
	 * @param sqlId
	 * @param params
	 * @return 返回一个数字
	 */
	public Object selectOne(String sqlId, Map<String, Object> params);

	/**
	 * 根据条件，返回一个对象
	 * 
	 * @param sqlId
	 * @param entity
	 * @return 返回一个对象
	 */
	public Object selectOne(String sqlId, T entity);

	/*-------------------   end  返回单条数据  ----------------------*/

	/*-------------------   start  返回符合条件的分页对象  ----------------------*/
	/**
	 * 根据条件进行查询
	 * @param sqlId
	 *            查询的Sql语句
	 * @param params
	 *            传入Sql的参数
	 * @return List<T>
	 */
	public List<T> selectList(String sqlId, Map<String, Object> params);
	
	/**
	 * 根据条件进行查询
	 * @param sqlId
	 *            查询的Sql语句
	 * @param entity
	 *            传入Sql的参数
	 * @return List<T>
	 */
	public List<T> selectList(String sqlId, T entity);
	
	/**
	 * 返回Map
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectMap(String sqlId, Map<String, Object> params);
	/*-------------------   end  返回符合条件的分页对象  ----------------------*/
	
	public String getEntityName();
}
