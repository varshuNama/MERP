package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Accept_Training;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.TrainingModelData;

public class AsynctasksubmitAcceptTraining extends
		AsyncTask<String, String, String> {

	SweetAlertDialog pDialog, dialog;
	Accept_Training context;
	ConnectionDetector connection;
	int flag = 0;

	String myresult;

	ArrayList<TrainingModelData> accepttraining_model_list = new ArrayList<TrainingModelData>();

	ArrayList<String> arrayListTsId;
	ArrayList<String> arrayListRemarks;
	ArrayList<String> arrayListApprove;
	String empName;
	String userName;
	ArrayList<String> arrayListTraining;
	ArrayList<String> arrayListLocation;
	ArrayList<String> arrayListCustomer;
	ArrayList<String> arrayListTrainingStart;
	ArrayList<String> arrayListTrainingEnd;
	ArrayList<String> arrayListNoOfDays;
	ArrayList<String> arrayListTravelStart;
	ArrayList<String> arrayListTravelEnd;
	ArrayList<String> arrayListDA;
	ArrayList<String> arrayListEmailTo;

	public AsynctasksubmitAcceptTraining(Accept_Training con,
			ArrayList<String> arrayListTsId,
			ArrayList<String> arrayListRemarks,
			ArrayList<String> arrayListApprove, String empName,
			String userName, ArrayList<String> arrayListTraining,
			ArrayList<String> arrayListLocation,
			ArrayList<String> arrayListCustomer,
			ArrayList<String> arrayListTrainingStart,
			ArrayList<String> arrayListTrainingEnd,
			ArrayList<String> arrayListNoOfDays,
			ArrayList<String> arrayListTravelStart,
			ArrayList<String> arrayListTravelEnd,
			ArrayList<String> arrayListDA, ArrayList<String> arrayListEmailTo) {
		context = con;
		connection = new ConnectionDetector(con);

		this.arrayListTsId = arrayListTsId;
		this.arrayListRemarks = arrayListRemarks;
		this.arrayListApprove = arrayListApprove;
		this.empName = empName;
		this.userName = userName;
		this.arrayListTraining = arrayListTraining;

		this.arrayListLocation = arrayListLocation;
		this.arrayListCustomer = arrayListCustomer;
		this.arrayListTrainingStart = arrayListTrainingStart;
		this.arrayListTrainingEnd = arrayListTrainingEnd;
		this.arrayListNoOfDays = arrayListNoOfDays;
		this.arrayListTravelStart = arrayListTravelStart;
		this.arrayListTravelEnd = arrayListTravelEnd;
		this.arrayListDA = arrayListDA;
		this.arrayListEmailTo = arrayListEmailTo;

	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
		super.onPreExecute();
		pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.sendNewAcceptTrainingData(arrayListTsId,
					arrayListRemarks, arrayListApprove, empName, userName,
					arrayListTraining, arrayListLocation, arrayListCustomer,
					arrayListTrainingStart, arrayListTrainingEnd,
					arrayListNoOfDays, arrayListTravelStart,
					arrayListTravelEnd, arrayListDA, arrayListEmailTo);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			 myresult = jsonobj.getString("DataArr");

		} catch (Exception e) {
			e.printStackTrace();
			flag = 1;
			message = "";
			
		}

		return message;

	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {

				dialog = new SweetAlertDialog(context,
						SweetAlertDialog.SUCCESS_TYPE)
						.setTitleText("Successfully")
						.setContentText(myresult)
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(SweetAlertDialog sDialog) {
										Intent intent = new Intent(context,
												Accept_Training.class);
										context.startActivity(intent);
										context.finish();
										dialog.dismiss();
									}
								});
				dialog.show();

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Oops Data not found ")
						.setContentText(myresult).show();
				pDialog.dismiss();

			} else if (!connection.isConnectingToInternet()) {

				new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
				pDialog.dismiss();
			} else {

				pDialog.dismiss();
				if (flag == 1) {
					flag = 0;
					dialog = new SweetAlertDialog(context,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Server Connection Problem!")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {
											Intent intent = new Intent(context,
													NavigationActivity.class);
											context.startActivity(intent);
											((Activity) context).finish();

											sDialog.dismiss();
										}
									});
					dialog.show();

				} else {
					dialog = new SweetAlertDialog(context,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Does not get proper response.")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {
											Intent intent = new Intent(context,
													NavigationActivity.class);
											context.startActivity(intent);
											((Activity) context).finish();

											sDialog.dismiss();
										}
									});
					dialog.show();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
}