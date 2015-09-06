package com.mcerp.asyncheck;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.assets.AcceptDetail;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;

public class AsyncTaskSendAssetsTransferToEmp extends
		AsyncTask<String, String, String> {
	Context context;
	SweetAlertDialog pDialog,dialog;
	ConnectionDetector connection;
	String assetid, trnsferm, trnstype, myresult;
	int flag = 0;

	public AsyncTaskSendAssetsTransferToEmp(Context con, String strAssetId,
			String strTransferFrom, String strTransferType) {
		context = con;
		connection = new ConnectionDetector(con);
		assetid = strAssetId;
		trnsferm = strTransferFrom;
		trnstype = strTransferType;

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
			response = MethodSoap.getAcceptAssetDetail(assetid, trnsferm,
					trnstype);
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

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			if (!result.equals("") && !result.equals("null")
					&& result.equals("success")) {

				Intent intent = new Intent(context, AcceptDetail.class);
				intent.putExtra("RESPONSE", myresult);
				intent.putExtra("MESSAGE", result);
				intent.putExtra("TRANSFERTYPE", trnstype);
				context.startActivity(intent);
				((Activity) context).finish();

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