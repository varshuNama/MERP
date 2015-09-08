package com.mcerp.model;

import java.io.Serializable;

public class LeaveCancealReqModelData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean leave_cancel_checkbox = false;

	private String leavecancelreqedit = "0";

	public String getLeavecancelreqedit() {
		return leavecancelreqedit;
	}

	public void setLeavecancelreqedit(String leavecancelreqedit) {
		this.leavecancelreqedit = leavecancelreqedit;
	}

	public boolean isLeave_cancel_checkbox() {
		return leave_cancel_checkbox;
	}

	private String Id;
	private String MonthYear;
	private String EmpId;
	private String LeavecancelReqRemarks = " ";
	private String LeaveDate;

	private String LeaveDay;

	private String LeaveType;

	private String ProjMgr;
	private String ProjMgrEmail;
	private String ProjMgrName;

	public String getLeaveDate() {
		return LeaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		LeaveDate = leaveDate;
	}

	public String getLeaveDay() {
		return LeaveDay;
	}

	public void setLeaveDay(String leaveDay) {
		LeaveDay = leaveDay;
	}

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}

	public String getProjMgr() {
		return ProjMgr;
	}

	public void setProjMgr(String projMgr) {
		ProjMgr = projMgr;
	}

	public String getProjMgrEmail() {
		return ProjMgrEmail;
	}

	public void setProjMgrEmail(String projMgrEmail) {
		ProjMgrEmail = projMgrEmail;
	}

	public String getProjMgrName() {
		return ProjMgrName;
	}

	public void setProjMgrName(String projMgrName) {
		ProjMgrName = projMgrName;
	}

	public String getProjname() {
		return Projname;
	}

	public void setProjname(String projname) {
		Projname = projname;
	}

	private String Projname;

	public String getLeavecancelReqRemarks() {
		return LeavecancelReqRemarks;
	}

	public void setLeavecancelReqRemarks(String leavecancelReqRemarks) {
		LeavecancelReqRemarks = leavecancelReqRemarks;
	}

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

	public boolean getLeave_cancel_checkbox() {
		return leave_cancel_checkbox;
	}

	public void setLeave_cancel_checkbox(boolean leave_cancel_checkbox) {
		this.leave_cancel_checkbox = leave_cancel_checkbox;
	}

}
