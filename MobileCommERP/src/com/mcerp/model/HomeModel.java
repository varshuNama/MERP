package com.mcerp.model;

import java.io.Serializable;

public class HomeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String UserId;
	private String EmpCode;
	private String Email;
	private String EmpName;
	private String ReportingManager;
	
	public String getReportingManager() {
		return ReportingManager;
	}
	public void setReportingManager(String reportingManager) {
		ReportingManager = reportingManager;
	}
	private String Project;
	private String ProjMgr;
	private String Location;
	private String ProjStart;
	private String ProjEnd;
	
	private String TotalAssets;
	private String FromAdminPending;
	private String FromEmpPending;
	private String ToEmpPending;
	
	
	private String ToAdminPending;
	private String AssetName;
	private String CategoryName;
	private String Asset_SrNo;
	private String Asset_TagNo;
	private String AssetIssueDate;
	private String AcceptStatus;
	private String TimesheetReq;
	
	private String TransferToAdmin;
	private String AdminTransferDate;
	private String TransferToEmp;
	private String TransferTo;
	private String TransferDate;
	private String LeaveReq;
	private String TrainingReq;
	private String Training;
	
	private String TrainerFlag;
	private String TopMgtFlag;
	private String MonthName;
	private String MonthYear;
	
	
	public String getTrainerFlag() {
		return TrainerFlag;
	}
	public void setTrainerFlag(String trainerFlag) {
		TrainerFlag = trainerFlag;
	}
	public String getTopMgtFlag() {
		return TopMgtFlag;
	}
	public void setTopMgtFlag(String topMgtFlag) {
		TopMgtFlag = topMgtFlag;
	}
	public String getMonthName() {
		return MonthName;
	}
	public void setMonthName(String monthName) {
		MonthName = monthName;
	}
	public String getMonthYear() {
		return MonthYear;
	}
	public void setMonthYear(String monthYear) {
		MonthYear = monthYear;
	}
	public String getToEmpPending() {
		return ToEmpPending;
	}
	public void setToEmpPending(String toEmpPending) {
		ToEmpPending = toEmpPending;
	}
	
	public String getTimesheetReq() {
		return TimesheetReq;
	}
	public void setTimesheetReq(String timesheetReq) {
		TimesheetReq = timesheetReq;
	}

	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getEmpCode() {
		return EmpCode;
	}
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getProjMgr() {
		return ProjMgr;
	}
	public void setProjMgr(String projMgr) {
		ProjMgr = projMgr;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
	public String getTotalAssets() {
		return TotalAssets;
	}
	public void setTotalAssets(String totalAssets) {
		TotalAssets = totalAssets;
	}
	public String getFromAdminPending() {
		return FromAdminPending;
	}
	public void setFromAdminPending(String fromAdminPending) {
		FromAdminPending = fromAdminPending;
	}
	public String getFromEmpPending() {
		return FromEmpPending;
	}
	public void setFromEmpPending(String fromEmpPending) {
		FromEmpPending = fromEmpPending;
	}
	public String getToAdminPending() {
		return ToAdminPending;
	}
	public void setToAdminPending(String toAdminPending) {
		ToAdminPending = toAdminPending;
	}
	public String getAssetName() {
		return AssetName;
	}
	public void setAssetName(String assetName) {
		AssetName = assetName;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getAsset_SrNo() {
		return Asset_SrNo;
	}
	public void setAsset_SrNo(String asset_SrNo) {
		Asset_SrNo = asset_SrNo;
	}
	public String getAsset_TagNo() {
		return Asset_TagNo;
	}
	public void setAsset_TagNo(String asset_TagNo) {
		Asset_TagNo = asset_TagNo;
	}
	public String getAssetIssueDate() {
		return AssetIssueDate;
	}
	public void setAssetIssueDate(String assetIssueDate) {
		AssetIssueDate = assetIssueDate;
	}
	public String getAcceptStatus() {
		return AcceptStatus;
	}
	public void setAcceptStatus(String acceptStatus) {
		AcceptStatus = acceptStatus;
	}
	public String getTransferToAdmin() {
		return TransferToAdmin;
	}
	public void setTransferToAdmin(String transferToAdmin) {
		TransferToAdmin = transferToAdmin;
	}
	public String getAdminTransferDate() {
		return AdminTransferDate;
	}
	public void setAdminTransferDate(String adminTransferDate) {
		AdminTransferDate = adminTransferDate;
	}
	public String getTransferToEmp() {
		return TransferToEmp;
	}
	public void setTransferToEmp(String transferToEmp) {
		TransferToEmp = transferToEmp;
	}
	public String getTransferTo() {
		return TransferTo;
	}
	public void setTransferTo(String transferTo) {
		TransferTo = transferTo;
	}
	public String getTransferDate() {
		return TransferDate;
	}
	public void setTransferDate(String transferDate) {
		TransferDate = transferDate;
	}
	public String getLeaveReq() {
		return LeaveReq;
	}
	public void setLeaveReq(String leaveReq) {
		LeaveReq = leaveReq;
	}
	public String getTrainingReq() {
		return TrainingReq;
	}
	public void setTrainingReq(String trainingReq) {
		TrainingReq = trainingReq;
	}
	public String getTraining() {
		return Training;
	}
	public void setTraining(String training) {
		Training = training;
	}
	
	
	

}
