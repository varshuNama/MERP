package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.assets.Inventorydetails;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.model.InventoryModel;

public class SubmitInventorydetailsAsk extends
		AsyncTask<String, String, String> {
	Inventorydetails act;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connection;
	int flag = 0;
	ArrayList<InventoryModel> inventory_model_list = new ArrayList<InventoryModel>();
	String trnsferm, trnstype, myresult;
	ArrayList<String> arrayAssetStockIdlist;
	ArrayList<String> arrayAssetRemarkslist;
	ArrayList<String> arrayAssetBarCodelist;
	String empid;

	public SubmitInventorydetailsAsk(Inventorydetails con,
			ArrayList<InventoryModel> inventory_model_list) {
		act = con;

		this.inventory_model_list = inventory_model_list;
	}

	public SubmitInventorydetailsAsk(Inventorydetails act, String empid,
			ArrayList<String> arrayAssetStockIdlist,
			ArrayList<String> arrayAssetRemarkslist,
			ArrayList<String> arrayAssetBarCodelist) {
		this.empid = empid;
		this.act = act;
		connection = new ConnectionDetector(act);
		this.arrayAssetStockIdlist = arrayAssetStockIdlist;
		this.arrayAssetBarCodelist = arrayAssetBarCodelist;
		this.arrayAssetRemarkslist = arrayAssetRemarkslist;
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

			response = MethodSoap.sendInventorydetails(empid,
					arrayAssetStockIdlist, arrayAssetBarCodelist,
					arrayAssetRemarkslist);
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			myresult = jsonobj.getString("DataArr");
			try {

				JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
				InventoryModel ModelData;
				JSONObject obj;
				for (int i = 0; i < jsonarray.length(); i++) {
					obj = jsonarray.getJSONObject(i);
					ModelData = new InventoryModel();

					inventory_model_list.add(ModelData);

				}
			} catch (Exception e) {
				e.printStackTrace();
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
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Success").setContentText(myresult)
						.show();
				pDialog.dismiss();
			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
						.setTitleText("Oops...").setContentText(myresult)
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

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			pDialog.dismiss();
		}

	}
}