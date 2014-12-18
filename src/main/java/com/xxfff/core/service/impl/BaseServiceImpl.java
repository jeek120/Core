package com.xxfff.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.xxfff.core.dao.BaseDao;
import com.xxfff.core.exception.DaoException;
import com.xxfff.core.exception.ServiceException;
import com.xxfff.core.exception.ServiceExceptionConstant;
import com.xxfff.core.model.BaseEntity;
import com.xxfff.core.model.Pager;
import com.xxfff.core.service.BaseService;
import com.xxfff.core.util.UUIDUtils;

@Service
public abstract class BaseServiceImpl<T extends BaseEntity, DaoImpl extends BaseDao<T>>
		implements BaseService<T, DaoImpl> {
	public static Logger logger = Logger.getLogger(BaseServiceImpl.class);

	/* --------------------- start 新增数据 ------------------------ */
	public int save(Map<String, Object> params) throws DaoException,
			ServiceException {
		try {
			return getDaoImpl().save("insert", params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int save(T entity) throws DaoException, ServiceException {
		try {
			return getDaoImpl().save("insert", entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/* --------------------- end 新增数据 ------------------------ */

	/* --------------------- start 新增数据 ------------------------ */
	public int update(Map<String, Object> params) throws DaoException,
			ServiceException {
		try {
			return getDaoImpl().update("update", params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int update(T entity) throws DaoException, ServiceException {
		try {
			return getDaoImpl().update("update", entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/* --------------------- end 新增数据 ------------------------ */

	/* --------------------- start 删除数据 ------------------------ */
	public int delete(Map<String, Object> params) throws DaoException,
			ServiceException {
		try {
			return getDaoImpl().delete("deleteByCondition", params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int delete(T entity) throws DaoException, ServiceException {
		try {
			return getDaoImpl().delete("deleteByCondition", entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}
	
	public int deleteAll(List<String> ids) throws DaoException, ServiceException {
		try {
			return getDaoImpl().delete("deleteAll", ids);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}
	
	public int deleteAll(String[] ids) throws DaoException, ServiceException {
		try {
			return getDaoImpl().delete("deleteAll", ids);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/* --------------------- end 删除数据 ------------------------ */

	/* --------------------- start 返回实体 ---------------------- */
	public T selectOne(Map<String, Object> params) {
		return (T) getDaoImpl().selectOne("fetch", params);
	}

	public T selectOne(T entity) {
		return (T) getDaoImpl().selectOne("fetch", entity);
	}

	/* --------------------- end 返回实体 ---------------------- */

	/* --------------------- start 根据条件返回数据个数 ------------------------ */
	public int count(Map<String, Object> params) {
		Object ret = getDaoImpl().selectOne("count", params);
		if (null == ret)
			return 0;
		else
			return (Integer) ret;
	}

	public int count(T entity) {
		Object ret = getDaoImpl().selectOne("count", entity);
		if (null == ret)
			return 0;
		else
			return (Integer) ret;
	}

	/* --------------------- end 根据条件返回数据个数 ------------------------ */

	/* --------------------- start 返回数据库中某数据的最大值 ------------------------ */
	public Object max(Map<String, Object> params) {
		Object ret = getDaoImpl().selectOne("max", params);
		if (null == ret)
			return 0;
		else
			return (Integer) ret;
	}

	public Object max(T entity) {
		Object ret = getDaoImpl().selectOne("max", entity);
		if (null == ret)
			return 0;
		else
			return (Integer) ret;
	}

	/* --------------------- end 返回数据库中某数据的最大值 ------------------------ */

	/* --------------------- start 根据操作状态操作尸体数组 ------------------------ */
	public int doAllEntities(List<T> entities) throws DaoException, ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		int n = 0;
		if (null == entities) {
			return n;
		}
		for (T e : entities) {
			if (null != e.getOperaterStatus() && 1 == e.getOperaterStatus()) {
				params.put("id", e.getId());
				getDaoImpl().delete("delete", params);
				++n;
				continue;
			}
			saveOrUpdate(e);
			++n;
		}
		return n;
	}

	/* --------------------- end 根据操作状态操作尸体数组 ------------------------ */

	/* --------------------- start 返回分页对象 ------------------------ */
	public Pager<T> query(Map<String, Object> params, Integer currentPage,
			Integer pageSize, String orderBy, String sortBy) {
		Pager<T> pager = new Pager<T>(pageSize, (int) count(params),
				currentPage);
		try {
			if (params == null) {
				params = new HashMap<String, Object>();
			}
			params.put("beginRow",
					(pager.getCurrentPage() - 1) * pager.getPageSize());
			params.put("pageSize", pager.getPageSize());
			pager.setDataList(selectList("select", params));
			return pager;
		} catch (RuntimeException re) {
			logger.error("queryPageBySqlId " + getDaoImpl().getEntityName()
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}

	public Pager<T> query(T entity, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy) {
		Map<String, Object> params = entity.toHashMap();
		return query(params, currentPage, pageSize,
				orderBy, sortBy);
	}
	
	public Map<String, Object> distictOne(String tableField, Map<String, Object> params, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy) {
		try {
			params.put("beginRow",
					(currentPage - 1) * pageSize);
			params.put("pageSize", pageSize);
			params.put("tableField", tableField);
			return this.getDaoImpl().selectMap("distictOne", params);
		} catch (RuntimeException re) {
			logger.error("queryPageBySqlId " + getDaoImpl().getEntityName()
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Object> distictOne(String tableField, T entity, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy) {
		Map<String, Object> params = entity.toHashMap();
		params.put("tableField", tableField);
		return distictOne(tableField, params, currentPage, pageSize,
				orderBy, sortBy);
	}

	/* --------------------- end 返回分页对象 ------------------------ */

	/* -------------------- start 根据条件查询分页对象 ------------------------- */
	public Pager<T> queryPageBySqlId(String sqlId, String countSqlId,
			Map<String, Object> params, Integer currentPage, Integer pageSize,
			String orderBy, String sortBy) {
		Pager<T> pager = new Pager<T>(pageSize, (Integer) selectOne(countSqlId,
				params), currentPage);
		try {
			if (params == null) {
				params = new HashMap<String, Object>();
			}
			params.put("beginRow",
					(pager.getCurrentPage() - 1) * pager.getPageSize());
			params.put("pageSize", pager.getPageSize());
			pager.setDataList(selectList(sqlId, params));
			return pager;
		} catch (RuntimeException re) {
			logger.error("queryPageBySqlId " + getDaoImpl().getEntityName()
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}

	public Pager<T> queryPageBySqlId(String sqlId, String countSqlId, T entity,
			Integer currentPage, Integer pageSize, String orderBy, String sortBy) {
		Map<String, Object> params = new HashMap<String, Object>();
		Pager<T> pager = new Pager<T>(pageSize, (Integer) selectOne(countSqlId,
				params), currentPage);
		try {
			params.put("beginRow",
					(pager.getCurrentPage() - 1) * pager.getPageSize());
			params.put("pageSize", pager.getPageSize());
			pager.setDataList(selectList(sqlId, params));
			return pager;
		} catch (RuntimeException re) {
			logger.error("queryPageBySqlId " + getDaoImpl().getEntityName()
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}

	/* -------------------- end 根据条件查询分页对象 ------------------------- */

	/* 增加或保存数据 */
	public int saveOrUpdate(T entity) throws DaoException, ServiceException {
		if (null == entity) {
			return 0;
		}
		if (null == entity.getId()) {
			entity.setId(UUIDUtils.getUUID());
			return this.save(entity);
		} else {
			return this.update(entity);
		}
	}

	/* 以上是数据库的一些封装操作<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

	/*--------------------------------------------------------------------------------------------------------*/

	/* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>一下是数据哭的一些基本操作 */
	/*------------------------------- start  增加数据 -----------------------------*/
	public int save(String sqlId, Map<String, Object> params) throws DaoException,ServiceException {
		try {
			return getDaoImpl().save(sqlId, params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int save(String sqlId, T entity) throws DaoException,ServiceException {
		try {
			return getDaoImpl().save(sqlId, entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/*----------------------------- end  增加数据 -----------------------------*/

	/*----------------------------- start  更新数据 -----------------------------*/
	public int update(String sqlId, Map<String, Object> params) throws DaoException,ServiceException {
		try {
			return getDaoImpl().update(sqlId, params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int update(String sqlId, T entity) throws DaoException,ServiceException {
		try {
			return getDaoImpl().update(sqlId, entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/*-------------------   end  更新数据  ----------------------*/

	/*-------------------   start  删除数据  ----------------------*/
	public int delete(String sqlId, T entity)  throws DaoException,ServiceException{
		try {
			return getDaoImpl().delete(sqlId, entity);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	public int delete(String sqlId, Map<String, Object> params) throws DaoException,ServiceException {
		try {
			return getDaoImpl().delete(sqlId, params);
		} catch (DaoException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e, ServiceExceptionConstant.ANY, ServiceExceptionConstant.STR_ANY);
		}
	}

	/*-------------------   end  删除数据  ----------------------*/

	/*-------------------   start  返回单条数据  ----------------------*/
	public Object selectOne(String sqlId, Map<String, Object> params) {
		return getDaoImpl().selectOne(sqlId, params);
	}

	public Object selectOne(String sqlId, T entity) {
		return getDaoImpl().selectOne(sqlId, entity);
	}

	/*-------------------   end  返回单条数据  ----------------------*/

	/*-------------------   start  返回符合条件的分页对象  ----------------------*/
	public List<T> selectList(String sqlId, Map<String, Object> params) {
		return getDaoImpl().selectList(sqlId, params);
	}

	public List<T> selectList(String sqlId, T entity) {
		return getDaoImpl().selectList(sqlId, entity);
	}
	/*-------------------   end  返回符合条件的分页对象  ----------------------*/
}
