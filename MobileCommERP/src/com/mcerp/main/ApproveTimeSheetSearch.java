package com.mcerp.main;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.ApproveTimesheetSearchAdapter;
import com.mcerp.asyncheck.AsyncTaskApproveRejectTimesheet;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.TimesheetSearchModel;
import com.mcerp.util.AppPreferences;

public class ApproveTimeSheetSearch extends Activity implements OnClickListener {
	TextView norecord;
	ListView list;
	LinearLayout linearheader, backimg, approveBtn, rejectBtn;
	ConnectionDetector connection;
	
	String responsedata, myresult;
	ApproveTimesheetSearchAdapter adapter;
	ArrayList<TimesheetSearchModel> reportdata;
	String EmpId, Monthyr;
	AppPreferences prefs;
	ArrayList<String> arrayTSId = new ArrayList<String>();
	ArrayList<String> arrayTSDID = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approvesearch);
		
		reportdata = (ArrayList<TimesheetSearchModel>) getIntent()
				.getSerializableExtra("mylist");
		EmpId=getIntent().getExtras().getString("EMPID");
		Monthyr=getIntent().getExtras().getString("MONTHYEAR");
		init();

	}

	private void init() {
		norecord = (TextView) findViewById(R.id.norecordfound);
		linearheader = (LinearLayout) findViewById(R.id.searchsheettheader);
		backimg = (LinearLayout) findViewById(R.id.bkimg);
		approveBtn = (LinearLayout) findViewById(R.id.approve);
		rejectBtn = (LinearLayout) findViewById(R.id.reject);
		list = (ListView) findViewById(R.id.approvelistviewsearch);
		connection = new ConnectionDetector(ApproveTimeSheetSearch.this);
		prefs = AppPreferences.getInstance(this);
		backimg.setOnClickListener(this);
		approveBtn.setOnClickListener(this);
		rejectBtn.setOnClickListener(this);
		getasktashdata();

	}

	@Override
	public void onClick(View v) {
		boolean checkStatus;
		switch (v.getId()) {
		case R.id.approve:

			checkStatus = getData();
			if (connection.isConnectingToInternet()) {
				if (checkStatus == true) {

					new AsyncTaskApproveRejectTimesheet(
							ApproveTimeSheetSearch.this, "2",
							reportdata.get(0).getProjMgrName(), EmpId, "",
							reportdata.get(0).getEmpName(), reportdata.get(0)
									.getEmpEmail(), reportdata.get(0)
									.getProjDesc(), arrayTSId, arrayTSDID)
							.execute("");
				} else {
					Toast.makeText(ApproveTimeSheetSearch.this,
							"Please select all Rows.", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				new SweetAlertDialog(ApproveTimeSheetSearch.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}
			break;

		case R.id.reject:

			checkStatus = getData();

			if (connection.isConnectingToInternet()) {
				if (checkStatus == true) {

					new AsyncTaskApproveRejectTimesheet(
							ApproveTimeSheetSearch.this, "3",
							reportdata.get(0).getProjMgrName(), EmpId, "",
							reportdata.get(0).getEmpName(), reportdata.get(0)
									.getEmpEmail(), reportdata.get(0)
									.getProjDesc(), arrayTSId, arrayTSDID)
							.execute("");
				} else {
					Toast.makeText(ApproveTimeSheetSearch.this,
							"Please select all Rows.", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				new SweetAlertDialog(ApproveTimeSheetSearch.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}
			break;
		case R.id.bkimg:
			Intent intent = new Intent(ApproveTimeSheetSearch.this,
					ApproveTimeSheet.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

	public void getasktashdata() {

		linearheader.setVisibility(View.VISIBLE);
		list.setVisibility(View.VISIBLE);
		approveBtn.setVisibility(View.VISIBLE);
		rejectBtn.setVisibility(View.VISIBLE);
		norecord.setVisibility(View.GONE);

		adapter = new ApproveTimesheetSearchAdapter(
				ApproveTimeSheetSearch.this, reportdata);
		list.setAdapter(adapter);

	}

	boolean getData() {
		boolean flag = true;
		if (adapter != null) {

			for (Iterator<TimesheetSearchModel> i = (adapter.getList())
					.iterator(); i.hasNext();) {
				TimesheetSearchModel item = i.next();
				arrayTSId.add(item.getTSId());
				arrayTSDID.add(item.getTSDId());
				if (!item.isChecked()) {
					flag = false;
					break;
				}

			}

		}
		return flag;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(ApproveTimeSheetSearch.this,
				ApproveTimeSheet.class);
		startActivity(intent);
		finish();
	}
}
