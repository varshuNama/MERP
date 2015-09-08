package com.mcerp.model;

import java.io.Serializable;

public class ProjectCostGetSheetModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ResourceCode;
	private String ResourceName;
	private String SheetId;
	private String ExistingCost;
	private String qantity="0";
	private String unit_test="0";
	private String tatalcost="0";
	private boolean checkboxstatus=false;
	public boolean isCheckboxstatus() {
		return checkboxstatus;
	}
	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}
	public String getQantity() {
		return qantity;
	}
	public void setQantity(String qantity) {
		this.qantity = qantity;
	}
	public String getUnit_test() {
		return unit_test;
	}
	public void setUnit_test(String unit_test) {
		this.unit_test = unit_test;
	}
	public String getTatalcost() {
		return tatalcost;
	}
	public void setTatalcost(String tatalcost) {
		this.tatalcost = tatalcost;
	}
	public String getResourceCode() {
		return ResourceCode;
	}
	public void setResourceCode(String resourceCode) {
		ResourceCode = resourceCode;
	}
	public String getResourceName() {
		return ResourceName;
	}
	public void setResourceName(String resourceName) {
		ResourceName = resourceName;
	}
	public String getSheetId() {
		return SheetId;
	}
	public void setSheetId(String sheetId) {
		SheetId = sheetId;
	}
	public String getExistingCost() {
		return ExistingCost;
	}
	public void setExistingCost(String existingCost) {
		ExistingCost = existingCost;
	}
	

}
