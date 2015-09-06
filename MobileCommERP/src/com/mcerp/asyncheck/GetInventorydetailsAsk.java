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
import com.mcerp.util.AppPreferences;

public class GetInventorydetailsAsk extends AsyncTask<String, String, String> {
	Inventorydetails act;
	SweetAlertDialog pDialog,dialog;
	int flag=0;
	ConnectionDetector connection;
	AppPreferences prefs; 
	ArrayList<InventoryModel> inventorydata_model_list = new ArrayList<InventoryModel>();
	
	public GetInventorydetailsAsk(Inventorydetails con) {
		act = con;
		connection = new ConnectionDetector(con);
		prefs=AppPreferences.getInstance(act);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new SweetAlertDialog(act, SweetAlertDialog.PROGRESS_TYPE)
		.setTitleText("Loading");
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		String message = null, response = null;
		try {
			response = MethodSoap.getinventorydetails(prefs.getEmpCode());
			JSONObject jsonobj = new JSONObject(response);
			message = jsonobj.getString("message");
			JSONArray jsonarray = jsonobj.getJSONArray("DataArr");
			InventoryModel ModelData;
			JSONObject obj;
			for (int i = 0; i < jsonarray.length(); i++) {
				obj = jsonarray.getJSONObject(i);
				ModelData = new InventoryModel();
				ModelData.setAssetStockId(obj.getString("AssetStockId"));
				ModelData.setAssert_name(obj.getString("AssetName"));
				ModelData.setAssert_categoryname(obj.getString("CategoryName"));
				ModelData.setAssert_sr_no(obj.getString("Asset_SrNo"));
				ModelData.setAssert_tag_no(obj.getString("Asset_TagNo"));
				
				ModelData.setAcceptStatus(obj.getString("AcceptStatus"));
				ModelData.setAssetIssueDate(obj.getString("AssetIssueDate"));
				inventorydata_model_list.add(ModelData);
				
				

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

				
				act.getinventorylist_data(act,
						inventorydata_model_list);

				pDialog.dismiss();

			} else if (result.equals("fail")) {
				new SweetAlertDialog(act, SweetAlertDialog.NORMAL_TYPE)
				.setTitleText("Oops...").setContentText("ddhsdjsjdsjdhhs")
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