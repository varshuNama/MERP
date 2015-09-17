package com.mcerp.projectedcosting;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.AsyncTaskERPProjectCostReportBindGrid;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.ProjectCostGetSheetModel;
import com.mcerp.model.ProjectCostReortModel;
import com.mcerp.util.AppPreferences;

public class Project_Costing_Report extends Activity {
	ListView getlist;

	SweetAlertDialog pDialog;
	int flag = 0;

	Project_Cost_Report_Adapter adapter;

	String responsesubmitdata;

	ConnectionDetector connection;
	AppPreferences prefs;
	LinearLayout project_cost_report_back;
	String project_code, month_year_date, activeClosedFlag;
	SweetAlertDialog Dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_cost_report);
		init();
	}

	private void init() {
		Dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		getlist = (ListView) findViewById(R.id.list_project_cost_report);

		project_cost_report_back=(LinearLayout) findViewById(R.id.project_cost_report_back);

		connection = new ConnectionDetector(this);
		pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		prefs = AppPreferences.getInstance(this);
		project_code = getIntent().getExtras().getString("ProjectCode");
		month_year_date = getIntent().getExtras().getString("MonthYear");
		activeClosedFlag = getIntent().getExtras()
				.getString("ActiveClosedFlag");
		project_cost_report_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		new AsyncTaskERPProjectCostReportBindGrid(Project_Costing_Report.this,
				Dialog, project_code, month_year_date, activeClosedFlag, "1")
				.execute();

	}

	public void getdataForProjectCostReport(Project_Costing_Report act,
			ArrayList<ProjectCostReortModel> arraygetdata) {
		
		 adapter = new Project_Cost_Report_Adapter(act, arraygetdata);
		  getlist.setAdapter(adapter);
		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
