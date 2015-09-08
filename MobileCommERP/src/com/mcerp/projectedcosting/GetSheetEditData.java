package com.mcerp.projectedcosting;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.AsyncTaskProjectCostEditGetSheet;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.ProjectCostEditSheetModel;
import com.mcerp.model.ProjectCostGetSheetModel;
import com.mcerp.util.AppPreferences;

public class GetSheetEditData extends Activity implements OnClickListener {
	ListView getlist;
	TextView norecord;
	SweetAlertDialog pDialog;
	int flag = 0;
	LinearLayout header;
	ArrayList<ProjectCostGetSheetModel> array_list;
	Project_Cost_Get_Sheet_Edit_Adapter adapter;
	LinearLayout project_cost_back;
	String responsesubmitdata;
	ArrayList<String> arraySheetId, arrayResourceCode, arrayQty,arrayUnitprice, arrayTotal;
	ConnectionDetector connection;
	AppPreferences prefs;
	String project_code, month_year_date;
	SweetAlertDialog Dialog;
	String active_closed_flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_sheet_edit_data);
		init();
	}

	private void init() {
		Dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
		getlist = (ListView) findViewById(R.id.list_project_cost_edit_sheet);
		norecord = (TextView) findViewById(R.id.noRecordprojectprojectcost);
		project_cost_back = (LinearLayout) findViewById(R.id.project_cost_back);
		project_cost_back.setOnClickListener(this);
		connection = new ConnectionDetector(this);
		pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
		prefs = AppPreferences.getInstance(this);
		active_closed_flag = getIntent().getExtras().getString("ActiveClosedFlag");
		project_code = getIntent().getExtras().getString("ProjectCode");
		month_year_date = getIntent().getExtras().getString("MonthYear");

		new AsyncTaskProjectCostEditGetSheet(GetSheetEditData.this, Dialog,project_code, month_year_date, active_closed_flag).execute();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.project_cost_back:
			finish();
			break;
		}

	}

	public void sendDataToProjectCostEdit(GetSheetEditData act,
			ArrayList<ProjectCostEditSheetModel> arraygetdata) {
		adapter = new Project_Cost_Get_Sheet_Edit_Adapter(act, arraygetdata);
		getlist.setAdapter(adapter);
	}

}
