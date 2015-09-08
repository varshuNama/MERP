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
import com.mcerp.projectedcosting.SaveAfterEditGetSheetData;

public class AsyncTaskERPProjectCostEditGetEditDetails  extends AsyncTask<String, String, String> {
	SaveAfterEditGetSheetData act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;
	String cost_ID, sheet_ID;
	ArrayList<ProjectCostAfterEditSaveModel> arraygetdata;
	String empemail, empname, empid,messageData;

	public AsyncTaskERPProjectCostEditGetEditDetails(SaveAfterEditGetSheetData con, SweetAlertDialog Dialog,String cost_ID,
			String sheet_ID) {
		act = con;
		pDialog=Dialog;
		connection = new ConnectionDetector(con);
		this.cost_ID = cost_ID;
		this.sheet_ID = sheet_ID;

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
			arraygetdata = new ArrayList<ProjectCostAfterEditSaveModel>();
			response = MethodSoap.ERPProjectCostEditGetEditDetails(cost_ID,sheet_ID);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			if(message.equals("success"))
			{
				JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
				JSONObject obj;
				ProjectCostAfterEditSaveModel data;
				for (int i = 0; i < jsonarray.length(); i++) {

					data = new ProjectCostAfterEditSaveModel();
					obj = jsonarray.getJSONObject(i);
					data.setCostId(obj.getString("CostId"));
					data.setResourceName(obj.getString("ResourceName"));
					data.setResourceId(obj.getString("ResourceId"));
					data.setDetailId(obj.getString("DetailId"));
					data.setQantity(obj.getString("Qty"));
					data.setSheetId(obj.getString("SheetId"));
				    data.setTatalcost(obj.getString("Total"));
				    data.setUnit_test(obj.getString("Price"));
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
				act.sendDataToProjectEditCost(act,arraygetdata);
				pDialog.dismiss();


			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
				.setTitleText("Oops...")
				.setContentText(messageData).show();
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