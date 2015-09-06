package com.mcerp.asyncheck;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.fragments.Home;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.HomeModel;
import com.mcerp.util.AppPreferences;

public class AsyncTaskDataLogin extends AsyncTask<String, String, String> {

	Context act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connectionDetector;
	HomeModel modeldata;
	String UserName, Password, UserId;
	AppPreferences prefs;
	Home homefragment;
	String responsedata;
	int flag = 0;

	public AsyncTaskDataLogin(Context context, String username,
			String password, String userid) {
		act = context;
		prefs = AppPreferences.getInstance(act);
		connectionDetector = new ConnectionDetector(act);
		UserName = username;
		Password = password;
		UserId = userid;
		modeldata = new HomeModel();

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
		String Message = null;
		try {
			responsedata = MethodSoap
					.getLoginDetail(UserName, Password, UserId);
			JSONObject jsonobj = new JSONObject(responsedata);
			Message = jsonobj.getString("message");

		} catch (Exception e) {
			e.printStackTrace();
			flag = 1;
			Message = "";

		}

		return Message;

	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (result != null && !result.equals("") && !result.equals("null")
					&& result.equals("success")) {
				pDialog.dismiss();
				prefs.setPassword(Password);
				prefs.setResponse(responsedata);
				Intent intent = new Intent(act,
						com.mcerp.main.NavigationActivity.class);
				act.startActivity(intent);
				((Activity) act).finish();

			} else if (!connectionDetector.isConnectingToInternet()) {
				new SweetAlertDialog(act, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Connection not available!").show();
				pDialog.dismiss();
			} else {
				pDialog.dismiss();
				if (flag == 1) {
					flag = 0;
					dialog = new SweetAlertDialog(act,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Server Connection Problem.")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {

											sDialog.dismiss();
										}
									});
					dialog.show();

				} else {
					dialog = new SweetAlertDialog(act,
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops")
							.setContentText("Does Not Get Proper Respnse.")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {

											sDialog.dismiss();
										}
									});
					dialog.show();

				}
			}
		} catch (Exception e) {

		}

	}

}