package org.xjd.client.models;

import java.io.Serializable;
import java.util.Date;

public class NormalProductFeasibilityModel implements Serializable{

	
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public int getProductCycle() {
		return productCycle;
	}
	public void setProductCycle(int productCycle) {
		this.productCycle = productCycle;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public NormalProductModel getNpm() {
		return npm;
	}
	public void setNpm(NormalProductModel npm) {
		this.npm = npm;
	}
	public boolean isNeedAssimilation() {
		return needAssimilation;
	}
	public void setNeedAssimilation(boolean needAssimilation) {
		this.needAssimilation = needAssimilation;
	}
	public boolean isNeedValdation() {
		return needValdation;
	}
	public void setNeedValdation(boolean needValdation) {
		this.needValdation = needValdation;
	}
	public String getBounds() {
		return bounds;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	public SatelliteModel[] getSatellites() {
		return satellites;
	}
	public void setSatellites(SatelliteModel[] satellites) {
		this.satellites = satellites;
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
	public int getCoverPercent() {
		return coverPercent;
	}
	public void setCoverPercent(int coverPercent) {
		this.coverPercent = coverPercent;
	}
	
	String taskID;
	Date productDate;
	int productCycle;
	float fee;
	NormalProductModel npm;
	boolean needAssimilation;//是否需要同化
	boolean needValdation;//是否需要真实性检验
	String bounds;
	SatelliteModel[] satellites;
	Date startDate;
	Date endDate;
	int coverPercent;
	
	boolean isFeasibility;

	public boolean isFeasibility() {
		return isFeasibility;
	}
	public void setFeasibility(boolean isFeasibility) {
		this.isFeasibility = isFeasibility;
	}
}
