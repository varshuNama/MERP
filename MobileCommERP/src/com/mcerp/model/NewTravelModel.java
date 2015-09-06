package com.mcerp.model;

import java.io.Serializable;

public class NewTravelModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	String ProjAllocationId;
    private String ProjCode;
    private String ProjName;
    private String ProjMgrId;
    private String ProjMgrCode; 
    private String ProjMgrEmail;
    public String getProjAllocationId() {
		return ProjAllocationId;
	}
	public void setProjAllocationId(String projAllocationId) {
		ProjAllocationId = projAllocationId;
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
	public String getProjMgrId() {
		return ProjMgrId;
	}
	public void setProjMgrId(String projMgrId) {
		ProjMgrId = projMgrId;
	}
	public String getProjMgrCode() {
		return ProjMgrCode;
	}
	public void setProjMgrCode(String projMgrCode) {
		ProjMgrCode = projMgrCode;
	}
	public String getProjMgrEmail() {
		return ProjMgrEmail;
	}
	public void setProjMgrEmail(String projMgrEmail) {
		ProjMgrEmail = projMgrEmail;
	}
	public String getProjMgrName() {
		return ProjMgrName;
	}
	public void setProjMgrName(String projMgrName) {
		ProjMgrName = projMgrName;
	}
	private String ProjMgrName;

}
