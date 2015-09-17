package com.mcerp.model;

import java.io.Serializable;

public class ProjectCostReortModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String MY;
	private String MonthYear;
	private String ProjCode;
	private String  Projname;
	private String ProjctedCost;
	private String PFwdCost;
	private String NetProjectedCost;
	private String ActualCost;
	private String CostGap ;
	private String Closed;
	public String getMY() {
		return MY;
	}
	public void setMY(String mY) {
		MY = mY;
	}
	public String getMonthYear() {
		return MonthYear;
	}
	public void setMonthYear(String monthYear) {
		MonthYear = monthYear;
	}
	public String getProjCode() {
		return ProjCode;
	}
	public void setProjCode(String projCode) {
		ProjCode = projCode;
	}
	public String getProjname() {
		return Projname;
	}
	public void setProjname(String projname) {
		Projname = projname;
	}
	public String getProjctedCost() {
		return ProjctedCost;
	}
	public void setProjctedCost(String projctedCost) {
		ProjctedCost = projctedCost;
	}
	public String getPFwdCost() {
		return PFwdCost;
	}
	public void setPFwdCost(String pFwdCost) {
		PFwdCost = pFwdCost;
	}
	public String getNetProjectedCost() {
		return NetProjectedCost;
	}
	public void setNetProjectedCost(String netProjectedCost) {
		NetProjectedCost = netProjectedCost;
	}
	public String getActualCost() {
		return ActualCost;
	}
	public void setActualCost(String actualCost) {
		ActualCost = actualCost;
	}
	public String getCostGap() {
		return CostGap;
	}
	public void setCostGap(String costGap) {
		CostGap = costGap;
	}
	public String getClosed() {
		return Closed;
	}
	public void setClosed(String closed) {
		Closed = closed;
	}

}
