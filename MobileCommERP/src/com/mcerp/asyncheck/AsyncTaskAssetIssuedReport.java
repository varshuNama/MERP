package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.assets.AssetReport;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.AssetModelData;
import com.mcerp.util.AppPreferences;

public class AsyncTaskAssetIssuedReport extends
		AsyncTask<String, String, String> {

	String responseData, Message = null;
	SweetAlertDialog pDialog,dialog;
	AppPreferences prefs;
	ArrayList<AssetModelData> reportdata;
	Context context;
	int flag=0;
	ConnectionDetector connectionDetector;

	public AsyncTaskAssetIssuedReport(AssetReport assetReport) {
		context = assetReport;
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
		reportdata = new ArrayList<AssetModelData>();

		try {
			response = MethodSoap.getAssetsIssuedReport(prefs.getUserID(),prefs.getEmpCode());
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");

			if (message.equals("success")) {

				JSONArray jsonarray = new JSONArray(responseData);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject json = jsonarray.getJSONObject(i);
					AssetModelData rep = new AssetModelData(context);
					rep.setAssetType(json.getString("AssetType"));
					rep.setCategory(json.getString("Category"));
					rep.setSerialNumber(json.getString("SerialNo"));
					rep.setTagNumber(json.getString("TagNo"));
					rep.setIssuedDate(json.getString("IssueDate"));
					rep.setRemarks(json.getString("Status"));
					rep.setPendingTransferTo(json.getString("PendingTransferTo"));
					reportdata.add(rep);

				}
			}

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
				((AssetReport)context).callAssetReportSuccessAdapter(reportdata);
				pDialog.dismiss();

			} else if (result.equals("fail")) {
				((AssetReport)context).callAssetReportFailAdapter(responseData);
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
			
		}

	}
}
