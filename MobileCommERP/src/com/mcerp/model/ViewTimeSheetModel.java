package com.mcerp.model;

import java.io.Serializable;

public class ViewTimeSheetModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ProjMgrName;
	private String ProjMgr;
	private String Project;
	private String Circle;
	private String Month;
	private String MonthYear;
	private String TSStart;
	private String Saved;
	private String Pending;
	private String Rejected;
	private String Approved;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjMgrName() {
		return ProjMgrName;
	}
	public void setProjMgrName(String projMgrName) {
		ProjMgrName = projMgrName;
	}
	public String getProjMgr() {
		return ProjMgr;
	}
	public void setProjMgr(String projMgr) {
		ProjMgr = projMgr;
	}
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getCircle() {
		return Circle;
	}
	public void setCircle(String circle) {
		Circle = circle;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getMonthYear() {
		return MonthYear;
	}
	public void setMonthYear(String monthYear) {
		MonthYear = monthYear;
	}
	public String getTSStart() {
		return TSStart;
	}
	public void setTSStart(String tSStart) {
		TSStart = tSStart;
	}
	public String getSaved() {
		return Saved;
	}
	public void setSaved(String saved) {
		Saved = saved;
	}
	public String getPending() {
		return Pending;
	}
	public void setPending(String pending) {
		Pending = pending;
	}
	public String getRejected() {
		return Rejected;
	}
	public void setRejected(String rejected) {
		Rejected = rejected;
	}
	public String getApproved() {
		return Approved;
	}
	public void setApproved(String approved) {
		Approved = approved;
	}
	

	
}
