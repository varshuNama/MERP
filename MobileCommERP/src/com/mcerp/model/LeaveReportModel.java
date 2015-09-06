package com.mcerp.model;

import java.io.Serializable;

public class LeaveReportModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MonthDesc;
	private String YearMonth;
	private String  Month_Leave;
	private String Carry_Forward;
	private String Leave_In_Hand;
	private String Leave_Taken;
	private String Approval_pending;
	private String Forward;
	private String MonthStatus;
	public String getMonthDesc() {
		return MonthDesc;
	}
	public void setMonthDesc(String monthDesc) {
		MonthDesc = monthDesc;
	}
	public String getYearMonth() {
		return YearMonth;
	}
	public void setYearMonth(String yearMonth) {
		YearMonth = yearMonth;
	}
	public String getMonth_Leave() {
		return Month_Leave;
	}
	public void setMonth_Leave(String month_Leave) {
		Month_Leave = month_Leave;
	}
	public String getCarry_Forward() {
		return Carry_Forward;
	}
	public void setCarry_Forward(String carry_Forward) {
		Carry_Forward = carry_Forward;
	}
	public String getLeave_In_Hand() {
		return Leave_In_Hand;
	}
	public void setLeave_In_Hand(String leave_In_Hand) {
		Leave_In_Hand = leave_In_Hand;
	}
	public String getLeave_Taken() {
		return Leave_Taken;
	}
	public void setLeave_Taken(String leave_Taken) {
		Leave_Taken = leave_Taken;
	}
	public String getApproval_pending() {
		return Approval_pending;
	}
	public void setApproval_pending(String approval_pending) {
		Approval_pending = approval_pending;
	}
	public String getForward() {
		return Forward;
	}
	public void setForward(String forward) {
		Forward = forward;
	}
	public String getMonthStatus() {
		return MonthStatus;
	}
	public void setMonthStatus(String monthStatus) {
		MonthStatus = monthStatus;
	}
	

}
