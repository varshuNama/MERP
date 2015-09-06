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
import com.mcerp.model.Approvetravelmodel;
import com.mcerp.travel.ApproveTravelView;

public class ApproveAsktask extends AsyncTask<String, String, String> {
	ApproveTravelView act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;
	String managerid, responseData;
	ArrayList<Approvetravelmodel> approvetravelmodelslist;

	public ApproveAsktask(ApproveTravelView con, String managerid) {
		act = con;
		connection = new ConnectionDetector(con);
		this.managerid = managerid;

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
			approvetravelmodelslist = new ArrayList<Approvetravelmodel>();
			response = MethodSoap.gettravelsummary(managerid);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");
			if (message.equals("success")) {
				JSONArray jsonarray = new JSONArray(responseData);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					Approvetravelmodel approvemodel = new Approvetravelmodel();
					approvemodel.setEmail(obj.getString("Email"));
					approvemodel.setEmpCode(obj.getString("EmpCode"));
					approvemodel.setEmpId(obj.getString("EmpId"));
					approvemodel.setEmpName(obj.getString("EmpName"));
					approvemodel.setTravelAmount(obj.getString("TravelAmount"));
					approvemodel.setTravelKm(obj.getString("TravelKm"));

					approvetravelmodelslist.add(approvemodel);

				}

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
				act.getlistdata(act, approvetravelmodelslist);

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				act.nolistdata(act, responseData);

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
							.setTitleText("Does not get proper response.")
							.setContentText("Oops")
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