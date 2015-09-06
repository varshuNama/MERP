package com.mcerp.model;

import java.io.Serializable;

public class ProjectCostEditSheetModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String monthdate;
	private String projectcode;
	private String projectname;
	private String totalcost;
	public String getMonthdate() {
		return monthdate;
	}
	public void setMonthdate(String monthdate) {
		this.monthdate = monthdate;
	}
	public String getProjectcode() {
		return projectcode;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}

}
