package com.hmel.myway.central.conditioholder;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.hmel.myway.central.holder.BaseConditionHolder;
import com.hmel.myway.central.models.Block;

/**
 * @author bismark
 *
 */
public class BlockConditionHolder  extends BaseConditionHolder{
	
	public int firstPage=0;
	public int pageSize=10;
	private String label;
	private String shortDescription;
	private String destinationLabel;
	private String destinationShortDescription;
	
	public BlockConditionHolder(int firstPage, int pageSize) {
		this.firstPage = firstPage;
		this.pageSize = pageSize;
	}

	public DetachedCriteria loadCriteria(){
		DetachedCriteria cr = DetachedCriteria.forClass(Block.class);
		if(StringUtils.isNotBlank(label)){
			cr.add(Restrictions.like("label", label, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(shortDescription)){
			cr.add(Restrictions.like("shortDescription", shortDescription, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(destinationLabel)){
			cr.add(Restrictions.like("destinationLabel", destinationLabel, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(destinationShortDescription)){
			cr.add(Restrictions.like("destinationShortDescription", destinationShortDescription, MatchMode.ANYWHERE));
		}
		return cr;
	}
	
	public void reset(){
		label=null;
		pageSize=10;
		firstPage=0;
		shortDescription=null;
		destinationLabel=null;
		destinationShortDescription=null;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDestinationLabel() {
		return destinationLabel;
	}
	public void setDestinationLabel(String destinationLabel) {
		this.destinationLabel = destinationLabel;
	}
	public String getDestinationShortDescription() {
		return destinationShortDescription;
	}
	public void setDestinationShortDescription(String destinationShortDescription) {
		this.destinationShortDescription = destinationShortDescription;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
}