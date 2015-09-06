package com.mcerp.model;

import java.io.Serializable;

public class Gts_View_Training_Report_Model implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TrainingName;
	private String CustomerName;
	private String TrainingDate;
	private String TrainingPlace;
	private String TravelEndDate;
	private String TravelStartDate;
	private String Status;
	private String Active;
	
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}
	public String getTrainingName() {
		return TrainingName;
	}
	public void setTrainingName(String trainingName) {
		TrainingName = trainingName;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getTrainingDate() {
		return TrainingDate;
	}
	public void setTrainingDate(String trainingDate) {
		TrainingDate = trainingDate;
	}
	public String getTrainingPlace() {
		return TrainingPlace;
	}
	public void setTrainingPlace(String trainingPlace) {
		TrainingPlace = trainingPlace;
	}
	public String getTravelEndDate() {
		return TravelEndDate;
	}
	public void setTravelEndDate(String travelEndDate) {
		TravelEndDate = travelEndDate;
	}
	public String getTravelStartDate() {
		return TravelStartDate;
	}
	public void setTravelStartDate(String travelStartDate) {
		TravelStartDate = travelStartDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	


}
