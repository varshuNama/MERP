package com.mcerp.model;

import java.io.Serializable;

public class ProjectedCostGetData implements Serializable {

	/**
	 * 
	 */
	private static  final long serialVersionUID = 1L;
	private String ProjectCode;
	private  String ProjectName;
	private  String Closed;
	public  String getProjectCode() {
		return ProjectCode;
	}
	public void setProjectCode(String projectCode) {
		ProjectCode = projectCode;
	}
	public  String getProjectName() {
		return ProjectName;
	}
	public  void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public  String getClosed() {
		return Closed;
	}
	public  void setClosed(String closed) {
		Closed = closed;
	}
	

}
