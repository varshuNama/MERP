package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Accept_Training;
import com.mcerp.main.LeaveCancealReq;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.LeaveCancealReqModelData;
import com.mcerp.model.TrainingModelData;
import com.mcerp.util.AppPreferences;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class AsynctaskLeaveCancelReq extends AsyncTask<String, String, String> {

	LeaveCancealReq act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	AppPreferences prefs;
	int flag = 0;
	String responseData, yyyy, mm;
	ArrayList<LeaveCancealReqModelData> leavecancel_model_list = new ArrayList<LeaveCancealReqModelData>();

	public AsynctaskLeaveCancelReq(LeaveCancealReq con) {
		prefs = AppPreferences.getInstance(con);

		act = con;
		connection = new ConnectionDetector(con);

	}

	@Override
	protected void onPreExecute() {
		pDialog = new SweetAlertDialog(act, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			
			 response = MethodSoap.getLeaveCancelReq(prefs.getUserID());
			

			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");
			if (message.equals("success")) {
				JSONArray jsonarray = new JSONArray(responseData);

				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					LeaveCancealReqModelData ModelData = new LeaveCancealReqModelData();
					ModelData.setId(obj.getString("Id"));
					ModelData.setLeaveDate(obj.getString("LeaveDate"));
					ModelData.setLeaveDay(obj.getString("LeaveDay"));
					ModelData.setLeaveType(obj.getString("LeaveType"));
					ModelData.setProjMgr(obj.getString("ProjMgr"));
					ModelData.setProjMgrEmail(obj.getString("ProjMgrEmail"));
					ModelData.setProjMgrName(obj.getString("ProjMgrName"));
					ModelData.setProjname(obj.getString("Projname"));

					leavecancel_model_list.add(ModelData);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = 1;
			message = "";

		}

		return message;

	}

	@Override
	protected void onPostExecute(String result) {

		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {

				act.getleavecancel_list(act, leavecancel_model_list);

				pDialog.dismiss();

			} else if (result.equals("fail")) {

				act.getleavecancel_listfor_fail(act, responseData);

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
				} else {
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