package com.mcerp.model;

import java.io.Serializable;

public class InventoryModel implements Serializable{

	/**
	 * 
	 */



	private static final long serialVersionUID = 1L;
	private String AssetStockId;
	private boolean checkbox_status=false;
	private String assert_name;
	private String assert_categoryname;
	private String assert_sr_no;
	private String assert_tag_no;
	private String assert_barcode;
	private String AssetIssueDate;
	private String AcceptStatus;
	private String remarks;

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean getCheckbox_status() {
		return checkbox_status;
	}
	public void setCheckbox_status(boolean checkbox_status) {
		this.checkbox_status = checkbox_status;
	}

	public String getAssert_sr_no() {
		return assert_sr_no;
	}
	public void setAssert_sr_no(String assert_sr_no) {
		this.assert_sr_no = assert_sr_no;
	}
	public String getAssert_tag_no() {
		return assert_tag_no;
	}
	public String getAssetStockId() {
		return AssetStockId;
	}
	public void setAssetStockId(String assetStockId) {
		AssetStockId = assetStockId;
	}
	public String getAssert_name() {
		return assert_name;
	}
	public void setAssert_name(String assert_name) {
		this.assert_name = assert_name;
	}
	public String getAssert_categoryname() {
		return assert_categoryname;
	}
	public void setAssert_categoryname(String assert_categoryname) {
		this.assert_categoryname = assert_categoryname;
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
	public void setAssert_tag_no(String assert_tag_no) {
		this.assert_tag_no = assert_tag_no;
	}
	public String getAssert_barcode() {
		return assert_barcode;
	}
	public void setAssert_barcode(String assert_barcode) {
		this.assert_barcode = assert_barcode;
	}


}
