package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.ApproveTimeSheet;
import com.mcerp.main.NavigationActivity;
import com.mcerp.util.AppPreferences;

public class AsyncTaskApproveRejectTimesheet extends
		AsyncTask<String, String, String> {

	SweetAlertDialog pDialog, dialog;
	AppPreferences prefs;
	int flag=0;
	Context context;
	ConnectionDetector connection;
	String getstatus, prjmgrname, empid, empcode, empname, empemail, projdesc,
			status, myresult;
	ArrayList<String> arraytsid, arraytsdid;

	public AsyncTaskApproveRejectTimesheet(Context con, String getstatuss,
			String prjmgrnames, String empids, String empcodes,
			String empnames, String empemails, String projdescs,
			ArrayList<String> array1, ArrayList<String> array2) {

		status = getstatuss;
		context = con;
		prjmgrname = prjmgrnames;
		empid = empids;
		empcode = empcodes;
		empname = empnames;
		empemail = empemails;
		projdesc = projdescs;
		arraytsid = array1;
		arraytsdid = array2;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		connection = new ConnectionDetector(context);
		prefs = AppPreferences.getInstance(context);
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
			response = MethodSoap.getApproveRejectTimeSheet(prefs.getUserID(),
					prjmgrname, empid, empcode, empname, empemail, projdesc,
					status, arraytsid, arraytsdid);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			myresult = jsonobj.getString("DataArr");

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
												ApproveTimeSheet.class);
										context.startActivity(intent);
										((Activity) context).finish();
										dialog.dismiss();
									}
								});
				dialog.show();

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Oops...").setContentText(myresult)
						.show();
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
		
		}

	}
}