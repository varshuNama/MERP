package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.livendtimesheet.LeaveCancelation_ProjMgrApprova;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.LeaveCancelation_ProjMgrApprova_Model;

public class AsynctasksubmitLeaveCancelation_ProjMgrApprova extends
		AsyncTask<String, String, String> {

	SweetAlertDialog pDialog, dialog;
	LeaveCancelation_ProjMgrApprova context;
	ConnectionDetector connection;
	int flag = 0;

	String myresult;

	ArrayList<LeaveCancelation_ProjMgrApprova_Model> leavecancelation_projMgrapprova_model_list = new ArrayList<LeaveCancelation_ProjMgrApprova_Model>();

	String userID;
	String empName;
	String userName;
	ArrayList<String> arrayLeaveId;
	ArrayList<String> arrayApprovalFlag;
	ArrayList<String> arrayApproveReason;
	ArrayList<String> arrayReqEmpId;
	ArrayList<String> arrayReqEmpName;
	ArrayList<String> arrayReqEmpEmail;
	ArrayList<String> arrayLeaveDate;
	ArrayList<String> arrayLeaveDay;
	ArrayList<String> arrayProjectName;
	ArrayList<String> arrayLeaveMonthYear;

	public AsynctasksubmitLeaveCancelation_ProjMgrApprova(
			LeaveCancelation_ProjMgrApprova con, String userID,
			String empName,
			String userName, ArrayList<String> arrayLeaveId,
			ArrayList<String> arrayApprovalFlag,
			ArrayList<String> arrayApproveReason,
			ArrayList<String> arrayReqEmpId,
			ArrayList<String> arrayReqEmpName,
			ArrayList<String> arrayReqEmpEmail,
			ArrayList<String> arrayLeaveDate, ArrayList<String> arrayLeaveDay,
			ArrayList<String> arrayProjectName,ArrayList<String>arrayLeaveMonthYear) {
		context = con;
		connection = new ConnectionDetector(con);

		this.userID = userID;
		this.empName = empName;
		this.userName = userName;
		this.arrayLeaveId = arrayLeaveId;
		this.arrayApprovalFlag = arrayApprovalFlag;
		this.arrayApproveReason = arrayApproveReason;
		this.arrayReqEmpId=arrayReqEmpId;
		this.arrayReqEmpName = arrayReqEmpName;
		this.arrayReqEmpEmail = arrayReqEmpEmail;
		this.arrayLeaveDate = arrayLeaveDate;
		this.arrayLeaveDay = arrayLeaveDay;
		this.arrayProjectName = arrayProjectName;
		this.arrayLeaveMonthYear = arrayLeaveMonthYear;

	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
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
			response = MethodSoap.send_sumbit_LeaveCancelation_ProjMgrApprova(userID,empName, userName,
					arrayLeaveId, arrayApprovalFlag, arrayApproveReason,
					arrayReqEmpId,arrayReqEmpName, arrayReqEmpEmail, arrayLeaveDate,
					arrayLeaveDay, arrayProjectName,arrayLeaveMonthYear);
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
												LeaveCancelation_ProjMgrApprova.class);
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