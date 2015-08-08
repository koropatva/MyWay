package com.hmel.myway.central.models;

import java.io.Serializable;
import java.util.List;

public class Autosuggest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Autosuggest(){
		
	}
	
	
	public Autosuggest(long currentPage, long totalItems, List<Criteria> criterias) {
		super();
		this.currentPage = currentPage;
		this.totalItems = totalItems;
		this.criterias = criterias;
	}


	private long id;
	
	private long currentPage;
	
	private long totalItems;
	
	private List<Criteria> criterias;

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public List<Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
