package com.xxfff.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体
 * @author jeek
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 6509066057807634977L;

	private Long id;
	
	/**
	 * 数据状态，0---禁用(系统后台数据库不显示)  1---可以使用   2---屏蔽
	 */
	private String status;
	
	/**
	 * 数据的创建人
	 */
	private Long creator;
	
	/**
	 * 创建时间
	 */
	private Date creatTime;
	private Date beginCreatTime;
	private Date endCreatTime;
	
	/**
	 * 创建记录的Ip
	 */
	private String creatIp;
	
	/**
	 * 修改人
	 */
	private Long operator;
	
	/**
	 * 修改时间
	 */
	private Date operatTime;
	private Date beginOperatTime;
	private Date endOperatTime;
	
	/**
	 * 修改记录的Ip
	 */
	private String operatIp;
	
	/**
	 * 版本号
	 */
	private Integer version;
	private Integer geVersion;
	private Integer leVersion;
	
	/**
	 * 操作状态，临时状态，不存到数据库中
	 * <p>
	 * 1.删除
	 * </p>
	 */
	private Integer operaterStatus;
	
	public Map<String, Object> toHashMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		if (getId() != null) {
			params.put("id", getId());
		}
		/* 数据状态 */
		if(getStatus() != null){
			params.put("status", getStatus());
		}
		/* 数据创建人 */
		if(getCreator() != null){
			params.put("creator", getCreator());
		}
		/* 数据创建时间 */
		if(getCreatTime() != null){
			params.put("creatTime", getCreatTime());
		}
		if(getBeginCreatTime() != null){
			params.put("beginCreatTime", getBeginCreatTime());
		}
		if(getEndCreatTime() != null){
			params.put("endCreatTime", getEndCreatTime());
		}
		/* 数据创建ip */
		if(getCreatIp() != null){
			params.put("creatIp", getCreatIp());
		}

		/* 数据创建人 */
		if(getOperator() != null){
			params.put("operator", getOperator());
		}
		/* 数据修改时间 */
		if(getOperatTime() != null){
			params.put("operatTime", getOperatTime());
		}
		if(getBeginOperatTime() != null){
			params.put("beginOperatTime", getBeginOperatTime());
		}
		if(getEndOperatTime() != null){
			params.put("endOperatTime", getEndOperatTime());
		}
		/* 数据修改ip */
		if(getOperatIp() != null){
			params.put("operatIp", getOperatIp());
		}
		/* 数据的版本号 */
		if (getVersion() != null) {
			params.put("version", getVersion());
		}
		
		/*if (getDomain() != null) {
			params.put("domain", getDomain());
		}
		if (getAndLikeDomain() != null) {
			params.put("andLikeDomain", getAndLikeDomain());
		}
		if (getStartLikeDomain() != null) {
			params.put("startLikeDomain", getStartLikeDomain());
		}*/
		return params;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		return id.equals(other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOperaterStatus() {
		return operaterStatus;
	}

	public void setOperaterStatus(Integer operaterStatus) {
		this.operaterStatus = operaterStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatIp() {
		return creatIp;
	}

	public void setCreatIp(String creatIp) {
		this.creatIp = creatIp;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getOperatTime() {
		return operatTime;
	}

	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

	public String getOperatIp() {
		return operatIp;
	}

	public void setOperatIp(String operatIp) {
		this.operatIp = operatIp;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getBeginCreatTime() {
		return beginCreatTime;
	}

	public void setBeginCreatTime(Date beginCreatTime) {
		this.beginCreatTime = beginCreatTime;
	}

	public Date getEndCreatTime() {
		return endCreatTime;
	}

	public void setEndCreatTime(Date endCreatTime) {
		this.endCreatTime = endCreatTime;
	}

	public Date getBeginOperatTime() {
		return beginOperatTime;
	}

	public void setBeginOperatTime(Date beginOperatTime) {
		this.beginOperatTime = beginOperatTime;
	}

	public Date getEndOperatTime() {
		return endOperatTime;
	}

	public void setEndOperatTime(Date endOperatTime) {
		this.endOperatTime = endOperatTime;
	}

	public Integer getGeVersion() {
		return geVersion;
	}

	public void setGeVersion(Integer geVersion) {
		this.geVersion = geVersion;
	}

	public Integer getLeVersion() {
		return leVersion;
	}

	public void setLeVersion(Integer leVersion) {
		this.leVersion = leVersion;
	}

	/**
	 * 网站的域名
	 
	private String domain;
	private String andLikeDomain;
	private String startLikeDomain;
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAndLikeDomain() {
		return andLikeDomain;
	}

	public void setAndLikeDomain(String andLikeDomain) {
		this.andLikeDomain = andLikeDomain;
	}

	public String getStartLikeDomain() {
		return startLikeDomain;
	}

	public void setStartLikeDomain(String startLikeDomain) {
		this.startLikeDomain = startLikeDomain;
	}*/
}