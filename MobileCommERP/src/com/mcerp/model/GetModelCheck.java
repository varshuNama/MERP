package com.mcerp.model;

import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GetModelCheck {

	private TextView LeaveFrom;
	private TextView LeaveTo;
	private TextView Project;
	private TextView Circle;
	private TextView LeaveType;
	private CheckBox checkbox;
	private RadioGroup radio;
	private TextView EmpName;
	private TextView LeaveReason;
	public GetModelCheck(TextView leaveFrom, TextView leaveTo,
			TextView project, TextView circle, TextView leaveType,
			TextView empName, TextView leaveReason, CheckBox checkbox,
			RadioGroup radio) {
		super();
		LeaveFrom = leaveFrom;
		LeaveTo = leaveTo;
		Project = project;
		Circle = circle;
		LeaveType = leaveType;
		EmpName = empName;
		LeaveReason = leaveReason;
		this.checkbox = checkbox;
		this.radio = radio;
	}

	public TextView getLeaveFrom() {
		return LeaveFrom;
	}

	public void setLeaveFrom(TextView leaveFrom) {
		LeaveFrom = leaveFrom;
	}

	public TextView getLeaveTo() {
		return LeaveTo;
	}

	public void setLeaveTo(TextView leaveTo) {
		LeaveTo = leaveTo;
	}

	public TextView getProject() {
		return Project;
	}

	public void setProject(TextView project) {
		Project = project;
	}

	public TextView getCircle() {
		return Circle;
	}

	public void setCircle(TextView circle) {
		Circle = circle;
	}

	public TextView getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(TextView leaveType) {
		LeaveType = leaveType;
	}

	public TextView getEmpName() {
		return EmpName;
	}

	public void setEmpName(TextView empName) {
		EmpName = empName;
	}

	public TextView getLeaveReason() {
		return LeaveReason;
	}

	public void setLeaveReason(TextView leaveReason) {
		LeaveReason = leaveReason;
	}

	public CheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(CheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public RadioGroup getRadio() {
		return radio;
	}

	public void setRadio(RadioGroup radio) {
		this.radio = radio;
	}

	
}
