package com.mcerp.main;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.LeaveReportAdapter;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.LeaveReportModel;
import com.mcerp.util.AppPreferences;

public class LeaveReport extends Activity implements OnClickListener {
	String responseData, Message = null;
	Button getbtn;
	LinearLayout backbutton;
	ListView listview;
	TextView norecord;
	ArrayList<LeaveReportModel> reportdata;
	LeaveReportAdapter adapter;
	SweetAlertDialog pDialog;
	AppPreferences prefs;
	ConnectionDetector connectionDetector;
	String responsegetdata;
	Spinner spinyear;
	LinearLayout reportHeader;
	int flag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leavereport);
		prefs = AppPreferences.getInstance(LeaveReport.this);
		connectionDetector = new ConnectionDetector(LeaveReport.this);

		init();
		new AsyncTaskLeaveReport().execute("");

	}

	private void init() {

		backbutton = (LinearLayout) findViewById(R.id.linearback);
		getbtn = (Button) findViewById(R.id.getbtn);
		listview = (ListView) findViewById(R.id.listViewReport);
		norecord = (TextView) findViewById(R.id.textrecordnofound);
		spinyear = (Spinner) findViewById(R.id.spinnerYear);
		reportHeader = (LinearLayout) findViewById(R.id.reportheader);
		Calendar calendar = Calendar.getInstance();
		int year2 = calendar.get(Calendar.YEAR);
		int year1 = year2 - 1;
		int year0 = year2 - 2;
		String str1 = year2 + "";
		String str2 = year1 + "";
		String str3 = year0 + "";
		String year[] = { str1, str2, str3 };
		ArrayAdapter<String> adapter_view = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, year);
		adapter_view
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinyear.setAdapter(adapter_view);
		spinyear.setSelection(0);
		backbutton.setOnClickListener(this);
		getbtn.setOnClickListener(this);

	}

	/* Webservice For Leave Report */

	public class AsyncTaskLeaveReport extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			reportdata = new ArrayList<LeaveReportModel>();
			pDialog = new SweetAlertDialog(LeaveReport.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;

			try {
				response = MethodSoap.getGetLeaveReport(prefs.getUserID(),
						spinyear.getSelectedItem() + "");
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsegetdata = jsonobj.getString("DataArr");
				if (message.equals("success")) {
					JSONArray jsonarray = new JSONArray(responsegetdata);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						LeaveReportModel rep = new LeaveReportModel();
						rep.setMonthDesc(json.getString("MonthDesc"));
						rep.setYearMonth(json.getString("YearMonth"));
						rep.setMonth_Leave(json.getString("MonthLeave"));
						rep.setMonthStatus(json.getString("MonthStatus"));
						rep.setCarry_Forward(json.getString("CarryFwd"));
						rep.setLeave_In_Hand(json.getString("LeaveInHand"));
						rep.setLeave_Taken(json.getString("LeaveTaken"));
						rep.setApproval_pending(json
								.getString("ApprovalPending"));
						rep.setForward(json.getString("Forward"));
						reportdata.add(rep);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				flag=1;
				message="";
				pDialog.dismiss();
			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {

					if (reportdata.size() != 0 && reportdata != null) {
						reportHeader.setVisibility(View.VISIBLE);
						listview.setVisibility(View.VISIBLE);
						norecord.setVisibility(View.GONE);
						adapter = new LeaveReportAdapter(LeaveReport.this,
								reportdata);
						listview.setAdapter(adapter);
						pDialog.dismiss();

					} else {
						norecord.setVisibility(View.VISIBLE);
						listview.setVisibility(View.GONE);
						reportHeader.setVisibility(View.GONE);
						pDialog.dismiss();
					}

				} else if (result.equals("fail")) {
					norecord.setVisibility(View.VISIBLE);
					norecord.setText(responsegetdata);
					reportHeader.setVisibility(View.GONE);
					listview.setVisibility(View.GONE);
					pDialog.dismiss();

				} else if (!connectionDetector.isConnectingToInternet()) {
					new SweetAlertDialog(LeaveReport.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {

					if (flag == 1) {
						flag=0;
						new SweetAlertDialog(LeaveReport.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Server Connection Problem.")
								.show();
						pDialog.dismiss();

					} else {
						new SweetAlertDialog(LeaveReport.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Does not get Proper Response.").show();
						pDialog.dismiss();
					}
				}
			} catch (Exception e) {
				pDialog.dismiss();
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linearback:
			Intent intent = new Intent(LeaveReport.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.getbtn:
			if (connectionDetector.isConnectingToInternet())
				new AsyncTaskLeaveReport().execute("");
			else {
				new SweetAlertDialog(LeaveReport.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(LeaveReport.this, NavigationActivity.class);
		startActivity(intent);
		finish();
	}
}
