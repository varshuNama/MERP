package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.livendtimesheet.TimeSheetReport;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.ViewTimeSheetModel;
import com.mcerp.util.AppPreferences;

/* Webservice For Leave Report */

public class AsyncTaskViewDetailTimeSheet extends
		AsyncTask<String, String, String> {

	String responseData, Message = null, spingetvalue, getFlag, checkservice;
	SweetAlertDialog pDialog, dialog;
	AppPreferences prefs;
	ArrayList<ViewTimeSheetModel> reportdata;
	TimeSheetReport context;
	int flag = 0;
	ConnectionDetector connectionDetector;

	public AsyncTaskViewDetailTimeSheet(TimeSheetReport con, String spinvalue,
			String flag, String chk) {
		super();
		context = con;
		spingetvalue = spinvalue;
		getFlag = flag;
		checkservice = chk;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		prefs = AppPreferences.getInstance(context);
		connectionDetector = new ConnectionDetector(context);
		pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();
		pDialog.setCancelable(false);
		pDialog.setCanceledOnTouchOutside(false);

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		reportdata = new ArrayList<ViewTimeSheetModel>();

		try {
			response = MethodSoap.getViewTimeSheetDetail(prefs.getUserID(),
					spingetvalue, getFlag);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");

			if (message.equals("success")) {
				if (checkservice == "1") {
					JSONArray jsonarray = new JSONArray(responseData);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						ViewTimeSheetModel rep = new ViewTimeSheetModel();
						rep.setMonth(json.getString("Month"));
						rep.setProject(json.getString("Project"));
						rep.setSaved(json.getString("Saved"));
						rep.setPending(json.getString("Pending"));
						rep.setRejected(json.getString("Rejected"));
						rep.setApproved(json.getString("Approved"));
						rep.setId(json.getString("Id"));
						rep.setMonthYear(json.getString("MonthYear"));

						reportdata.add(rep);
					}
				} else {
					JSONArray jsonarray = new JSONArray(responseData);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						ViewTimeSheetModel rep = new ViewTimeSheetModel();
						rep.setMonth(json.getString("ProjName"));
						rep.setProject(json.getString("ProjMgrName"));
						rep.setSaved(json.getString("TSDate"));
						rep.setRejected(json.getString("LHStatus"));
						rep.setApproved(json.getString("ViewStatus"));

						reportdata.add(rep);
					}
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
				context.calladapter(reportdata, responseData, checkservice);
				pDialog.dismiss();

			} else if (result.equals("fail")) {
				context.calladapter(reportdata, responseData, checkservice);
				pDialog.dismiss();
			} else if (!connectionDetector.isConnectingToInternet()) {
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
			pDialog.dismiss();
		}

	}
}
