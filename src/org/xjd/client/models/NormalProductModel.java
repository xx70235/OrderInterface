package org.xjd.client.models;

import java.io.Serializable;
import java.util.Date;

public class NormalProductModel implements Serializable {
	
	String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String id;
	String name;
	Date startDate;
	Date endDate;
	String coverRange;
	String spaceResolution;
	String timeResolution;
	float precision;
	
	public enum ProductStatus
	{
		Booked,
		Available
	}
	
	ProductStatus npStatus;
	
	
	public ProductStatus getNpStatus() {
		return npStatus;
	}
	public void setNpStatus(ProductStatus npStatus) {
		this.npStatus = npStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCoverRange() {
		return coverRange;
	}
	public void setCoverRange(String coverRange) {
		this.coverRange = coverRange;
	}
	public String getSpaceResolution() {
		return spaceResolution;
	}
	public void setSpaceResolution(String spaceResolution) {
		this.spaceResolution = spaceResolution;
	}
	public String getTimeResolution() {
		return timeResolution;
	}
	public void setTimeResolution(String timeResolution) {
		this.timeResolution = timeResolution;
	}
	public float getPrecision() {
		return precision;
	}
	public void setPrecision(float precision) {
		this.precision = precision;
	}
	
	

}
