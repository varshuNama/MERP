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
import com.mcerp.model.ProjectCostAfterEditSaveModel;
import com.mcerp.model.ProjectCostReortModel;
import com.mcerp.projectedcosting.Project_Costing_Report;
import com.mcerp.projectedcosting.SaveAfterEditGetSheetData;

public class AsyncTaskERPProjectCostReportBindGrid extends AsyncTask<String, String, String> {
	Project_Costing_Report act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;

	ArrayList<ProjectCostReortModel> arraygetdata;
	String qflag,activecloseflag, projectcode,messageData,monthyear;

	public AsyncTaskERPProjectCostReportBindGrid(Project_Costing_Report con, SweetAlertDialog Dialog,String projectCode,
			String monthYear,String strflag,String strQFlag) {
		act = con;
		pDialog=Dialog;
		connection = new ConnectionDetector(con);
		activecloseflag= strflag;
		qflag = strQFlag;
		projectcode=projectCode;
		monthyear=monthYear;
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
			arraygetdata = new ArrayList<ProjectCostReortModel>();
			response = MethodSoap.ERPProjectCostReportBindGrid(projectcode,monthyear,activecloseflag,qflag);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			if(message.equals("success"))
			{
				JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
				JSONObject obj;
				ProjectCostReortModel data;
				for (int i = 0; i < jsonarray.length(); i++) {

					data = new ProjectCostReortModel();
					obj = jsonarray.getJSONObject(i);
					data.setMY(obj.getString("MY"));
					data.setMonthYear(obj.getString("MonthYear"));
					data.setProjname(obj.getString("Projname"));
					data.setProjCode(obj.getString("ProjCode"));
					data.setActualCost(obj.getString("ActualCost"));
					data.setClosed(obj.getString("Closed"));
				    data.setCostGap(obj.getString("CostGAP"));
				    data.setProjctedCost(obj.getString("ProjectedCost"));
				    data.setNetProjectedCost(obj.getString("NetProjectedCost"));
				    data.setPFwdCost(obj.getString("PFwdCost"));
            	    arraygetdata.add(data);

				}
			}else if(message.equals("fail")){
				messageData=jsonobj.getString("DataArr");
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
			act.getdataForProjectCostReport(act, arraygetdata);
				pDialog.dismiss();


			} else if (result.equals("fail")) {
				pDialog.dismiss();
				dialog = new SweetAlertDialog(act,
						SweetAlertDialog.WARNING_TYPE)
				.setTitleText("Sorry")
				.setContentText(messageData)
				.setConfirmText("ok")
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								
								((Activity) act).finish();

								sDialog.dismiss();
							}
						});
				dialog.show();

				

			} else if (!connection.isConnectingToInternet()) {

				
				pDialog.dismiss();
				dialog = new SweetAlertDialog(act,
						SweetAlertDialog.WARNING_TYPE)
				.setTitleText("Sorry")
				.setContentText("Interconnection not available.")
				.setConfirmText("ok")
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								
								((Activity) act).finish();

								sDialog.dismiss();
							}
						});
				dialog.show();
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
			
		}

	}
}