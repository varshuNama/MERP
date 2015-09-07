package com.mcerp.asyncheck;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.assets.TransferAssets;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.util.AppPreferences;

public class AsyncTaskTransferAssets extends AsyncTask<String, String, String> {
	NavigationActivity act;
	SweetAlertDialog pDialog;
	ConnectionDetector connection;
	String myresult1, myresult2, myresult3, myresult4, myresult;
	AppPreferences myprefs;

	SweetAlertDialog mydialog1, mydialog2, mydialog3;
	String checkserverstatus = "true";
	int flag = 0;

	public AsyncTaskTransferAssets(NavigationActivity con) {
		act = con;
		connection = new ConnectionDetector(con);
		myprefs = AppPreferences.getInstance(con);

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
		
		String message1 = null, message2 = null, message3 = null, message4 = null, response = null;
		try {
			response = MethodSoap.getTransferAssets(myprefs.getUserID(),
					myprefs.getEmpCode());
			JSONObject jsonobj = new JSONObject(response);
			message1 = jsonobj.getString("message1");
			myresult1 = jsonobj.getString("DataArr1");
			if (message1.equals("success")) {

				message2 = jsonobj.getString("message2");
				message3 = jsonobj.getString("message3");
				message4 = jsonobj.getString("message4");
				if (message1.equals("success")) {

					if (message2.equals("success")) {
						myresult2 = jsonobj.getString("DataArr2");

						if (message3.equals("success")) {
							myresult3 = jsonobj.getString("DataArr3");

							if (message4.equals("success")) {
								myresult4 = jsonobj.getString("DataArr4");

							}

						}

					}

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			flag = 1;
			
			return message1;
		}
		if (message1.equals("fail")) {
			return message1;
		} else {
			return message4;
		}

	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {
				pDialog.dismiss();

				TransferAssets fragment = new TransferAssets(act);
				fragment.callTransferData(act, myresult1, myresult2, myresult3,
						myresult4);
			
			} else if (result.equals("fail")) {
				pDialog.dismiss();
				mydialog1 = new SweetAlertDialog(act,
						SweetAlertDialog.WARNING_TYPE)
						.setTitleText("Does not get proper response.")
						.setContentText(myresult1)
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(SweetAlertDialog sDialog) {
										Intent intent = new Intent(act,
												NavigationActivity.class);
										act.startActivity(intent);
										((Activity) act).finish();
										mydialog1.dismiss();

									}
								});
				mydialog1.show();

			} else if (!connection.isConnectingToInternet()) {
				pDialog.dismiss();
				mydialog2 = new SweetAlertDialog(act,
						SweetAlertDialog.WARNING_TYPE)
						.setTitleText("Connection Problem")
						.setContentText("Please Check Internet Connection.")
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(SweetAlertDialog sDialog) {
										Intent intent = new Intent(act,
												NavigationActivity.class);
										act.startActivity(intent);
										((Activity) act).finish();
										mydialog2.dismiss();
									}
								});
				mydialog2.show();

			} else {
				pDialog.dismiss();

				if (flag == 1) {
					flag = 0;
					mydialog3 = new SweetAlertDialog(act,
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
					mydialog3.show();

				} else {
					mydialog3 = new SweetAlertDialog(act,
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
					mydialog3.show();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}

	}
}