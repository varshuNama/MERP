package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.livendtimesheet.LeaveCancelation_ProjMgrApprova;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.LeaveCancelation_ProjMgrApprova_Model;
import com.mcerp.util.AppPreferences;

public class AsyncTask_LeaveCancelation_ProjMgrApprova extends
		AsyncTask<String, String, String> {
	LeaveCancelation_ProjMgrApprova act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	AppPreferences prefs;
	int flag = 0;
	ArrayList<LeaveCancelation_ProjMgrApprova_Model> leavecancelation_projMgrapprova_model_list = new ArrayList<LeaveCancelation_ProjMgrApprova_Model>();
	String responseData;

	public AsyncTask_LeaveCancelation_ProjMgrApprova(
			LeaveCancelation_ProjMgrApprova con) {
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
		pDialog.setCancelable(false);
		pDialog.setCanceledOnTouchOutside(false);


	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.getLeaveCancelation_ProjMgrApprova(prefs.getUserID());
			//response = MethodSoap.getLeaveCancelation_ProjMgrApprova("386");
			
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");
			if (message.equals("success")) {
				JSONArray jsonarray = new JSONArray(responseData);

				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					LeaveCancelation_ProjMgrApprova_Model ModelData = new LeaveCancelation_ProjMgrApprova_Model();
					ModelData.setId(obj.getString("Id"));
					ModelData.setEmpName(obj.getString("EmpName"));
					ModelData.setEmail(obj.getString("Email"));
					ModelData.setProjName(obj.getString("ProjName"));
					ModelData.setLeaveDate(obj.getString("LeaveDate"));
					ModelData.setReqEmpId(obj.getString("EmpId"));
					ModelData.setLeaveDay(obj.getString("LeaveDay"));
					ModelData.setLeaveType(obj.getString("LeaveType"));
					ModelData.setCancelRemark(obj.getString("CancelReason"));
					ModelData.setLeaveDateMnthYr(obj.getString("LeaveDateMnthYr"));

					leavecancelation_projMgrapprova_model_list.add(ModelData);

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

				act.leavecancelation_projMgrapproval(act,
						leavecancelation_projMgrapprova_model_list);

				pDialog.dismiss();

			} else if (result.equals("fail")) {

				act.leavecancelation_projMgrapprova_fail(act, responseData);

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