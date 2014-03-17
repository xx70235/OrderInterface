package org.xjd.client.models;

import java.io.Serializable;
import java.util.Date;

public class OrderModel implements Serializable {

	String taskId;
	String type;
	String name;
	Date startDate;
	Date endDate;
	String range;
	String rangeScale;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getRange() {
		return range;
	}
	public void setRage(String range) {
		this.range = range;
	}
	public String getRangeScale() {
		return rangeScale;
	}
	public void setRageScale(String rangeScale) {
		this.rangeScale = rangeScale;
	}
	
	
	public String generateTaskId()
	{
		//TODO: generate task id
		return "testID";
	}
	
	
}
