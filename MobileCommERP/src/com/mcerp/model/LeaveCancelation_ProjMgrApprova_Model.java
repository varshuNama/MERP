package com.mcerp.model;

import java.io.Serializable;

public class LeaveCancelation_ProjMgrApprova_Model implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private boolean checkboxstatus = false;
	private boolean radioApprove = true;

	private boolean radioReject = false;

	private String Id;
	private String EmpName;
	private String Email;
	private String ProjName;
	private String LeaveDate;
	private String LeaveDay;
	private String LeaveType;
	private String CancelRemark;
	private int rejectOrApprove;
	private String LeaveDateMnthYr;
	private String ReqEmpId;

	public String getReqEmpId() {
		return ReqEmpId;
	}

	public void setReqEmpId(String reqEmpId) {
		ReqEmpId = reqEmpId;
	}

	public String getLeaveDateMnthYr() {
		return LeaveDateMnthYr;
	}

	public void setLeaveDateMnthYr(String leaveDateMnthYr) {
		LeaveDateMnthYr = leaveDateMnthYr;
	}

	private String yes_no_status = "3";
	private String Remarks = " ";

	

	public boolean isRadioApprove() {
		return radioApprove;
	}

	public void setRadioApprove(boolean radioApprove) {
		this.radioApprove = radioApprove;
	}

	public boolean isRadioReject() {
		return radioReject;
	}

	public void setRadioReject(boolean radioReject) {
		this.radioReject = radioReject;
	}

	public boolean isCheckboxstatus() {
		return checkboxstatus;
	}

	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}

	public int getRejectOrApprove() {
		return rejectOrApprove;
	}

	public void setRejectOrApprove(int rejectOrApprove) {
		this.rejectOrApprove = rejectOrApprove;
	}

	public String getYes_no_status() {
		return yes_no_status;
	}

	public void setYes_no_status(String yes_no_status) {
		this.yes_no_status = yes_no_status;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getProjName() {
		return ProjName;
	}

	public void setProjName(String projName) {
		ProjName = projName;
	}

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

	public String getCancelRemark() {
		return CancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		CancelRemark = cancelRemark;
	}

	public boolean getCheckboxstatus() {
		return checkboxstatus;
	}

}
