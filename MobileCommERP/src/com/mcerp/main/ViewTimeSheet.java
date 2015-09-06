package com.mcerp.main;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.ViewTimeSheetAdapter;
import com.mcerp.asyncheck.AsyncTaskViewDetailTimeSheet;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.ViewTimeSheetModel;
import com.mcerp.util.AppPreferences;

public class ViewTimeSheet extends Activity implements OnClickListener {

	Button viewBtn;
	LinearLayout backbutton, linear10, secondheader, mainHeaderView;
	ListView listview;
	TextView norecord;
	String checkFlag;
	ViewTimeSheetAdapter adapter;
	ArrayList<ViewTimeSheetModel> arraylist;
	AppPreferences prefs;
	ConnectionDetector connectionDetector;
	String responsegetdata;
	Spinner spinyear;
	LinearLayout reportHeader,liearapprove,mainLinearTimesheet;
	TextView t1, t2, t3, t4, t5, t6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_timesheet);
		prefs = AppPreferences.getInstance(ViewTimeSheet.this);
		connectionDetector = new ConnectionDetector(ViewTimeSheet.this);

		init(ViewTimeSheet.this);
		new AsyncTaskViewDetailTimeSheet(ViewTimeSheet.this,
				spinyear.getSelectedItem() + "", "S", "1").execute("");

	}

	private void init(ViewTimeSheet v) {

		backbutton = (LinearLayout) findViewById(R.id.viewback);
		viewBtn = (Button) findViewById(R.id.viewBtn);
		listview = (ListView) findViewById(R.id.listviewtime);
		norecord = (TextView) findViewById(R.id.noRecordViewTimesheet);
		spinyear = (Spinner) findViewById(R.id.spinView);
		reportHeader = (LinearLayout) findViewById(R.id.headerview);
		secondheader = (LinearLayout) findViewById(R.id.secondheader);
		mainHeaderView = (LinearLayout) findViewById(R.id.mainHeaderView);
		mainLinearTimesheet=(LinearLayout) findViewById(R.id.mainLinearViewTimesheet);

		t1 = (TextView) findViewById(R.id.linear1);
		t2 = (TextView) findViewById(R.id.linear2);
		t3 = (TextView) findViewById(R.id.linear3);
		t4 = (TextView) findViewById(R.id.linear4);
		t5 = (TextView) findViewById(R.id.linear5);
		t6 = (TextView) findViewById(R.id.linear6);
		liearapprove= (LinearLayout) findViewById(R.id.linearapproved);
		linear10 = (LinearLayout) findViewById(R.id.linear7);

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
		viewBtn.setOnClickListener(this);

	}

	public void calladapter(ArrayList<ViewTimeSheetModel> timesheetmodel,
			String responsegetdata, String chkst) {
		checkFlag = chkst;
		arraylist = timesheetmodel;
		if (checkFlag == "1") {
			ViewTimeSheetData(responsegetdata);
		} else {

			ViewTimeSheetSearch(responsegetdata);

		}

	}

	public void ViewTimeSheetSearch(String responsegetdata) {
		if (arraylist.size() != 0 && arraylist != null) {
		
			reportHeader.setVisibility(View.VISIBLE);
			mainLinearTimesheet.setWeightSum(0.85f);
			listview.setVisibility(View.VISIBLE);
			norecord.setVisibility(View.GONE);
			secondheader.setVisibility(View.GONE);
			t1.setText("Project");
			t2.setText("ProjectManager");
			t3.setText("Date");
			t4.setText("Leave/Holiday");
			t5.setText("Status");
			linear10.setVisibility(View.GONE);
			liearapprove.setVisibility(View.GONE);
			adapter = new ViewTimeSheetAdapter(ViewTimeSheet.this, arraylist,
					checkFlag);
			listview.setAdapter(adapter);
		} else {
			norecord.setVisibility(View.VISIBLE);
			norecord.setText(responsegetdata);
			reportHeader.setVisibility(View.GONE);
			listview.setVisibility(View.GONE);
		}

	}

	void ViewTimeSheetData(String responsegetdata) {
		if (arraylist.size() != 0 && arraylist != null) {
			mainLinearTimesheet.setWeightSum(1);
			reportHeader.setVisibility(View.VISIBLE);
			listview.setVisibility(View.VISIBLE);
			linear10.setVisibility(View.VISIBLE);
			liearapprove.setVisibility(View.VISIBLE);
			norecord.setVisibility(View.GONE);
			secondheader.setVisibility(View.VISIBLE);
		    mainHeaderView.setWeightSum(1);
			t1.setText("Month");
			t2.setText("Project");
			t3.setText("Saved");
			t4.setText("Pending");
			t5.setText("Rejected");
			t6.setText("Approved");
		
			adapter = new ViewTimeSheetAdapter(ViewTimeSheet.this, arraylist,checkFlag);
			listview.setAdapter(adapter);
		} else {
			norecord.setVisibility(View.VISIBLE);
			norecord.setText(responsegetdata);
			reportHeader.setVisibility(View.GONE);
			listview.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.viewback:
			if (checkFlag == "2") {
				Intent intent = new Intent(ViewTimeSheet.this,
						ViewTimeSheet.class);
				startActivity(intent);
				finish();

			} else {
				Intent intent = new Intent(ViewTimeSheet.this,
						NavigationActivity.class);
				startActivity(intent);
				finish();

			}
			break;
		case R.id.viewBtn:
			if (connectionDetector.isConnectingToInternet())
				new AsyncTaskViewDetailTimeSheet(ViewTimeSheet.this,
						spinyear.getSelectedItem() + "", "S", "1").execute("");
			else {
				new SweetAlertDialog(ViewTimeSheet.this,
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
		if (checkFlag == "2") {
			Intent intent = new Intent(ViewTimeSheet.this, ViewTimeSheet.class);
			startActivity(intent);
			finish();

		} else {
			Intent intent = new Intent(ViewTimeSheet.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();


		}

	}
}
