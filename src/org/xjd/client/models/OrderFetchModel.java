package org.xjd.client.models;

import java.io.Serializable;

public class OrderFetchModel  implements Serializable{

	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderStartTime() {
		return orderStartTime;
	}
	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	public String getOrderEndTime() {
		return orderEndTime;
	}
	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}
	String orderType;
	String orderStartTime;
	String orderEndTime;
	
	
}
