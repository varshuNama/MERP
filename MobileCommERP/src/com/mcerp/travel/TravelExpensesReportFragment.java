package com.mcerp.travel;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.main.R;
import com.mcerp.travel.TravelExpensesTable_Report;
import com.mcerp.util.AppPreferences;

public class TravelExpensesReportFragment extends Fragment {
	String[] myList;
	String[] myMonthlist;

	ArrayList<Integer> currentyear = new ArrayList<Integer>();
	int currentmonth;
	Button view;
	TextView travel_month_year_txt, travel_emp_txt, travel_transport_mode_txt,
			travel_view_type_txt, travel_emp_txtview;
	RadioGroup travel_viewtype_rg;
	RadioButton travel_viewtype_summary, travel_viewtype_detail;

	AppPreferences prefs;

	Spinner monthSpinner, yearSpinner, travel_mode;

	SweetAlertDialog pDialog;

	String EmpCode, EmpId, Employee_name;

	ArrayAdapter adaptermonth, adapteryear, adaptertransport;

	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)

	{

		rootView = inflater.inflate(R.layout.activity_travel_expenses,
				container, false);
		prefs = AppPreferences.getInstance(getActivity());
		EmpCode = prefs.getEmpCode();
		EmpId = prefs.getUserID();
		Employee_name = prefs.getEmpName();
		init();
		return rootView;
	}

	private void init() {
		travel_month_year_txt = (TextView) rootView
				.findViewById(R.id.travel_month_year_txt);
		travel_emp_txt = (TextView) rootView.findViewById(R.id.travel_emp_txt);
		travel_transport_mode_txt = (TextView) rootView
				.findViewById(R.id.travel_transport_mode_txt);

		travel_emp_txtview = (TextView) rootView
				.findViewById(R.id.travel_emp_txtview);
		travel_emp_txtview.setText(Employee_name);

		monthSpinner = (Spinner) rootView.findViewById(R.id.travel_month_sv);

		yearSpinner = (Spinner) rootView.findViewById(R.id.travel_year_sv);
		myList = getResources().getStringArray(
				R.array.Send_transport_mode_array);

		myMonthlist = getResources().getStringArray(R.array.month_arrays);

		view = (Button) rootView.findViewById(R.id.travel_view);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// String s=yearSpinner.getTag().toString();
				// String empid = travel_emp_txtview.getText().toString();

				String empid = EmpId.toString();

				String yyyy = yearSpinner.getSelectedItem().toString();
				String mm = monthSpinner.getSelectedItem().toString();
				int mPos = monthSpinner.getSelectedItemPosition();
				String wordmonthSpinner = myMonthlist[mPos];

				String mode = travel_mode.getSelectedItem().toString();
				int Pos = travel_mode.getSelectedItemPosition();
				String travelmode = myList[Pos];
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = currentmonth;
				if (Integer.parseInt(yyyy) > year || mPos + 1 > month) {
					Toast.makeText(
							getActivity(),
							"Month should not be greater then Current Month",
							Toast.LENGTH_SHORT).show();

				} else {
					Intent intent = new Intent(getActivity(),
							TravelExpensesTable_Report.class);

					intent.putExtra("year", yyyy);
					intent.putExtra("month", wordmonthSpinner);
					intent.putExtra("mode", travelmode);
					intent.putExtra("empid", empid);

					startActivity(intent);
				}

			}
		});

		travel_mode = (Spinner) rootView
				.findViewById(R.id.travel_transport_mode_sv);
		adaptermonth = ArrayAdapter
				.createFromResource(getActivity(), R.array.word_month_arrays,
						android.R.layout.simple_spinner_item);
		adaptermonth
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(adaptermonth);

		currentmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
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

		adaptertransport = ArrayAdapter.createFromResource(getActivity(),
				R.array.transport_mode_array,
				android.R.layout.simple_spinner_item);
		adaptertransport
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		travel_mode.setAdapter(adaptertransport);

	}

}
