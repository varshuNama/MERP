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
	LinearLayout header, submit_linear;
	ArrayList<ProjectCostAfterEditSaveModel> array_list;
	Project_Cost_After_EditGet_Sheet_Adapter adapter;
	LinearLayout project_cost_back;
	String responsesubmitdata;
	ArrayList<String> arraySheetId, arrayResourceCode, arrayQty,
			arrayUnitprice, arrayTotal;
	ConnectionDetector connection;
	AppPreferences prefs;
	String project_code, month_year_date;
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
		submit_linear = (LinearLayout) findViewById(R.id.submit_project_cost);
		project_cost_back = (LinearLayout) findViewById(R.id.project_cost_back);

		project_cost_back.setOnClickListener(this);
		submit_linear.setOnClickListener(this);
		connection = new ConnectionDetector(this);
		pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		prefs = AppPreferences.getInstance(this);
		project_code = getIntent().getExtras().getString("ProjectCode");
		month_year_date = getIntent().getExtras().getString("MonthYear");

		// new
		// AsyncTaskProjectCostGetSheet(SaveAfterEditSaveAfterEditGetSheetData.this,
		// Dialog,project_code, month_year_date).execute();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.project_cost_back:
			finish();
			break;
		case R.id.submit_project_cost:
			arraySheetId = new ArrayList<String>();
			arrayResourceCode = new ArrayList<String>();
			arrayQty = new ArrayList<String>();
			arrayUnitprice = new ArrayList<String>();
			arrayTotal = new ArrayList<String>();
			if (adapter != null) {
				for (Iterator<ProjectCostAfterEditSaveModel> i = (adapter.getList())
						.iterator(); i.hasNext();) {

					ProjectCostAfterEditSaveModel item = i.next();
					if (!item.isCheckboxstatus() == false) {
						arraySheetId.add(item.getSheetId());
						arrayResourceCode.add(item.getResourceCode());
						arrayTotal.add(item.getTatalcost());
						arrayQty.add(item.getQantity());
						arrayUnitprice.add(item.getUnit_test());
					}
				}
			}
			if (connection.isConnectingToInternet())
				new AsyncTaskERPProjectCostAddNewProjectedCost().execute("");
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

	public class AsyncTaskERPProjectCostAddNewProjectedCost extends
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
				response = MethodSoap.ERPProjectCostAddNewProjectedCost(
						prefs.getUserID(), month_year_date, project_code,
						arraySheetId, arrayResourceCode, arrayQty,
						arrayUnitprice, arrayTotal);
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
					intent.putExtra("ProjectCode", project_code);
					intent.putExtra("MonthYear", month_year_date);
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

	public void sendDataToProjectCost(SaveAfterEditGetSheetData act,
			ArrayList<ProjectCostAfterEditSaveModel> arraygetdata) {
		adapter = new Project_Cost_After_EditGet_Sheet_Adapter(act, arraygetdata);
		getlist.setAdapter(adapter);
	}
}
