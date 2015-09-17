package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Gts_View_Training_Report;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.Gts_View_Training_Report_Model;

public class AsyncTaskGts_View_Training_Report extends
		AsyncTask<String, String, String> {
	Gts_View_Training_Report context;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	String responsedata;
	int flag = 0;
	ArrayList<Gts_View_Training_Report_Model> list_to_complete = new ArrayList<Gts_View_Training_Report_Model>();
	String myresult, year, month, empid;

	public AsyncTaskGts_View_Training_Report(Gts_View_Training_Report con,
			String empid, String year, String month) {
		context = con;
		connection = new ConnectionDetector(con);
		this.year = year;
		this.month = month;

		this.empid = empid;
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
			response = MethodSoap.getGts_View_Training_Report_Data(empid, year
					+ month);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responsedata = jsonobj.getString("DataArr");
			if (message.equals("success")) {

				JSONArray jsonarray = new JSONArray(responsedata);

				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					Gts_View_Training_Report_Model ModelData = new Gts_View_Training_Report_Model();
					ModelData.setTrainingName(obj.getString("TrainingName"));
					ModelData.setCustomerName(obj.getString("CustomerName"));
					ModelData.setTrainingDate(obj.getString("TrainingDate"));
					ModelData.setTrainingPlace(obj.getString("TrainingPlace"));
					ModelData.setTravelEndDate(obj.getString("TravelEndDate"));
					ModelData.setTravelStartDate(obj
							.getString("TravelStartDate"));
					ModelData.setActive(obj.getString("Active"));

					list_to_complete.add(ModelData);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = 1;
			message = "";
			

		}

		return message;

	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {

				context.getlist_to_gts_view_training_report(context,
						list_to_complete);

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				context.getlist_to_gts_view_training_report_fail(context,responsedata);

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
			pDialog.dismiss();
		}

	}
}