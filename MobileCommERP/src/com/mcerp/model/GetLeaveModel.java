package com.mcerp.model;

import java.io.Serializable;

public class GetLeaveModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id_All;
	private String EmpId;

	private String EmpCode;
	private String EmpEmail;
	private String EmpName;
	private String LeaveReason;
	private String NOD;
	private String LeaveFrom;
	private String LeaveTo;
	private String Project;
	private String Circle;
	private String LeaveType;

	

	public GetLeaveModel() {
		super();
	}

	public String getId_All() {
		return Id_All;
	}

	public void setId_All(String id_All) {
		Id_All = id_All;
	}

	public String getEmpId() {
		return EmpId;
	}

	public void setEmpId(String empId) {
		EmpId = empId;
	}

	public String getEmpCode() {
		return EmpCode;
	}

	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}

	public String getEmpEmail() {
		return EmpEmail;
	}

	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getLeaveReason() {
		return LeaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		LeaveReason = leaveReason;
	}

	public String getNOD() {
		return NOD;
	}

	public void setNOD(String nOD) {
		NOD = nOD;
	}

	public String getLeaveFrom() {
		return LeaveFrom;
	}

	public void setLeaveFrom(String leaveFrom) {
		LeaveFrom = leaveFrom;
	}

	public String getLeaveTo() {
		return LeaveTo;
	}

	public void setLeaveTo(String leaveTo) {
		LeaveTo = leaveTo;
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

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}
}
