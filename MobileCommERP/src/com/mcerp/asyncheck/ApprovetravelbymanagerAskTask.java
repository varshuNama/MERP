package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.travel.ApproveTravelView;
import com.mcerp.travel.ApproveTraveldata;
import com.mcerp.util.AppPreferences;

public class ApprovetravelbymanagerAskTask extends AsyncTask<String, String, String> {

	SweetAlertDialog pDialog, dialog;
	AppPreferences prefs;
	int flag=0;
	
	
	ConnectionDetector connection;
	ApproveTraveldata act;
	String ProjMgrId, strProjMgrName,strProjMgrEmail, strEmpName, strEmpEmail;

	ArrayList<String> id, projName,location,travelDate,unit,unitCost,transportMode,transportModeDesc,approveUnitCost,arrayApproveStatus,arrayRemarks;



	public ApprovetravelbymanagerAskTask(ApproveTraveldata act,String ProjMgrId, String strProjMgrName,
			String strProjMgrEmail, String strEmpName, String strEmpEmail,
			ArrayList<String> id, ArrayList<String> location,
			ArrayList<String> projName, ArrayList<String> travelDate,
			ArrayList<String> unit, ArrayList<String> unitCost,
			ArrayList<String> transportMode,
			ArrayList<String> transportModeDesc,
			ArrayList<String> approveUnitCost,
			ArrayList<String> arrayApproveStatus, ArrayList<String> arrayRemarks) {

		this.ProjMgrId=ProjMgrId;
		this.strProjMgrName=strProjMgrName;
		this.strProjMgrEmail=strProjMgrEmail;
		this.strEmpName=strEmpName;
		this.strEmpEmail=strEmpEmail;
		this.act=act;

		this.id = id;
		this.projName = projName;
		this.location = location;
		this.travelDate = travelDate;
		this.unit = unit;
		this.unitCost = unitCost;
		this.transportMode = transportMode;
		this.transportModeDesc = transportModeDesc;
		this.approveUnitCost = approveUnitCost;

		this.arrayApproveStatus = arrayApproveStatus;
		this.arrayRemarks = arrayRemarks;


		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		connection = new ConnectionDetector(act);
		prefs = AppPreferences.getInstance(act);
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
			response = MethodSoap.getApproveRejectTraveldetails(ProjMgrId,
					strProjMgrName, strProjMgrEmail, strEmpName, strEmpEmail, id, projName,location,travelDate,unit,
					unitCost,transportMode,transportModeDesc,approveUnitCost,arrayApproveStatus,arrayRemarks);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			
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
				dialog = new SweetAlertDialog(act,
						SweetAlertDialog.SUCCESS_TYPE)
				.setTitleText("Successfully")
				.setContentText("your request successful")
				.setConfirmText("ok")
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(SweetAlertDialog sDialog) {
								Intent intent = new Intent(act,
										ApproveTravelView.class);
								act.startActivity(intent);
								act.finish();
								dialog.dismiss();
							}
						});
				dialog.show();
				

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
				.setTitleText("Oops...").setContentText("Your request has been declined.")
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
					flag=0;
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
					dialog.show();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
}