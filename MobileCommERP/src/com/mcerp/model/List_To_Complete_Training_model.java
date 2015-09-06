package com.mcerp.model;

import java.io.Serializable;

public class List_To_Complete_Training_model implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Id;
	private String Training;
	private String Location;
	private String  TrainingStart;
	private String TrainingEnd;
	private String Customer;
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
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}

}
