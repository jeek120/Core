package com.xxfff.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xxfff.core.dao.BaseDao;
import com.xxfff.core.exception.DaoException;
import com.xxfff.core.exception.DaoExceptionConstant;
import com.xxfff.core.exception.ServiceException;
import com.xxfff.core.model.BaseEntity;
import com.xxfff.core.util.UUIDUtils;

@Repository("baseDaoImpl")
public class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport
		implements BaseDao<T> {
	public static Logger logger = Logger.getLogger(BaseDaoImpl.class);

	private Class<T> entityClass = null;

	/**
	 * 创建默认构造方法，以取得真正的泛型类型
	 * 
	 * @author Jeek(www.iitgo.net)
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Resource(name = "sqlSessionFactory")
	public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public String getEntityName() {
		return entityClass.getName();
	}

	/*------------------------------- start  增加数据 -----------------------------*/
	/**
	 * 保存对象
	 * 
	 * @param sqlId
	 * @param params
	 * @return 插入了几行数据
	 * @throws DaoException
	 */
	public int save(String sqlId, Map<String, Object> params)
			throws DaoException {
		try {
			if (!params.containsKey("id"))
				params.put("id", UUIDUtils.getUUID());
			return getSqlSession().insert(getEntityName() + "Mapper." + sqlId,
					params);
		} catch (RuntimeException re) {
			logger.error("save " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/**
	 * 保存对象
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public int save(String sqlId, T entity) throws DaoException {
		try {
			/*if (null == entity.getId())
				entity.setId(UUIDUtils.getUUID());*/
			return getSqlSession().insert(getEntityName() + "Mapper." + sqlId,
					entity);
		} catch (RuntimeException re) {
			logger.error("save " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/*----------------------------- end  增加数据 -----------------------------*/

	/*----------------------------- start  更新数据 -----------------------------*/

	/**
	 * 根据sqlId更新对象
	 * 
	 * @param sqlId
	 * @param params
	 * @throws DaoException
	 */
	public int update(String sqlId, Map<String, Object> params)
			throws DaoException {
		try {
			return this.getSqlSession().update(
					getEntityName() + "Mapper." + sqlId, params);
		} catch (RuntimeException re) {
			logger.error("update " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/**
	 * 根据sqlId更新对象
	 * 
	 * @param sqlId
	 * @param entity
	 * @throws DaoException 
	 */
	public int update(String sqlId, T entity) throws DaoException{
		try {
			return this.getSqlSession().update(
					getEntityName() + "Mapper." + sqlId, entity);
		} catch (RuntimeException re) {
			logger.error("delete " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}
	
	/**
	 * 根据sqlId更新多个对象
	 * 
	 * @param sqlId
	 * @param ids
	 * @return
	 * @throws DaoException 
	 */
	public int update(String sqlId, List<String> ids) throws DaoException{
		try {
			return this.getSqlSession().update(
					getEntityName() + "Mapper." + sqlId, ids);
		} catch (RuntimeException re) {
			logger.error("update " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}
	
	/**
	 * 根据sqlId更新多个对象
	 * 
	 * @param sqlId
	 * @param ids
	 * @return
	 * @throws DaoException 
	 */
	public int update(String sqlId, String[] ids) throws DaoException{
		try {
			return this.getSqlSession().update(
					getEntityName() + "Mapper." + sqlId, ids);
		} catch (RuntimeException re) {
			logger.error("update " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

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
	public int delete(String sqlId, T entity) throws DaoException {
		try {
			return this.getSqlSession().delete(
					getEntityName() + "Mapper." + sqlId, entity);
		} catch (RuntimeException re) {
			logger.error("delete " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param sqlId
	 * @param params
	 * @return 返回删除数据的个数
	 * @throws DaoException
	 */
	public int delete(String sqlId, Map<String, Object> params)
			throws DaoException {
		try {
			return this.getSqlSession().delete(
					getEntityName() + "Mapper." + sqlId, params);
		} catch (RuntimeException re) {
			logger.error("delete " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/**
	 * 根据sqlId删除数据
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public int delete(String sqlId, List<String> ids) throws DaoException {
		try {
			return this.getSqlSession().delete(
					getEntityName() + "Mapper." + sqlId, ids);
		} catch (RuntimeException re) {
			logger.error("delete " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/**
	 * 根据sqlId删除数据
	 * 
	 * @param sqlId
	 * @param entity
	 * @return
	 * @throws DaoException
	 */
	public int delete(String sqlId, String[] ids) throws DaoException {
		try {
			return this.getSqlSession().delete(
					getEntityName() + "Mapper." + sqlId, ids);
		} catch (RuntimeException re) {
			logger.error("delete " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
			throw new DaoException(re, DaoExceptionConstant.ANY,DaoExceptionConstant.STR_ANY);
		}
	}

	/*-------------------   end  删除数据  ----------------------*/

	/*-------------------   start  返回单条数据  ----------------------*/
	/**
	 * 根据条件，返回记录个数
	 * 
	 * @param sqlId
	 * @param params
	 * @return 返回一个数字
	 */
	public Object selectOne(String sqlId, Map<String, Object> params) {
		try {
			return this.getSqlSession().selectOne(
					getEntityName() + "Mapper." + sqlId, params);
		} catch (RuntimeException re) {
			logger.error("selectInt " + getEntityName() + "Mapper." + sqlId
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据条件，返回记录个数
	 * 
	 * @param sqlId
	 * @param entity
	 * @return 返回一个对象
	 */
	public Object selectOne(String sqlId, T entity) {
		try {
			return this.getSqlSession().selectOne(
					getEntityName() + "Mapper." + sqlId, entity);
		} catch (RuntimeException re) {
			logger.error("selectInt " + getEntityName() + "Mapper." + sqlId
					+ "failed :{}", re);
			re.printStackTrace();
		}
		return 0;
	}

	/*-------------------   end  返回单条数据  ----------------------*/

	/*-------------------   start  返回符合条件的分页对象  ----------------------*/
	/**
	 * @param sqlId
	 *            查询的Sql语句
	 * @param countSqlId
	 *            统计数据数量的Sql语句
	 * @param params
	 *            传入Sql的参数
	 * @return Pager<T>
	 */
	public List<T> selectList(String sqlId, Map<String, Object> params) {
		try {
			return this.getSqlSession().selectList(
					getEntityName() + "Mapper." + sqlId, params);
		} catch (RuntimeException re) {
			logger.error("selectList " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}

	public Map<String, Object> selectMap(String sqlId,
			Map<String, Object> params) {
		try {
			return this.getSqlSession().selectMap(
					getEntityName() + "Mapper." + sqlId, params, "id");
		} catch (RuntimeException re) {
			logger.error("selectList " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据条件进行查询
	 * 
	 * @param sqlId
	 *            查询的Sql语句
	 * @param countSqlId
	 *            统计数据数量的Sql语句
	 * @param entity
	 *            传入Sql的参数
	 * @return List<T>
	 */
	public List<T> selectList(String sqlId, T entity) {
		try {
			return this.getSqlSession().selectList(
					getEntityName() + "Mapper." + sqlId, entity);
		} catch (RuntimeException re) {
			logger.error("selectList " + getEntityName() + "Mapper." + sqlId
					+ " failed :{}", re);
			re.printStackTrace();
		}
		return null;
	}
	/*-------------------   end  返回符合条件的分页对象  ----------------------*/
}