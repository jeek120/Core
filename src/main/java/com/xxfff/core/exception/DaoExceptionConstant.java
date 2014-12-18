package com.xxfff.core.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface DaoExceptionConstant {
	/**一项操作成功地执行，但在释放数据库资源时发生异常（例如，关闭一个Connection）*/
	public static int CLEANUPFAILUREDATAACCESSEXCEPTION = 1;
	/**数据访问资源彻底失败，例如不能连接数据库*/
	public static int DATAACCESSRESOURCEFAILUREEXCEPTION = 2;
	/**Insert或Update数据时违反了完整性，例如违反了惟一性限制*/
	public static int DATAINTEGRITYVIOLATIONEXCEPTION = 3;
	/**某些数据不能被检测到，例如不能通过关键字找到一条记录*/
	public static int DATARETRIEVALFAILUREEXCEPTION = 4;
	/**当前的操作因为死锁而失败*/
	public static int DEADLOCKLOSERDATAACCESSEXCEPTION = 5;
	/**Update时发生某些没有预料到的情况，例如更改超过预期的记录数。当这个异常被抛出时，执行着的事务不会被回滚*/
	public static int INCORRECTUPDATESEMANTICSDATAACCESSEXCEPTION = 6;
	/**一个数据访问的JAVA API没有正确使用，例如必须在执行前编译好的查询编译失败了*/
	public static int INVALIDDATAACCESSAPIUSAGEEXCEPTION = 7;
	/**错误使用数据访问资源，例如用错误的SQL语法访问关系型数据库*/
	public static int INVALIDDATAACCESSRESOURCEUSAGEEXCEPTION = 8;
	/**乐观锁的失败。这将由ORM工具或用户的DAO实现抛出*/
	public static int OPTIMISTICLOCKINGFAILUREEXCEPTION = 9;
	/**Java类型和数据类型不匹配，例如试图把String类型插入到数据库的数值型字段中*/
	public static int TYPEMISMATCHDATAACCESSEXCEPTION = 10;
	/**有错误发生，但无法归类到某一更为具体的异常中*/
	public static int UNCATEGORIZEDDATAACCESSEXCEPTION = 11;
	/**某列不能为空*/
	public static int MYSQLINTEGRITYCONSTRAINTVIOLATIONEXCEPTION = 12;
	/**任意一种异常*/
	public static int ANY = 0xff;
	public static String STR_ANY = "任意一种异常";
}
