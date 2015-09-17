package com.mcerp.projectedcosting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.ProjectedCostGetData;
import com.mcerp.util.AppPreferences;

public class Project_Costing_Report_Fragment extends Fragment implements
		OnEditorActionListener, OnClickListener {
	View rootView;
	AutoCompleteTextView spin_active, spin_closed;
	String[] myMonthlist;
	RadioGroup rd;
	Spinner monthSpinner, yearSpinner;
	SweetAlertDialog Dialog, pDialog, dialog;
	Button submit_btn;
	ImageView close_btn_active, cloase_btn_closed;
	int flag;
	ConnectionDetector connection;
	AppPreferences prefs;
	ArrayList<Integer> currentyear = new ArrayList<Integer>();
	String fDate;
	String responseData, ProjectCode;
	String whichPage, active_closed_flag = "0";
	List<String> strClosedProjectName, strActiveProjectName;
	ArrayList<ProjectedCostGetData> project_cost_array;
	ArrayAdapter adaptermonth, adapteryear;
	String monthyearpassdata;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.projectcost_report_fragment,
				container, false);
		init();
		return rootView;
	}

	private void init() {
		monthSpinner = (Spinner) rootView.findViewById(R.id.cost_month_sv);
		yearSpinner = (Spinner) rootView.findViewById(R.id.cost_year_sv);
		spin_active = (AutoCompleteTextView) rootView
				.findViewById(R.id.spinnerActive);
		spin_closed = (AutoCompleteTextView) rootView
				.findViewById(R.id.spinnerClosed);
		close_btn_active = (ImageView) rootView
				.findViewById(R.id.cloase_btn_active);
		cloase_btn_closed = (ImageView) rootView
				.findViewById(R.id.close_btn_closed);

		submit_btn = (Button) rootView.findViewById(R.id.project_cost_submit);
		rd = (RadioGroup) rootView.findViewById(R.id.radioGroupProjectCost);
		spin_closed.setEnabled(false);
		submit_btn.setOnClickListener(this);
		cloase_btn_closed.setOnClickListener(this);
		close_btn_active.setOnClickListener(this);
		spin_active.setOnEditorActionListener(this);
		spin_closed.setOnEditorActionListener(this);
		new AsyncTaskProjectedCost().execute();
		adaptermonth = ArrayAdapter
				.createFromResource(getActivity(), R.array.word_month_arrays,
						android.R.layout.simple_spinner_item);
		adaptermonth
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		monthSpinner.setAdapter(adaptermonth);
		
		monthSpinner.setSelection(Calendar.getInstance().get(Calendar.MONTH));

		for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 2008; i--) {

			currentyear.add(i);

		}
		ArrayAdapter<Integer> adapteryear = new ArrayAdapter<Integer>(
				getActivity(), android.R.layout.simple_spinner_item,
				currentyear);

		adapteryear
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		yearSpinner.setAdapter(adapteryear);

		rd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_active) {
					spin_active.setEnabled(true);
					spin_closed.setEnabled(false);
					spin_closed.setText("");
					active_closed_flag = "0";
				} else if (checkedId == R.id.rd_closed) {
					spin_active.setEnabled(false);
					spin_active.setText("");
					spin_closed.setEnabled(true);
					active_closed_flag = "1";
				}

			}
		});

	}

	/*********************************** Poject Costing Get Data ***********************************/
	public class AsyncTaskProjectedCost extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new SweetAlertDialog(getActivity(),
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
			project_cost_array = new ArrayList<ProjectedCostGetData>();
			strClosedProjectName = new ArrayList<String>();
			strActiveProjectName = new ArrayList<String>();

			ProjectedCostGetData data = new ProjectedCostGetData();
			data.setProjectName("ALL");
			data.setProjectCode("%");

			project_cost_array.add(data);
			strActiveProjectName.add("ALL");
			strClosedProjectName.add("ALL");

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {

				response = MethodSoap.GetDataForPojectCosting();

				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responseData = jsonobj.getString("DataArr");
				if (message.equals("success")) {
					JSONArray jsonarray = new JSONArray(responseData);

					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject obj = jsonarray.getJSONObject(i);
						ProjectedCostGetData ModelData = new ProjectedCostGetData();
						ModelData.setProjectCode(obj.getString("Projcode"));
						ModelData.setProjectName(obj.getString("ProjName"));

						ModelData.setClosed(obj.getString("Closed"));

						if (obj.getString("Closed").equals("0")) {
							strActiveProjectName.add(obj.getString("ProjName"));
						} else {
							strClosedProjectName.add(obj.getString("ProjName"));
						}
						project_cost_array.add(ModelData);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				message = "";

			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {
					if (strActiveProjectName != null
							&& strClosedProjectName != null) {
						spin_active.setAdapter(new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_dropdown_item_1line,
								strActiveProjectName));

						spin_active.setThreshold(1);
						spin_closed.setAdapter(new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_dropdown_item_1line,
								strClosedProjectName));

						spin_closed.setThreshold(1);

					}
					pDialog.dismiss();

				} else {

					pDialog.dismiss();
					if (flag == 1) {
						flag = 0;

						dialog = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Oops")
								.setContentText("Server Connection Problem!")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												Intent intent = new Intent(
														getActivity(),
														NavigationActivity.class);
												getActivity().startActivity(
														intent);
												((Activity) getActivity())
														.finish();

												sDialog.dismiss();
											}
										});
						dialog.show();

					} else {
						dialog = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Oops")
								.setContentText("Does not get Proper Response.")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												Intent intent = new Intent(
														getActivity(),
														NavigationActivity.class);
												getActivity().startActivity(
														intent);
												((Activity) getActivity())
														.finish();

												sDialog.dismiss();
											}
										});
						dialog.show();

					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				pDialog.dismiss();
			}

		}
	}

	void getSpinValue() {
		if (project_cost_array.size() > 0 && project_cost_array != null) {
			for (int i = 0; i < project_cost_array.size(); i++) {
				if (spin_active.isEnabled()) {
					if (spin_active.getText().toString().trim()
							.equals(project_cost_array.get(i).getProjectName())) {
						ProjectCode = project_cost_array.get(i)
								.getProjectCode();
						break;
					}
				} else if (spin_closed.isEnabled()) {
					if (spin_closed.getText().toString().trim()
							.equals(project_cost_array.get(i).getProjectName())) {
						ProjectCode = project_cost_array.get(i)
								.getProjectCode();
						break;
					}
				}
			}
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			submit_btn.performClick();

		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cloase_btn_active:
			spin_active.setText("");
			break;
		case R.id.close_btn_closed:

			spin_closed.setText("");
			break;
		case R.id.project_cost_submit:
			String mm,
			yy;
			int mmm;
			yy = yearSpinner.getSelectedItem().toString();
			mmm = monthSpinner.getSelectedItemPosition();
			if ((mmm + 1) < 10) {
				mm = "0" + (mmm + 1);
			} else {
				mm = (mmm + 1) + "";
			}
			monthyearpassdata = yy + mm;
			if (spin_active.isEnabled()
					&& spin_active.getText().toString().trim().equals("")) {
				Toast.makeText(getActivity(),
						"Please select one Active Project from list.",
						Toast.LENGTH_LONG).show();

			} else if (spin_closed.isEnabled()
					&& spin_closed.getText().toString().trim().equals("")) {
				Toast.makeText(getActivity(),
						"Please select one Closed Project from list.",
						Toast.LENGTH_LONG).show();

			} else {

				getSpinValue();
				Intent intent = new Intent(getActivity(),
						Project_Costing_Report.class);
				intent.putExtra("ProjectCode", ProjectCode);
				intent.putExtra("MonthYear", monthyearpassdata);
				intent.putExtra("ActiveClosedFlag", active_closed_flag);
				startActivity(intent);

			}

			break;
		default:
			break;
		}

	}
}
