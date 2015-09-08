package com.mcerp.model;

import java.io.Serializable;

public class ProjectCostAfterEditSaveModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DetailId;
	private String ResourceId;
	private String ResourceName;
	private String SheetId;
	private String CostId;
	private String qantity = "0";
	private String unit_test = "0";
	private String tatalcost = "0";
	private boolean checkboxstatus=false;

	public boolean isCheckboxstatus() {
		return checkboxstatus;
	}

	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}

	public String getDetailId() {
		return DetailId;
	}

	public void setDetailId(String detailId) {
		DetailId = detailId;
	}

	public String getResourceId() {
		return ResourceId;
	}

	public void setResourceId(String resourceId) {
		ResourceId = resourceId;
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

	public String getCostId() {
		return CostId;
	}

	public void setCostId(String costId) {
		CostId = costId;
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

}
