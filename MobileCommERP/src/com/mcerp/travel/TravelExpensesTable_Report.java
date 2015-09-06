package com.mcerp.travel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mcerp.adapter.TravelExpensesReport_Adapter;
import com.mcerp.asyncheck.AsyncTravelExpensesTableReport;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.TravelExpensesReport_Model;

public class TravelExpensesTable_Report extends Activity {
	android.support.v4.app.Fragment fragment = null;

	String responseData, message, transtype;
	ConnectionDetector connectiondetector;

	TravelExpensesReport_Adapter adapter;

	ListView travel_expenses_table_report_list;

	TextView noRecord_found_travel_expenses_table_report;
	LinearLayout travel_expenses_report_linearList,
			travel_expenses_report_backbtn, expencetablereportrow_header;

	String year, month, travelmode, empid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityexpenses_table_report);
		travel_expenses_report_backbtn = (LinearLayout) findViewById(R.id.travel_expenses_report_backbtn);
		travel_expenses_report_backbtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						finish();

					}
				});

		Intent intent = getIntent();

		Bundle bundle = intent.getExtras();

		if (bundle != null) {
			year = bundle.getString("year");

			month = bundle.getString("month");

			travelmode = bundle.getString("mode");
			empid = bundle.getString("empid");
		}

		new AsyncTravelExpensesTableReport(TravelExpensesTable_Report.this,
				empid, year, month, travelmode).execute();

	}

	public void gettravel_expenses_report(
			TravelExpensesTable_Report act,
			ArrayList<TravelExpensesReport_Model> travelexpensesreport_model_list) {

		travel_expenses_table_report_list = (ListView) act
				.findViewById(R.id.travel_expenses_table_report_list);
		adapter = new TravelExpensesReport_Adapter(act,
				travelexpensesreport_model_list);
		travel_expenses_table_report_list.setAdapter(adapter);
		noRecord_found_travel_expenses_table_report = (TextView) act
				.findViewById(R.id.noRecord_found_travel_expenses_table_report);
		noRecord_found_travel_expenses_table_report.setVisibility(View.GONE);

	}

	public void gettravel_expenses_report_fail(TravelExpensesTable_Report act,String response) {
		noRecord_found_travel_expenses_table_report = (TextView) act
				.findViewById(R.id.noRecord_found_travel_expenses_table_report);
		travel_expenses_table_report_list = (ListView) act
				.findViewById(R.id.travel_expenses_table_report_list);
		travel_expenses_table_report_list.setVisibility(View.GONE);
		travel_expenses_report_linearList = (LinearLayout) findViewById(R.id.travel_expenses_report_linearList);

		expencetablereportrow_header = (LinearLayout) findViewById(R.id.expencetablereportrow_header);
		expencetablereportrow_header.setVisibility(View.GONE);
		travel_expenses_report_linearList.setVisibility(View.VISIBLE);

		noRecord_found_travel_expenses_table_report.setVisibility(View.VISIBLE);
		noRecord_found_travel_expenses_table_report.setText(response);

	}

}
