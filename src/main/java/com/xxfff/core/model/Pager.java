package com.xxfff.core.model;

import java.util.List;
import java.util.Map;

public class Pager<T> {

	// 分页结果集
	private List<T> dataList = null;
	
	private Map<String, Object> map = null;

	// 记录总数
	private int totalCount = 0;

	// 每页显示记录数
	private int pageSize = 0;

	// 当前页数
	private int currentPage = 1;

	private int pageCount = 1;
	
	/* 初始化分页组件 */
	public Pager(Integer pageSize, Integer totalCount,Integer currentPage) {
				
		if(null==pageSize){
			pageSize = 15;
		}
		
		setPageSize(pageSize);
		
		if(null == totalCount){
			totalCount = 0;
		}
		
		setTotalCount(totalCount);
		
		Double pageNum=Math.ceil((getTotalCount()+0.00)/pageSize);
		
		if(null==currentPage||currentPage<1||totalCount==0){
			
			setCurrentPage(1);
		
		}else if(currentPage>pageNum.intValue()){
		
			setCurrentPage(pageNum.intValue());
		
		}else{
			
			setCurrentPage(currentPage);
			
		}

		setPageCount(pageNum.intValue());
		
	}

	public Pager(int totalCount, int pageSize, int currentPage,List<T> dataList) {
		this.dataList = dataList;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
	}
	
	public Pager(Pager pager) {
		pageSize = pager.getPageSize();
		currentPage = pager.getCurrentPage();
		totalCount = pager.getTotalCount();
	}
	
	public Pager() {
	}
	
	public boolean isLastPage(){
		return ((this.getCurrentPage() + 1) * this.getPageSize()) >= this.getPageCount();
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// 计算总页数，如果为0则置为1
	public int getPageCount() {

		return pageCount ;

	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	/**
	 * FunName:    	    updatePageCount
	 * Description :   	更新总页数
	 * @Author:         袁鹏(Jeek)
	 * @Create Date: 	2013年9月17日 下午2:31:36
	 */
	public void updatePageCount() {
		if(totalCount == 0){
			this.pageCount = 0;
		}else{
			this.pageCount = (totalCount - 1)/this.pageSize;
		}
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
