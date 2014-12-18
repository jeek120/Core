package com.xxfff.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 实体数组
 * @author jeek
 *
 * @param <T>
 */
public class BaseEntityArray<T extends BaseEntity> implements Serializable {
	private static final long serialVersionUID = 1847593255532494015L;
	
	private List<T> entityArray;

	public List<T> getEntityArray() {
		return entityArray;
	}

	public void setEntityArray(List<T> entityArray) {
		this.entityArray = entityArray;
	}
}