package com.mcerp.model;

import java.io.Serializable;

import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Approvetraveldetailsmodel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ApproveUnitCost;
	private String Id;
	private String Location;
	private String ProjName;
	private String TransportMode;
	private String TransportModeDesc;
	private String TravelDate;
	private String Unit;
	private String UnitCost;
	private String Remarks=" ";
	private String pm_approvecast="0";
	private String yes_no_status="9";
	private boolean checkboxstatus=false;
	private boolean radioFirst=true;
	private boolean radiSecond=false;

	public boolean getCheckboxstatus() {
		return checkboxstatus;
	}
	public boolean getRadioFirst() {
		return radioFirst;
	}
	public void setRadioFirst(boolean radioFirst) {
		this.radioFirst = radioFirst;
	}
	public boolean getRadiSecond() {
		return radiSecond;
	}
	public void setRadiSecond(boolean radiSecond) {
		this.radiSecond = radiSecond;
	}
	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}
	public String getYes_no_status() {
		return yes_no_status;
	}
	public void setYes_no_status(String yes_no_status) {
		this.yes_no_status = yes_no_status;
	}
	public String getPm_approvecast() {
		return pm_approvecast;
	}
	public void setPm_approvecast(String pm_approvecast) {
		this.pm_approvecast = pm_approvecast;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getApproveUnitCost() {
		return ApproveUnitCost;
	}
	public void setApproveUnitCost(String approveUnitCost) {
		ApproveUnitCost = approveUnitCost;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getProjName() {
		return ProjName;
	}
	public void setProjName(String projName) {
		ProjName = projName;
	}
	public String getTransportMode() {
		return TransportMode;
	}
	public void setTransportMode(String transportMode) {
		TransportMode = transportMode;
	}
	public String getTransportModeDesc() {
		return TransportModeDesc;
	}
	public void setTransportModeDesc(String transportModeDesc) {
		TransportModeDesc = transportModeDesc;
	}
	public String getTravelDate() {
		return TravelDate;
	}
	public void setTravelDate(String travelDate) {
		TravelDate = travelDate;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getUnitCost() {
		return UnitCost;
	}
	public void setUnitCost(String unitCost) {
		UnitCost = unitCost;
	}

}
