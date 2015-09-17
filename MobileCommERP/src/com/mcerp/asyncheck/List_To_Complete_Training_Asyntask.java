package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Complete_Training;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.List_To_Complete_Training_model;
import com.mcerp.model.TimesheetSearchModel;
import com.mcerp.util.AppPreferences;

public class List_To_Complete_Training_Asyntask extends
AsyncTask<String, String, String> {
	Complete_Training context;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	ArrayList<List_To_Complete_Training_model> list_to_complete = new ArrayList<List_To_Complete_Training_model>();
	String myresult, empId;
	AppPreferences prefs;
	int flag=0;

	public List_To_Complete_Training_Asyntask(Complete_Training con) {

		context = con;
		connection = new ConnectionDetector(con);
		prefs = AppPreferences.getInstance(con);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
		.setTitleText("Loading");
		pDialog.show();
		pDialog.setCancelable(false);
		pDialog.setCanceledOnTouchOutside(false);


	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {

			response = MethodSoap.getList_to_complete_training(prefs
					.getUserID());

			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			myresult = jsonobj.getString("DataArr");
			if (message.equals("success")) {

				JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					List_To_Complete_Training_model ModelData = new List_To_Complete_Training_model();
					ModelData.setId(obj.getString("Id"));
					ModelData.setTraining(obj.getString("Training"));
					ModelData.setLocation(obj.getString("Location"));
					ModelData.setTrainingStart(obj.getString("TrainingStart"));
					ModelData.setTrainingEnd(obj.getString("TrainingEnd"));
					ModelData.setCustomer(obj.getString("Customer"));
					list_to_complete.add(ModelData);

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

				context.getlist_to_complete_training(context, list_to_complete);

				pDialog.dismiss();

			} else if (result.equals("fail")) {

				pDialog.dismiss();
				context.getlist_to_complete_training_fail(context, myresult);
			} else if (!connection.isConnectingToInternet()) {

				new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
				.setTitleText("Oops...")
				.setContentText("Internet Connection not available!")
				.show();
				pDialog.dismiss();
			} else {

				pDialog.dismiss();
				if (flag == 1) {
					flag=0;
					dialog = new SweetAlertDialog(context,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Server Connection Problem.")
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
							.setContentText("Server Connection Problem.")
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
			pDialog.dismiss();
		}

	}
}