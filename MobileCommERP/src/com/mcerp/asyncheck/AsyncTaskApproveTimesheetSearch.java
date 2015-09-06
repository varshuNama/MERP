package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.ApproveTimeSheet;
import com.mcerp.main.ApproveTimeSheetSearch;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.TimesheetSearchModel;
import com.mcerp.util.AppPreferences;

public class AsyncTaskApproveTimesheetSearch extends
		AsyncTask<String, String, String> {

	AppPreferences prefs;
	Activity context;
	ConnectionDetector connection;
	String empid, monthyr;
	int flag=0;
	ArrayList<TimesheetSearchModel> reportdata;
	String responsedata;
	SweetAlertDialog pDialog,dialog;

	public AsyncTaskApproveTimesheetSearch(Activity con, String empidd,
			String monthyrrr) {

		
		context = con;
		empid = empidd;
		monthyr = monthyrrr;
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		prefs = AppPreferences.getInstance(context);
		reportdata = new ArrayList<TimesheetSearchModel>();
		connection = new ConnectionDetector(context);
		pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
		pDialog.show();
		pDialog.setCancelable(false);
		pDialog.setCanceledOnTouchOutside(false);

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.getTimeSheetApproveSearch(prefs.getUserID(),
					empid, monthyr);

			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responsedata = jsonobj.getString("DataArr");
			if (message.equals("success")) {

				JSONArray jsonarray = new JSONArray(responsedata);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject json = jsonarray.getJSONObject(i);
					TimesheetSearchModel data = new TimesheetSearchModel();
					data.setDate(json.getString("TSDate"));
					data.setDay(json.getString("TSDay"));
					data.setActivity(json.getString("TSActivity"));
					data.setDescription(json.getString("TSDescription"));
					data.setStatus(json.getString("ViewStatus"));
					data.setDA(json.getString("DA"));
					data.setProjMgrEmail(json.getString("ProjMgrEmail"));
					data.setProjMgrName(json.getString("ProjMgrName"));
					data.setProjDesc(json.getString("ProjDesc"));
					data.setEmpName(json.getString("EmpName"));
					data.setEmpEmail(json.getString("EmpEmail"));
					data.setAssetDesc(json.getString("AssetDesc"));
					data.setTSDId(json.getString("TSDId"));
					data.setTSId(json.getString("TSId"));

					reportdata.add(data);

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

				Intent intent = new Intent(context,ApproveTimeSheetSearch.class);
				intent.putExtra("mylist", reportdata);
				intent.putExtra("EMPID", empid);
				intent.putExtra("MONTHYEAR", monthyr);
				context.startActivity(intent);
				((ApproveTimeSheet)context).finish();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...").setContentText(responsedata)
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
