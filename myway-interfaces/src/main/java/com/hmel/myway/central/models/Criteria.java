package com.hmel.myway.central.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hmel.myway.central.enums.CriteriaType;

@Entity
@Table(name = "criteria")
public class Criteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String description;

	@Enumerated(EnumType.STRING)
	private CriteriaType type;

	@Column(name = "creation_time")
	private Date creationTime;

	@Column(name = "modified_time")
	private Date modifiedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CriteriaType getType() {
		return type;
	}

	public void setType(CriteriaType type) {
		this.type = type;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	@Override
	public String toString() {
		return "Criteria [id=" + id + ", name=" + name + ", description="
				+ description + ", type=" + type + ", creationTime="
				+ creationTime + ", modifiedTime=" + modifiedTime + "]";
	}

}
