package com.mcerp.projectedcosting;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.AsyncTaskERPProjectCostEditGetEditDetails;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.ProjectCostAfterEditSaveModel;
import com.mcerp.util.AppPreferences;

public class SaveAfterEditGetSheetData extends Activity implements
		OnClickListener {
	ListView getlist;
	TextView norecord;
	SweetAlertDialog pDialog;
	int flag = 0;
	LinearLayout header, edit__button_linear;
	ArrayList<ProjectCostAfterEditSaveModel> array_list;
	Project_Cost_After_EditGet_Sheet_Adapter adapter;
	LinearLayout project_cost_back;
	String responsesubmitdata;
	ArrayList<String> arrayDetailId, arraySheetId, arrayResourceId, arrayQty,
			arrayCostId, arrayUnitprice, arrayAmount;
	ConnectionDetector connection;
	AppPreferences prefs;
	String cost_id, sheet_id, month_year_date;
	SweetAlertDialog Dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_edit_save_data);
		init();
	}

	private void init() {
		Dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		getlist = (ListView) findViewById(R.id.list_project_cost_after_edit);
		norecord = (TextView) findViewById(R.id.noRecordprojectprojectcost);
		header = (LinearLayout) findViewById(R.id.project_cost_header);
		edit__button_linear = (LinearLayout) findViewById(R.id.submit_project_cost);
		project_cost_back = (LinearLayout) findViewById(R.id.project_cost_after_back_edit);

		project_cost_back.setOnClickListener(this);
		edit__button_linear.setOnClickListener(this);
		connection = new ConnectionDetector(this);
		pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		prefs = AppPreferences.getInstance(this);
		cost_id = getIntent().getExtras().getString("CostID");
		sheet_id = getIntent().getExtras().getString("SheetID");

		new AsyncTaskERPProjectCostEditGetEditDetails(
				SaveAfterEditGetSheetData.this, Dialog, cost_id, sheet_id)
				.execute();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.project_cost_after_back_edit:

			finish();
			break;
		case R.id.submit_project_cost:
			arraySheetId = new ArrayList<String>();
			arrayResourceId = new ArrayList<String>();
			arrayQty = new ArrayList<String>();
			arrayUnitprice = new ArrayList<String>();
			arrayAmount = new ArrayList<String>();
			arrayDetailId = new ArrayList<String>();
			arrayCostId = new ArrayList<String>();
			if (adapter != null) {
				for (Iterator<ProjectCostAfterEditSaveModel> i = (adapter
						.getList()).iterator(); i.hasNext();) {

					ProjectCostAfterEditSaveModel item = i.next();
					if (!item.isCheckboxstatus() == false) {
						arraySheetId.add(item.getSheetId());
						arrayResourceId.add(item.getResourceId());
						arrayQty.add(item.getQantity());
						arrayUnitprice.add(item.getUnit_test());
						arrayCostId.add(item.getCostId());
						arrayAmount.add(item.getTatalcost());
						arrayDetailId.add(item.getDetailId());
					}
				}
			}
			if (connection.isConnectingToInternet())
				new AsyncTaskERP_ProjectCost_Edit_SubmitEditData().execute("");
			else {
				new SweetAlertDialog(SaveAfterEditGetSheetData.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}

			break;

		default:
			break;
		}

	}

	/* Submit Data ERP Project Cost Add New ProjectedCost */

	public class AsyncTaskERP_ProjectCost_Edit_SubmitEditData extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new SweetAlertDialog(SaveAfterEditGetSheetData.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {
				response = MethodSoap.ERPProjectCostEditSubmitEditData(cost_id,
						sheet_id, prefs.getUserID(), arrayDetailId,
						arrayCostId, arraySheetId, arrayResourceId, arrayQty,
						arrayUnitprice, arrayAmount);
				JSONObject jsonobj = new JSONObject(response);
				
				message = jsonobj.getString("message");
				responsesubmitdata = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				message = "";
				pDialog.dismiss();
			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {
					Toast.makeText(SaveAfterEditGetSheetData.this,
							responsesubmitdata, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(SaveAfterEditGetSheetData.this,
							SaveAfterEditGetSheetData.class);
					intent.putExtra("SaveAfterEditGetSheetData", array_list);
					intent.putExtra("CostID", cost_id);
					intent.putExtra("SheetID", sheet_id);
					startActivity(intent);
					finish();
					pDialog.dismiss();

				} else if (result.equals("fail")) {
					Toast.makeText(SaveAfterEditGetSheetData.this,
							responsesubmitdata, Toast.LENGTH_LONG).show();
					pDialog.dismiss();
				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(SaveAfterEditGetSheetData.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {
					if (flag == 1) {
						flag = 0;
						new SweetAlertDialog(SaveAfterEditGetSheetData.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Server connection problem !")
								.show();
						pDialog.dismiss();
					} else {
						new SweetAlertDialog(SaveAfterEditGetSheetData.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText(
										"Does not get Proper Response !")
								.show();
						pDialog.dismiss();
					}
				}
			} catch (Exception e) {
				pDialog.dismiss();
			}

		}
	}

	public void sendDataToProjectEditCost(SaveAfterEditGetSheetData act,
			ArrayList<ProjectCostAfterEditSaveModel> arraygetdata) {
		adapter = new Project_Cost_After_EditGet_Sheet_Adapter(act,
				arraygetdata);
		getlist.setAdapter(adapter);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
