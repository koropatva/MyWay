package com.hmel.myway.central.models;

import java.io.Serializable;

public class AutosuggestCriteria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String criteria;
	
	private Integer page;
	
	private Integer limit;

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
