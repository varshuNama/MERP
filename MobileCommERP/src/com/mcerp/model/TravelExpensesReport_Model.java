package com.mcerp.model;

import java.io.Serializable;

public class TravelExpensesReport_Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ProjName;
	private String Location;
	private String TravelDate;
	private String Unit;
	private String UnitCost;
	private String TransportMode;
	private String TransportModeDesc;
	private String ApproveUnit;
	private String ApproveUnitCost;
	private String Status;
	public String getProjName() {
		return ProjName;
	}
	public void setProjName(String projName) {
		ProjName = projName;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
	public String getApproveUnit() {
		return ApproveUnit;
	}
	public void setApproveUnit(String approveUnit) {
		ApproveUnit = approveUnit;
	}
	public String getApproveUnitCost() {
		return ApproveUnitCost;
	}
	public void setApproveUnitCost(String approveUnitCost) {
		ApproveUnitCost = approveUnitCost;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

}
