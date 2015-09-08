package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.LeaveCancealReq;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.LeaveCancealReqModelData;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class Asynctask_SubmitCancel_Leave_Req extends
		AsyncTask<String, String, String> {

	SweetAlertDialog pDialog, dialog;
	LeaveCancealReq context;
	ConnectionDetector connection;
	int flag = 0;

	String myresult;

	ArrayList<LeaveCancealReqModelData> leavecancel_model_list = new ArrayList<LeaveCancealReqModelData>();
	ArrayList<String> arrayLeaveID;
	ArrayList<String> arrayCancelRemark;

	
	String userName;
	String empname;
	String empId;
	ArrayList<String> strPmName;
	ArrayList<String> strPmEmail;
	ArrayList<String> arrayPmName;
	ArrayList<String> arrayPmEmail;
	ArrayList<String> arrayLeaveDate;
	ArrayList<String> arrayLeaveDay;
	ArrayList<String> arrayProjName;
	
		
		
	public Asynctask_SubmitCancel_Leave_Req(LeaveCancealReq con,
			ArrayList<String> arrayLeaveID,
			ArrayList<String> arrayCancelRemark, String userName,
			String empname,String empId, ArrayList<String> arrayPmName,
			ArrayList<String> arrayPmEmail, ArrayList<String> arrayLeaveDate,
			ArrayList<String> arrayLeaveDay,ArrayList<String> arrayProjName) {
		
		context = con;
		connection = new ConnectionDetector(con);

		this.arrayLeaveID = arrayLeaveID;
		this.arrayCancelRemark = arrayCancelRemark;
		
		this.empname = empname;
		this.empId = empId;
		this.userName = userName;
		this.arrayPmName = arrayPmName;

		this.arrayPmEmail = arrayPmEmail;
		this.arrayLeaveDate = arrayLeaveDate;
		this.arrayLeaveDay = arrayLeaveDay;
		this.arrayProjName = arrayProjName;
		
		
		
		
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
			response = MethodSoap.sendLeave_Canceal_Req(arrayLeaveID,
					arrayCancelRemark, userName, empname+"-"+empId,arrayPmName, arrayPmEmail,arrayLeaveDate,arrayLeaveDay,arrayProjName);
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
												LeaveCancealReq.class);
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