package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.ProjectCostEditSheetModel;
import com.mcerp.model.ProjectCostGetSheetModel;
import com.mcerp.projectedcosting.GetSheetEditData;

public class AsyncTaskProjectCostEditGetSheet extends
		AsyncTask<String, String, String> {
	GetSheetEditData act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;
	String strProjCode, strSheetDate;
	ArrayList<ProjectCostEditSheetModel> arraygetdata;
	String empemail, empname, empid, messageData,active_flag;

	public AsyncTaskProjectCostEditGetSheet(GetSheetEditData con,
			SweetAlertDialog Dialog, String strProjCode, String strSheetDate,String active_flag) {
		act = con;
		pDialog = Dialog;
		connection = new ConnectionDetector(con);
		this.strProjCode = strProjCode;
		this.strSheetDate = strSheetDate;
		this.active_flag=active_flag;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		pDialog.show();
		pDialog.setCancelable(false);
		pDialog.setCanceledOnTouchOutside(false);

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			arraygetdata = new ArrayList<ProjectCostEditSheetModel>();
			response = MethodSoap.ERPProjectCostEditGetData(strProjCode, active_flag,strSheetDate);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			if (message.equals("success")) {
				JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
				JSONObject obj;
				ProjectCostEditSheetModel data;
				for (int i = 0; i < jsonarray.length(); i++) {

					data = new ProjectCostEditSheetModel();
					obj = jsonarray.getJSONObject(i);
					data.setMonthdate(obj.getString("ForTheMonthYear"));
					data.setProjectcode(obj.getString("ProjCode"));
					data.setProjectname(obj.getString("Projname"));
					data.setTotalcost(obj.getString("Cost"));
					data.setMonth_year_date(obj.getString("MonthYear"));
					data.setId(obj.getString("Id"));
					data.setSheet_id(obj.getString("SheetId"));

					arraygetdata.add(data);

				}
			} else if (message.equals("fail")) {
				messageData = jsonobj.getString("DataArr");
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
				act.sendDataToProjectCostEdit(act, arraygetdata);

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Oops...").setContentText(messageData)
						.show();
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
							.setContentText("Does not get Proper Response.")
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