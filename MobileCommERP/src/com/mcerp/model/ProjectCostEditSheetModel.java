package com.mcerp.model;

import java.io.Serializable;

public class ProjectCostEditSheetModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String monthdate;
	private String projectcode;
	private String projectname;
	private String id;
	private String month_year_date;
	private String totalcost;
	private String sheet_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMonth_year_date() {
		return month_year_date;
	}
	public void setMonth_year_date(String month_year_date) {
		this.month_year_date = month_year_date;
	}
	public String getSheet_id() {
		return sheet_id;
	}
	public void setSheet_id(String sheet_id) {
		this.sheet_id = sheet_id;
	}
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
