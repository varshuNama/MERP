package com.mcerp.model;

import java.io.Serializable;

public class TrainingModelData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean checkboxstatus = false;
	private boolean radioApprove = true;
	public boolean isRadioApprove() {
		return radioApprove;
	}
	public void setRadioApprove(boolean radioApprove) {
		this.radioApprove = radioApprove;
	}
	public boolean isRadioReject() {
		return radioReject;
	}
	public void setRadioReject(boolean radioReject) {
		this.radioReject = radioReject;
	}

	private boolean radioReject = false;

	private String Id;
	private String Training;
	private String Location;
	private String Customer;
	private String TrainingStart;
	private String TrainingEnd;
	private String TravelStart;
	private String resquestemailid;

	private int rejectOrApprove;

	private String TravelEnd;
	private String NoOfDays;
	private String yes_no_status="9";
	private String Remarks=" ";
	private String accept_trianingedit="0";
	

	
	public String getAccept_trianingedit() {
		return accept_trianingedit;
	}
	public String getResquestemailid() {
		return resquestemailid;
	}
	public void setResquestemailid(String resquestemailid) {
		this.resquestemailid = resquestemailid;
	}
	public void setAccept_trianingedit(String accept_trianingedit) {
		this.accept_trianingedit = accept_trianingedit;
	}
	public String getYes_no_status() {
		return yes_no_status;
	}
	public void setYes_no_status(String yes_no_status) {
		this.yes_no_status = yes_no_status;
	}
	public boolean getCheckboxstatus() {
		return checkboxstatus;
	}
	public void setCheckboxstatus(boolean checkboxstatus) {
		this.checkboxstatus = checkboxstatus;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTraining() {
		return Training;
	}

	public void setTraining(String training) {
		Training = training;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getTrainingStart() {
		return TrainingStart;
	}

	public void setTrainingStart(String trainingStart) {
		TrainingStart = trainingStart;
	}

	public String getTrainingEnd() {
		return TrainingEnd;
	}

	public void setTrainingEnd(String trainingEnd) {
		TrainingEnd = trainingEnd;
	}

	public String getTravelStart() {
		return TravelStart;
	}

	public void setTravelStart(String travelStart) {
		TravelStart = travelStart;
	}

	public String getTravelEnd() {
		return TravelEnd;
	}

	public void setTravelEnd(String travelEnd) {
		TravelEnd = travelEnd;
	}

	public String getNoOfDays() {
		return NoOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		NoOfDays = noOfDays;
	}

	public String getDA() {
		return DA;
	}

	public void setDA(String dA) {
		DA = dA;
	}

	private String DA;

	public int getRejectOrApprove() {
		return rejectOrApprove;
	}

	public void setRejectOrApprove(int rejectOrApprove) {
		this.rejectOrApprove = rejectOrApprove;
	}

	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	

}
}