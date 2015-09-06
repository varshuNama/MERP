package com.mcerp.gts;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.main.R;
import com.mcerp.util.AppPreferences;

public class Gts_View_Training_Fragment extends Fragment {

	String[] myMonthlist;

	ArrayList<Integer> currentyear = new ArrayList<Integer>();

	Button view;
	TextView gts_view_training_activity_trainer_name,
			gts_view_training_activity_trainer_id,
			gts_view_training_activity_month_year_txt;

	AppPreferences prefs;

	String EmpCode, EmpId, Employee_name;

	Spinner monthSpinner, yearSpinner;

	ArrayAdapter adaptermonth, adapteryear;

	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.gts_view_training_activity,
				container, false);

		prefs = AppPreferences.getInstance(getActivity());
		EmpCode = prefs.getEmpCode();
		EmpId = prefs.getUserID();
		Employee_name = prefs.getEmpName();

		init();
		return rootView;

	}

	private void init() {

		gts_view_training_activity_trainer_name = (TextView) rootView
				.findViewById(R.id.gts_view_training_activity_trainer_name);
		gts_view_training_activity_trainer_id = (TextView) rootView
				.findViewById(R.id.gts_view_training_activity_trainer_id);
		gts_view_training_activity_month_year_txt = (TextView) rootView
				.findViewById(R.id.gts_view_training_activity_month_year_txt);
		monthSpinner = (Spinner) rootView
				.findViewById(R.id.gts_view_training_activity_month_sv);
		yearSpinner = (Spinner) rootView
				.findViewById(R.id.gts_view_training_activity_year_sv);

		myMonthlist = getResources().getStringArray(R.array.month_arrays);

		view = (Button) rootView
				.findViewById(R.id.gts_view_training_activity_view);

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String empid = EmpId.toString();

				String yyyy = yearSpinner.getSelectedItem().toString();
				//String mm = monthSpinner.getSelectedItem().toString();
				int mPos = monthSpinner.getSelectedItemPosition();
				String wordmonthSpinner = myMonthlist[mPos];
				/*int year = Calendar.getInstance().get(Calendar.YEAR);

				int month = Calendar.getInstance().get(Calendar.MONTH) + 1;*/
				/*if (Integer.parseInt(yyyy) > year || mPos + 1 > month) {
					Toast.makeText(
							getActivity(),
							"Month should not be greater then Current Month",
							Toast.LENGTH_SHORT).show();

				} else {*/
					Intent intent = new Intent(getActivity(),
							Gts_View_Training_Report.class);

					intent.putExtra("year", yyyy);
					intent.putExtra("month", wordmonthSpinner);

					intent.putExtra("empid", empid);

					startActivity(intent);
				/*}*/

			}
		});

		adaptermonth = ArrayAdapter
				.createFromResource(getActivity(), R.array.word_month_arrays,
						android.R.layout.simple_spinner_item);
		adaptermonth
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(adaptermonth);

		int currentmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		String currentmonthstringvalue = null;
		if (currentmonth < 10) {
			currentmonthstringvalue = "0" + currentmonth;
		}
		String[] arraydata = getResources()
				.getStringArray(R.array.month_arrays);
		if (!(currentmonth == 0)) {
			for (int i = 0; i <= arraydata.length; i++) {
				if (arraydata[i].equals(currentmonthstringvalue)) {
					monthSpinner.setSelection(i);
					break;
				}
			}

		}

		currentyear.add(2015);

		for (int i = 2015; i < Calendar.getInstance().get(Calendar.YEAR); i++) {

			currentyear.add(i + 1);

		}
		ArrayAdapter<Integer> adapteryear = new ArrayAdapter<Integer>(
				getActivity(), android.R.layout.simple_spinner_item,
				currentyear);

		adapteryear
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpinner.setAdapter(adapteryear);

		gts_view_training_activity_trainer_name.setText(Employee_name);
		gts_view_training_activity_trainer_id.setText(EmpCode);

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		AppPreferences mAppPreferences = AppPreferences
				.getInstance(getActivity());
		mAppPreferences
				.setScreen(com.mcerp.constant.AppConstants.GTSView);

	}
}
