package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Accept_Training;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.TrainingModelData;
import com.mcerp.util.AppPreferences;

public class AcceptTrainingAsktask extends AsyncTask<String, String, String> {
	Accept_Training act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	AppPreferences prefs;
	int flag=0;
	ArrayList<TrainingModelData> accepttraing_model_list = new ArrayList<TrainingModelData>();
	String responseData;

	public AcceptTrainingAsktask(Accept_Training con) {
		prefs = AppPreferences.getInstance(con);

		act = con;
		connection = new ConnectionDetector(con);

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new SweetAlertDialog(act, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.getAcceptTrainingdetails(prefs.getUserID());
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData= jsonobj.getString("DataArr");
			if(message.equals("success")){
			JSONArray jsonarray = new JSONArray(responseData);
			
			
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject obj = jsonarray.getJSONObject(i);
				TrainingModelData ModelData = new TrainingModelData();
				ModelData.setId(obj.getString("Id"));
				ModelData.setTraining(obj.getString("Training"));
				ModelData.setLocation(obj.getString("Location"));
				ModelData.setCustomer(obj.getString("Customer"));
				ModelData.setTrainingStart(obj.getString("TrainingStart"));
				ModelData.setTrainingEnd(obj.getString("TrainingEnd"));
				ModelData.setTravelStart(obj.getString("TravelStart"));
				ModelData.setTravelEnd(obj.getString("TravelEnd"));
				ModelData.setNoOfDays(obj.getString("NoOfDays"));
				ModelData.setDA(obj.getString("DA"));
				ModelData.setResquestemailid(obj.getString("EmailTo"));
				accepttraing_model_list.add(ModelData);

			}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag=1;
			message="";
			
		}

		return message;

	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {

				act.getaccept_traing_list_for_approvel(act,
						accepttraing_model_list);

				pDialog.dismiss();

			} else if (result.equals("fail")) {

				act.getaccept_traing_list_for_fail(act,responseData);

				pDialog.dismiss();

			} else if (!connection.isConnectingToInternet()) {

				new SweetAlertDialog(act, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
				pDialog.dismiss();
			} else {

				pDialog.dismiss();
				if (flag == 1) {
					flag = 0;

					dialog = new SweetAlertDialog(act,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Server Connection Problem!")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {
											Intent intent = new Intent(act,
													NavigationActivity.class);
											act.startActivity(intent);
											((Activity) act).finish();

											sDialog.dismiss();
										}
									});
					dialog.show();
				}else{
					dialog = new SweetAlertDialog(act,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Does not get proper response.")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {
											Intent intent = new Intent(act,
													NavigationActivity.class);
											act.startActivity(intent);
											((Activity) act).finish();

											sDialog.dismiss();
										}
									});
					dialog.show();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			pDialog.dismiss();
		}

	}
}