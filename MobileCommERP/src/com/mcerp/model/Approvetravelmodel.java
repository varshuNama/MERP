package com.mcerp.model;

import java.io.Serializable;

public class Approvetravelmodel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Email;
	private String EmpCode;
	private String EmpId;
	private String EmpName;
	private String TravelAmount;
	private String TravelKm;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmpCode() {
		return EmpCode;
	}
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
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
	public String getTravelAmount() {
		return TravelAmount;
	}
	public void setTravelAmount(String travelAmount) {
		TravelAmount = travelAmount;
	}
	public String getTravelKm() {
		return TravelKm;
	}
	public void setTravelKm(String travelKm) {
		TravelKm = travelKm;
	}
	
	
	

}
