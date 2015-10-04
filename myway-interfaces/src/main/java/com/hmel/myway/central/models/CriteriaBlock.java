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

@Entity
@Table(name = "criteria_block")
public class CriteriaBlock implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "criteriaId")
	private Criteria criteria;

	@ManyToOne
	@JoinColumn(name = "blockId")
	private Block block;

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

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	@Override
	public String toString() {
		return "CriteriaBlock [id=" + id + ", criteria=" + criteria
				+ ", block=" + block + ", creationTime=" + creationTime
				+ ", modifiedTime=" + modifiedTime + "]";
	}

}
