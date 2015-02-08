package com.xxfff.core.service;

import java.util.List;
import java.util.Map;

import com.xxfff.core.dao.BaseDao;
import com.xxfff.core.exception.DaoException;
import com.xxfff.core.exception.ServiceException;
import com.xxfff.core.model.BaseEntity;
import com.xxfff.core.model.Pager;

public interface BaseService<T extends BaseEntity, DaoImpl extends BaseDao<T>> {

	/* --------------------- start 新增数据 ------------------------ */
	public int save(Map<String, Object> params) throws DaoException, Exception;
	public int save(T entity) throws DaoException, Exception;
	/* --------------------- end 新增数据 ------------------------ */

	/* --------------------- start 新增数据 ------------------------ */
	public int update(Map<String, Object> params) throws DaoException, ServiceException;
	public int update(T entity) throws DaoException, ServiceException;
	public int update(List<String> ids) throws DaoException, ServiceException;
	public int update(String[] ids) throws DaoException, ServiceException;
	/* --------------------- end 新增数据 ------------------------ */

	/* --------------------- start 删除数据 ------------------------ */
	public int delete(Map<String, Object> params) throws DaoException, ServiceException;

	public int delete(T entity) throws DaoException, ServiceException;
	
	public int delete(List<String> ids) throws DaoException, ServiceException;
	
	public int delete(String[] ids) throws DaoException, ServiceException;

	/* --------------------- end 删除数据 ------------------------ */
	
	/* --------------------- start 返回实体 ---------------------- */
	public T selectOne(Map<String, Object> params);
	public T selectOne(T entity);
	/* --------------------- end 返回实体 ---------------------- */

	/* --------------------- start 根据条件返回数据个数 ------------------------ */
	public int count(Map<String, Object> params);
	public int count(T entity);
	/* --------------------- end 根据条件返回数据个数 ------------------------ */

	/* --------------------- start 返回数据库中某数据的最大值 ------------------------ */
	public Object max(Map<String, Object> params);
	public Object max(T entity);
	/* --------------------- end 返回数据库中某数据的最大值 ------------------------ */

	/* --------------------- start 返回分页对象 ------------------------ */
	public Pager<T> query(Map<String, Object> params, Integer currentPage,
			Integer pageSize, String orderBy, String sortBy);
	public Pager<T> query(T entity, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy);
	/* --------------------- end 返回分页对象 ------------------------ */

	/* -------------------- start 根据条件查询分页对象 ------------------------- */
	public Pager<T> queryPageBySqlId(String sqlId, String countSqlId,
			Map<String, Object> params, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy);
	public Pager<T> queryPageBySqlId(String sqlId, String countSqlId, T entity,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy);
	/* -------------------- end 根据条件查询分页对象 ------------------------- */
	
	/* --------------------- start 根据操作状态操作尸体数组 ------------------------ */
	public int doAllEntities(List<T> entities) throws DaoException, ServiceException;
	/* --------------------- end 根据操作状态操作尸体数组 ------------------------ */

	/* 增加或保存数据 */
	public int saveOrUpdate(T entity) throws DaoException, ServiceException;

	/* 以上是数据库的一些封装操作<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

	/*--------------------------------------------------------------------------------------------------------*/

	/* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>一下是数据哭的一些基本操作 */
	/*------------------------------- start  增加数据 -----------------------------*/
	public int save(String sqlId, Map<String, Object> params) throws DaoException, ServiceException;
	public int save(String sqlId, T entity) throws DaoException, ServiceException;
	/*----------------------------- end  增加数据 -----------------------------*/

	/*----------------------------- start  更新数据 -----------------------------*/
	public int update(String sqlId, Map<String, Object> params) throws DaoException, ServiceException;
	public int update(String sqlId, T entity) throws DaoException, ServiceException;

	/*-------------------   end  更新数据  ----------------------*/

	/*-------------------   start  删除数据  ----------------------*/
	public int delete(String sqlId, T entity) throws DaoException, ServiceException;
	public int delete(String sqlId, Map<String, Object> params) throws DaoException, ServiceException;
	/*-------------------   end  删除数据  ----------------------*/

	/*-------------------   start  返回单条数据  ----------------------*/
	public Object selectOne(String sqlId, Map<String, Object> params);
	public Object selectOne(String sqlId, T entity);
	/*-------------------   end  返回单条数据  ----------------------*/

	/*-------------------   start  返回符合条件的分页对象  ----------------------*/
	public List<T> selectList(String sqlId, Map<String, Object> params);
	public List<T> selectList(String sqlId, T entity);
	/*-------------------   end  返回符合条件的分页对象  ----------------------*/
	
	public DaoImpl getDaoImpl();
	public void setDaoImpl(DaoImpl daoImpl);
	/**
	 * 对某列进行不重复处理
	 * @param tableField
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @param orderBy
	 * @param sortBy
	 * @return
	 */
	public Map<String, Object> distictOne(String tableField, T entity, Integer currentPage,
			Integer pageSize, String orderBy, String sortBy);
	
	/**
	 * 对某列进行不重复处理
	 * @param tableField
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @param orderBy
	 * @param sortBy
	 * @return
	 */
	public Map<String, Object> distictOne(String tableField, Map<String, Object> params,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy);
}
