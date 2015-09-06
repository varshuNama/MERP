package com.mcerp.model;

import java.io.Serializable;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mcerp.main.R;

public class Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean checkBoxChecked=false;
	private int rejectOrApprove;
	private String Name;
	private String Project;
	private String Circle;
	private String Reason;
	private String LeaveTo;
	private String LeaveFrom;
	private String Type;
	private String Id_All;
	private String EmpId;
	private String EmpCode;
	private String EmpEmail;
	private String NOD;
	private int selected=1;
	private ArrayAdapter<CharSequence> adapter;

	public Model(Context context) {

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


	public Model(boolean checked, String name, String project, String circle,
			String reason, String leaveTo, String leaveFrom, String type) {
		super();
		this.rejectOrApprove = 1;
		this.checkBoxChecked = checked;
		Name = name;
		Project = project;
		Circle = circle;
		Reason = reason;
		LeaveTo = leaveTo;
		LeaveFrom = leaveFrom;
		Type = type;
	}



	public Model() {

	}

	public String getId_All() {
		return Id_All;
	}



	public void setId_All(String id_All) {
		Id_All = id_All;
	}



	public String getEmpId() {
		return EmpId;
	}



	public void setEmpId(String empId) {
		EmpId = empId;
	}



	public String getEmpCode() {
		return EmpCode;
	}



	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}



	public String getEmpEmail() {
		return EmpEmail;
	}



	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}



	public String getNOD() {
		return NOD;
	}



	public void setNOD(String nOD) {
		NOD = nOD;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public boolean isChecked() {
		return checkBoxChecked;
	}
	public void setChecked(boolean checked) {
		this.checkBoxChecked = checked;
	}
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getCircle() {
		return Circle;
	}
	public void setCircle(String circle) {
		Circle = circle;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getLeaveTo() {
		return LeaveTo;
	}
	public void setLeaveTo(String leaveTo) {
		LeaveTo = leaveTo;
	}
	public String getLeaveFrom() {
		return LeaveFrom;
	}
	public void setLeaveFrom(String leaveFrom) {
		LeaveFrom = leaveFrom;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public int getRejectOrApprove() {
		return rejectOrApprove;
	}

	public void setRejectOrApprove(int rejectOrApprove) {
		this.rejectOrApprove = rejectOrApprove;
	}


}
