package com.mcerp.model;

import java.io.Serializable;

public class TransferAssetsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Asset_Id;
	private String Asset_Des;
	private String ProjCode;
	private String ProjName;
	private String ProjMgrCode;
	private String projMrgName;
	private String ProjMgrEmail;
	private String EmpName;
	private String KeyId;
	private String CircleID;
	private String CircleName;
	public String getCircleID() {
		return CircleID;
	}
	public void setCircleID(String circleID) {
		CircleID = circleID;
	}
	public String getCircleName() {
		return CircleName;
	}
	public void setCircleName(String circleName) {
		CircleName = circleName;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getKeyId() {
		return KeyId;
	}
	public void setKeyId(String keyId) {
		KeyId = keyId;
	}
	public String getProjCode() {
		return ProjCode;
	}
	public void setProjCode(String projCode) {
		ProjCode = projCode;
	}
	public String getProjName() {
		return ProjName;
	}
	public void setProjName(String projName) {
		ProjName = projName;
	}
	public String getProjMgrCode() {
		return ProjMgrCode;
	}
	public void setProjMgrCode(String projMgrCode) {
		ProjMgrCode = projMgrCode;
	}
	public String getProjMrgName() {
		return projMrgName;
	}
	public void setProjMrgName(String projMrgName) {
		this.projMrgName = projMrgName;
	}
	public String getProjMgrEmail() {
		return ProjMgrEmail;
	}
	public void setProjMgrEmail(String projMgrEmail) {
		ProjMgrEmail = projMgrEmail;
	}
	public String getAsset_Id() {
		return Asset_Id;
	}
	public void setAsset_Id(String asset_Id) {
		Asset_Id = asset_Id;
	}
	public String getAsset_Des() {
		return Asset_Des;
	}
	public void setAsset_Des(String asset_Des) {
		Asset_Des = asset_Des;
	}
	
	
}
