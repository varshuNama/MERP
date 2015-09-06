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
import com.mcerp.model.TravelExpensesReport_Model;
import com.mcerp.travel.TravelExpensesTable_Report;

public class AsyncTravelExpensesTableReport extends
		AsyncTask<String, String, String> {
	TravelExpensesTable_Report context;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;
	ArrayList<TravelExpensesReport_Model> travelexpensesreport_model_list = new ArrayList<TravelExpensesReport_Model>();
	String myresult, year, month, travelmode, empid, responseData;

	public AsyncTravelExpensesTableReport(TravelExpensesTable_Report con,
			String empid, String year, String month, String travelmode) {
		context = con;
		connection = new ConnectionDetector(con);
		this.year = year;
		this.month = month;
		this.travelmode = travelmode;
		this.empid = empid;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.getTravelExpenseReport(empid, year + month,
					travelmode, "D");
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			responseData = jsonobj.getString("DataArr");
			if (message.equals("success")) {
				JSONArray jsonarray = new JSONArray(responseData);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					TravelExpensesReport_Model ModelData = new TravelExpensesReport_Model();
					ModelData.setProjName(obj.getString("ProjName"));
					ModelData.setLocation(obj.getString("Location"));
					ModelData.setTravelDate(obj.getString("TravelDate"));
					ModelData.setUnit(obj.getString("Unit"));
					ModelData.setUnitCost(obj.getString("UnitCost"));
					ModelData.setTransportMode(obj.getString("TransportMode"));
					ModelData.setTransportModeDesc(obj
							.getString("TransportModeDesc"));
					ModelData.setApproveUnit(obj.getString("ApproveUnit"));
					ModelData.setApproveUnitCost(obj
							.getString("ApproveUnitCost"));
					ModelData.setStatus(obj.getString("Status"));
					travelexpensesreport_model_list.add(ModelData);

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

				context.gettravel_expenses_report(context,
						travelexpensesreport_model_list);

				pDialog.dismiss();

			} else if (result.equals("fail")) {

				context.gettravel_expenses_report_fail(context, responseData);

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