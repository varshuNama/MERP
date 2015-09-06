package com.mcerp.model;

import java.io.Serializable;

public class ApproveTimesheetModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Id;
	private String MonthYear;
	private String EmpId;
	private String  EmpName;
	private String Circle;
	private String Month;
	private String Project;
	private String NOD;
	private String ProjType;
	private String ProjStart;
	private String TSStart;
	private String CurrMonth;
	private String ApprovelFlag;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMonthYear() {
		return MonthYear;
	}
	public void setMonthYear(String monthYear) {
		MonthYear = monthYear;
	}
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
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
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getNOD() {
		return NOD;
	}
	public void setNOD(String nOD) {
		NOD = nOD;
	}
	public String getProjType() {
		return ProjType;
	}
	public void setProjType(String projType) {
		ProjType = projType;
	}
	public String getProjStart() {
		return ProjStart;
	}
	public void setProjStart(String projStart) {
		ProjStart = projStart;
	}
	public String getTSStart() {
		return TSStart;
	}
	public void setTSStart(String tSStart) {
		TSStart = tSStart;
	}
	public String getCurrMonth() {
		return CurrMonth;
	}
	public void setCurrMonth(String currMonth) {
		CurrMonth = currMonth;
	}
	public String getApprovelFlag() {
		return ApprovelFlag;
	}
	public void setApprovelFlag(String approvelFlag) {
		ApprovelFlag = approvelFlag;
	}

}
