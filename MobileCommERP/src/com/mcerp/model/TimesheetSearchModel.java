package com.mcerp.model;

import java.io.Serializable;

public class TimesheetSearchModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean checkBoxChecked=false;
	private String Date;
	private String Day;
	private String Activity;
	private String Description;
	private String Status;
	private String TSId;
	private String TSDId;
	private String EmpName;
	private String EmpEmail;
	private String ProjMgrName;
	private String ProjMgrEmail;
	private String ProjDesc;
	private String AssetDesc;
	private String LHStatus;
	private String HavingAsset;
	private String TrainingId;
	private String TimesheetType;
	private String DA;
	
	
	public TimesheetSearchModel() {
		
	}
	
	
	public TimesheetSearchModel(boolean checkBoxChecked, String date,
			String day, String activity, String description, String status) {
		super();
		this.checkBoxChecked = checkBoxChecked;
		Date = date;
		Day = day;
		Activity = activity;
		Description = description;
		Status = status;
	}
	public String getTSId() {
		return TSId;
	}


	public void setTSId(String tSId) {
		TSId = tSId;
	}
	public String getTSDId() {
		return TSDId;
	}


	public void setTSDId(String tSDId) {
		TSDId = tSDId;
	}


	public String getEmpName() {
		return EmpName;
	}


	public void setEmpName(String empName) {
		EmpName = empName;
	}


	public String getEmpEmail() {
		return EmpEmail;
	}


	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}


	public String getProjMgrName() {
		return ProjMgrName;
	}


	public void setProjMgrName(String projMgrName) {
		ProjMgrName = projMgrName;
	}


	public String getProjMgrEmail() {
		return ProjMgrEmail;
	}


	public void setProjMgrEmail(String projMgrEmail) {
		ProjMgrEmail = projMgrEmail;
	}


	public String getProjDesc() {
		return ProjDesc;
	}


	public void setProjDesc(String projDesc) {
		ProjDesc = projDesc;
	}


	public String getAssetDesc() {
		return AssetDesc;
	}


	public void setAssetDesc(String assetDesc) {
		AssetDesc = assetDesc;
	}


	public String getLHStatus() {
		return LHStatus;
	}


	public void setLHStatus(String lHStatus) {
		LHStatus = lHStatus;
	}


	public String getHavingAsset() {
		return HavingAsset;
	}


	public void setHavingAsset(String havingAsset) {
		HavingAsset = havingAsset;
	}


	public String getTrainingId() {
		return TrainingId;
	}


	public void setTrainingId(String trainingId) {
		TrainingId = trainingId;
	}


	public String getTimesheetType() {
		return TimesheetType;
	}


	public void setTimesheetType(String timesheetType) {
		TimesheetType = timesheetType;
	}


	public String getDA() {
		return DA;
	}


	public void setDA(String dA) {
		DA = dA;
	}
	
	public boolean isCheckBoxChecked() {
		return checkBoxChecked;
	}
	public void setCheckBoxChecked(boolean checkBoxChecked) {
		this.checkBoxChecked = checkBoxChecked;
	}
	public String getDate() {
		return Date;
	}
	
	public void setDate(String date) {
		Date = date;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public boolean isChecked() {
		return checkBoxChecked;
	}
	public void setChecked(boolean checked) {
		this.checkBoxChecked = checked;
	}

}
