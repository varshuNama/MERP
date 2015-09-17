package com.mcerp.asyncheck;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Complete_Training;
import com.mcerp.main.NavigationActivity;

public class AsynctaskCompleteTrainingButton extends
		AsyncTask<String, String, String> {
	String user_ID, traing_id, myresult;
	int flag = 0;

	Complete_Training act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;

	public AsynctaskCompleteTrainingButton(Complete_Training con,
			String userID, String id) {
		this.user_ID = userID;
		this.traing_id = id;
		act = con;

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
			response = MethodSoap.sendCompleteTrainingData(traing_id, user_ID);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");

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

				dialog = new SweetAlertDialog(act,
						SweetAlertDialog.SUCCESS_TYPE)
						.setTitleText("Successfully")
						.setContentText("myresult")
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(SweetAlertDialog sDialog) {
										Intent intent = new Intent(act,
												Complete_Training.class);
										act.startActivity(intent);
										act.finish();
										dialog.dismiss();
									}
								});
				dialog.show();

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Oops. NO Record Found")
						.setContentText(myresult).show();
				pDialog.dismiss();

			} else if (!connection.isConnectingToInternet()) {

				new SweetAlertDialog(act, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
				pDialog.dismiss();
			} else {

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
							.setContentText("Server Connection Problem.")
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

		}

	}
}