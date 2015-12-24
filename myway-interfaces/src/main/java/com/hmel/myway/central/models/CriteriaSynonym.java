package com.hmel.myway.central.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hmel.myway.dao.blogic.interfaces.IEntity;

@Entity
@Table(name = "criteria_synonim_ua")
public class CriteriaSynonym implements IEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Integer rate;

	@ManyToOne
	@JoinColumn(name = "criteria_id")
	private Criteria criteria;

	@Column(name = "creation_time", insertable=false, updatable = false)
	private Date creationTime;

	@Column(name = "modified_time", insertable=false, updatable = false)
	private Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

}
