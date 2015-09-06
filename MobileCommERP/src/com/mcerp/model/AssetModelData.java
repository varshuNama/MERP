package com.mcerp.model;

import java.io.Serializable;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mcerp.main.R;

public class AssetModelData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5630586147036407473L;
	private boolean checkBoxChecked = false;
	private String AssetType;
	private String CategoryName;
	private String SerialName;
	private String TagNumber;
	private String TransferTo;
	private String TransferDate;
	private String Category;
	private String SerialNumber;
	private String IssuedDate;

	private String AssetId;
	private String EmpCode;
	private String AssetDate;
	private String TransferFrom;
	private String TrnsfrType;
	private String TotalAsset;
	private String FromEmpCode;
	private String FromEmpId;

	private String Id_D;
	private String DetailId_D;
	private String AssetName_D;
	private String CategoryName_D;
	private String ProjectCode_D;
	private String ProjName_D;
	private String CircleId_D;
	private String circleName_D;
	private String Projmgrcode_D;
	private String ProjMgrName_D;
	private String ProjMgrEmail_D;
	private String IssuedBy_D;
	private String IssuedByEmail_D;
	private String TransferFrom_D;
	private String AssetSrNo_D;
	private String AssetTagNo_D;
	private String AssetDate_D;
	private String AssetIssueKey_D;
	private String Remarks;
	private String ApproveReject;
	private String PendingTransferTo;

	public String getPendingTransferTo() {
		return PendingTransferTo;
	}

	public void setPendingTransferTo(String pendingTransferTo) {
		PendingTransferTo = pendingTransferTo;
	}

	private int selected;
	private ArrayAdapter<CharSequence> adapter;

	public AssetModelData(Context context) {

		adapter = ArrayAdapter.createFromResource(context,
				R.array.acceptreject, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	}

	public ArrayAdapter<CharSequence> getAdapter() {
		return adapter;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getApproveReject() {
		return ApproveReject;
	}

	public void setApproveReject(String approveReject) {
		ApproveReject = approveReject;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getFromEmpCode() {
		return FromEmpCode;
	}

	public void setFromEmpCode(String fromEmpCode) {
		FromEmpCode = fromEmpCode;
	}

	public String getFromEmpId() {
		return FromEmpId;
	}

	public void setFromEmpId(String fromEmpId) {
		FromEmpId = fromEmpId;
	}

	public String getAssetId() {
		return AssetId;
	}

	public void setAssetId(String assetId) {
		AssetId = assetId;
	}

	public String getEmpCode() {
		return EmpCode;
	}

	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}

	public String getAssetDate() {
		return AssetDate;
	}

	public void setAssetDate(String assetDate) {
		AssetDate = assetDate;
	}

	public String getTransferFrom() {
		return TransferFrom;
	}

	public void setTransferFrom(String transferFrom) {
		TransferFrom = transferFrom;
	}

	public String getTrnsfrType() {
		return TrnsfrType;
	}

	public void setTrnsfrType(String trnsfrType) {
		TrnsfrType = trnsfrType;
	}

	public String getTotalAsset() {
		return TotalAsset;
	}

	public void setTotalAsset(String totalAsset) {
		TotalAsset = totalAsset;
	}

	public String getAssetType() {
		return AssetType;
	}

	public void setAssetType(String assetType) {
		AssetType = assetType;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public String getSerialName() {
		return SerialName;
	}

	public void setSerialName(String serialName) {
		SerialName = serialName;
	}

	public String getTagNumber() {
		return TagNumber;
	}

	public void setTagNumber(String tagNumber) {
		TagNumber = tagNumber;
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

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public String getIssuedDate() {
		return IssuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		IssuedDate = issuedDate;
	}

	public boolean isCheckBoxChecked() {
		return checkBoxChecked;
	}

	public void setCheckBoxChecked(boolean checkBoxChecked) {
		this.checkBoxChecked = checkBoxChecked;
	}

	public String getId_D() {
		return Id_D;
	}

	public void setId_D(String id_D) {
		Id_D = id_D;
	}

	public String getDetailId_D() {
		return DetailId_D;
	}

	public void setDetailId_D(String detailId_D) {
		DetailId_D = detailId_D;
	}

	public String getAssetName_D() {
		return AssetName_D;
	}

	public void setAssetName_D(String assetName_D) {
		AssetName_D = assetName_D;
	}

	public String getCategoryName_D() {
		return CategoryName_D;
	}

	public void setCategoryName_D(String categoryName_D) {
		CategoryName_D = categoryName_D;
	}

	public String getProjectCode_D() {
		return ProjectCode_D;
	}

	public void setProjectCode_D(String projectCode_D) {
		ProjectCode_D = projectCode_D;
	}

	public String getProjName_D() {
		return ProjName_D;
	}

	public void setProjName_D(String projName_D) {
		ProjName_D = projName_D;
	}

	public String getCircleId_D() {
		return CircleId_D;
	}

	public void setCircleId_D(String circleId_D) {
		CircleId_D = circleId_D;
	}

	public String getCircleName_D() {
		return circleName_D;
	}

	public void setCircleName_D(String circleName_D) {
		this.circleName_D = circleName_D;
	}

	public String getProjmgrcode_D() {
		return Projmgrcode_D;
	}

	public void setProjmgrcode_D(String projmgrcode_D) {
		Projmgrcode_D = projmgrcode_D;
	}

	public String getProjMgrName_D() {
		return ProjMgrName_D;
	}

	public void setProjMgrName_D(String projMgrName_D) {
		ProjMgrName_D = projMgrName_D;
	}

	public String getProjMgrEmail_D() {
		return ProjMgrEmail_D;
	}

	public void setProjMgrEmail_D(String projMgrEmail_D) {
		ProjMgrEmail_D = projMgrEmail_D;
	}

	public String getIssuedBy_D() {
		return IssuedBy_D;
	}

	public void setIssuedBy_D(String issuedBy_D) {
		IssuedBy_D = issuedBy_D;
	}

	public String getIssuedByEmail_D() {
		return IssuedByEmail_D;
	}

	public void setIssuedByEmail_D(String issuedByEmail_D) {
		IssuedByEmail_D = issuedByEmail_D;
	}

	public String getTransferFrom_D() {
		return TransferFrom_D;
	}

	public void setTransferFrom_D(String transferFrom_D) {
		TransferFrom_D = transferFrom_D;
	}

	public String getAssetSrNo_D() {
		return AssetSrNo_D;
	}

	public void setAssetSrNo_D(String assetSrNo_D) {
		AssetSrNo_D = assetSrNo_D;
	}

	public String getAssetTagNo_D() {
		return AssetTagNo_D;
	}

	public void setAssetTagNo_D(String assetTagNo_D) {
		AssetTagNo_D = assetTagNo_D;
	}

	public String getAssetDate_D() {
		return AssetDate_D;
	}

	public void setAssetDate_D(String assetDate_D) {
		AssetDate_D = assetDate_D;
	}

	public String getAssetIssueKey_D() {
		return AssetIssueKey_D;
	}

	public void setAssetIssueKey_D(String assetIssueKey_D) {
		AssetIssueKey_D = assetIssueKey_D;
	}

}
