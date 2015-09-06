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
import com.mcerp.model.Approvetraveldetailsmodel;
import com.mcerp.travel.ApproveTraveldata;

public class ApproveAcceptAsktask extends
AsyncTask<String, String, String> {
	ApproveTraveldata act;
	SweetAlertDialog pDialog,dialog;
	ConnectionDetector connection;
	int flag=0;
	String managerid;
	ArrayList<Approvetraveldetailsmodel> approvetraveldetailsmodels;
	String empemail,empname,empid;
	public ApproveAcceptAsktask(ApproveTraveldata con, String managerid,String empid,String empemail,String empname) {
		act = con;
		connection = new ConnectionDetector(con);
		this.managerid = managerid;
		this.empemail=empemail;
		this.empname=empname;
		this.empid=empid;

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
			approvetraveldetailsmodels=new ArrayList<Approvetraveldetailsmodel>();
			response = MethodSoap.gettraveldetailsforapprovel(managerid,empid);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
			JSONObject obj;
			Approvetraveldetailsmodel  approvemodel;
			for (int i = 0; i < jsonarray.length(); i++) {

				approvemodel=new Approvetraveldetailsmodel();
				obj=jsonarray.getJSONObject(i);
				approvemodel.setId(obj.getString("Id"));
				approvemodel.setLocation(obj.getString("Location"));
				approvemodel.setProjName(obj.getString("ProjName"));
				approvemodel.setTransportMode(obj.getString("TransportMode"));
				approvemodel.setTransportModeDesc(obj.getString("TransportModeDesc"));
				approvemodel.setTravelDate(obj.getString("TravelDate"));
				approvemodel.setUnit(obj.getString("Unit"));
				approvemodel.setUnitCost(obj.getString("UnitCost"));
				approvemodel.setUnitCost(obj.getString("ApproveUnitCost"));
				
				approvetraveldetailsmodels.add(approvemodel);



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
				act.getlistdata(act,approvetraveldetailsmodels);


				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
				.setTitleText("Oops...").setContentText("No Record Found")
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