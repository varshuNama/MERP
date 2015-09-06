package com.mcerp.model;

import java.io.Serializable;

public class NewTimesheetModel  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String Pending;
	private String Approved;
	private String NoOfDays;
	private String TSDays;
	private String TimesheetId;
	private String ProjAllocationId;
	private String EmpId;
	private String ProjCode;
	private String ProjMgr;
	private String Country;
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	private String CircleMarket;
	private String ProjType;
	private String ProjStart;
	private String ProjEnd;
	private String TSStart;
	private String TSEnd;
	private String TSFlag;
	private String TSDate;
	private String Leave;
	private String LeaveNOD;
	private String Holiday;
	private String TSDayStatus;
	private String TSDId;
	private String TimsheetDate;
	private String TimsheetWeekDay;
	private String LHStatus;
	private String TSStatus;
	private String TSStatusDesc;
	private String TSActivity;
	private String TSDescription;
	private String DA;
	private String DAId;
	private String DAAmount;
	private String DACurType;
	private String AssetStockId;
	private String TSMailDetail;
	private String ProjectDesc;
	private String TimesheetType;
	public String getPending() {
		return Pending;
	}
	public void setPending(String pending) {
		Pending = pending;
	}
	public String getApproved() {
		return Approved;
	}
	public void setApproved(String approved) {
		Approved = approved;
	}
	public String getNoOfDays() {
		return NoOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		NoOfDays = noOfDays;
	}
	public String getTSDays() {
		return TSDays;
	}
	public void setTSDays(String tSDays) {
		TSDays = tSDays;
	}
	public String getTimesheetId() {
		return TimesheetId;
	}
	public void setTimesheetId(String timesheetId) {
		TimesheetId = timesheetId;
	}
	public String getProjAllocationId() {
		return ProjAllocationId;
	}
	public void setProjAllocationId(String projAllocationId) {
		ProjAllocationId = projAllocationId;
	}
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	public String getProjCode() {
		return ProjCode;
	}
	public void setProjCode(String projCode) {
		ProjCode = projCode;
	}
	public String getProjMgr() {
		return ProjMgr;
	}
	public void setProjMgr(String projMgr) {
		ProjMgr = projMgr;
	}
	public String getCircleMarket() {
		return CircleMarket;
	}
	public void setCircleMarket(String circleMarket) {
		CircleMarket = circleMarket;
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
	public String getProjEnd() {
		return ProjEnd;
	}
	public void setProjEnd(String projEnd) {
		ProjEnd = projEnd;
	}
	public String getTSStart() {
		return TSStart;
	}
	public void setTSStart(String tSStart) {
		TSStart = tSStart;
	}
	public String getTSEnd() {
		return TSEnd;
	}
	public void setTSEnd(String tSEnd) {
		TSEnd = tSEnd;
	}
	public String getTSFlag() {
		return TSFlag;
	}
	public void setTSFlag(String tSFlag) {
		TSFlag = tSFlag;
	}
	public String getTSDate() {
		return TSDate;
	}
	public void setTSDate(String tSDate) {
		TSDate = tSDate;
	}
	public String getLeave() {
		return Leave;
	}
	public void setLeave(String leave) {
		Leave = leave;
	}
	public String getLeaveNOD() {
		return LeaveNOD;
	}
	public void setLeaveNOD(String leaveNOD) {
		LeaveNOD = leaveNOD;
	}
	public String getHoliday() {
		return Holiday;
	}
	public void setHoliday(String holiday) {
		Holiday = holiday;
	}
	public String getTSDayStatus() {
		return TSDayStatus;
	}
	public void setTSDayStatus(String tSDayStatus) {
		TSDayStatus = tSDayStatus;
	}
	public String getTSDId() {
		return TSDId;
	}
	public void setTSDId(String tSDId) {
		TSDId = tSDId;
	}
	public String getTimsheetDate() {
		return TimsheetDate;
	}
	public void setTimsheetDate(String timsheetDate) {
		TimsheetDate = timsheetDate;
	}
	public String getTimsheetWeekDay() {
		return TimsheetWeekDay;
	}
	public void setTimsheetWeekDay(String timsheetWeekDay) {
		TimsheetWeekDay = timsheetWeekDay;
	}
	public String getLHStatus() {
		return LHStatus;
	}
	public void setLHStatus(String lHStatus) {
		LHStatus = lHStatus;
	}
	public String getTSStatus() {
		return TSStatus;
	}
	public void setTSStatus(String tSStatus) {
		TSStatus = tSStatus;
	}
	public String getTSStatusDesc() {
		return TSStatusDesc;
	}
	public void setTSStatusDesc(String tSStatusDesc) {
		TSStatusDesc = tSStatusDesc;
	}
	public String getTSActivity() {
		return TSActivity;
	}
	public void setTSActivity(String tSActivity) {
		TSActivity = tSActivity;
	}
	public String getTSDescription() {
		return TSDescription;
	}
	public void setTSDescription(String tSDescription) {
		TSDescription = tSDescription;
	}
	public String getDA() {
		return DA;
	}
	public void setDA(String dA) {
		DA = dA;
	}
	public String getDAId() {
		return DAId;
	}
	public void setDAId(String dAId) {
		DAId = dAId;
	}
	public String getDAAmount() {
		return DAAmount;
	}
	public void setDAAmount(String dAAmount) {
		DAAmount = dAAmount;
	}
	public String getDACurType() {
		return DACurType;
	}
	public void setDACurType(String dACurType) {
		DACurType = dACurType;
	}
	public String getAssetStockId() {
		return AssetStockId;
	}
	public void setAssetStockId(String assetStockId) {
		AssetStockId = assetStockId;
	}
	public String getTSMailDetail() {
		return TSMailDetail;
	}
	public void setTSMailDetail(String tSMailDetail) {
		TSMailDetail = tSMailDetail;
	}
	public String getProjectDesc() {
		return ProjectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		ProjectDesc = projectDesc;
	}
	public String getTimesheetType() {
		return TimesheetType;
	}
	public void setTimesheetType(String timesheetType) {
		TimesheetType = timesheetType;
	}
	public String getMailDesc() {
		return MailDesc;
	}
	public void setMailDesc(String mailDesc) {
		MailDesc = mailDesc;
	}
	private String MailDesc;
}
