package com.mcerp.projectedcosting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.ProjectedCostGetData;
import com.mcerp.util.AppPreferences;

public class Projected_New_Fragment extends Fragment {
	View rootView;
	AutoCompleteTextView spin_active, spin_closed;
	TextView text_month, text_year;
	RadioGroup rd;
	SweetAlertDialog Dialog, pDialog, dialog;
	Button submit_btn;
	ImageView close_btn_active, cloase_btn_closed;
	int flag;
	ConnectionDetector connection;
	AppPreferences prefs;
	String responseData, ProjectCode;
	List<String> strClosedProjectName, strActiveProjectName;
	ArrayList<ProjectedCostGetData> project_cost_array;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.projectcost_new_fragment,
				container, false);
		init();
		return rootView;
	}

	private void init() {

		spin_active = (AutoCompleteTextView) rootView
				.findViewById(R.id.spinnerActive);
		spin_closed = (AutoCompleteTextView) rootView
				.findViewById(R.id.spinnerClosed);
		close_btn_active = (ImageView) rootView
				.findViewById(R.id.cloase_btn_active);
		cloase_btn_closed = (ImageView) rootView
				.findViewById(R.id.close_btn_closed);

		text_month = (TextView) rootView.findViewById(R.id.text_cost_month);
		text_year = (TextView) rootView.findViewById(R.id.text_cost_year);
		submit_btn = (Button) rootView.findViewById(R.id.project_cost_submit);
		rd = (RadioGroup) rootView.findViewById(R.id.radioGroupProjectCost);
		spin_closed.setEnabled(false);
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);

		SimpleDateFormat month_date = new SimpleDateFormat("MMM");
		String monthname = month_date.format(calender.getTime());

		prefs = AppPreferences.getInstance(getActivity());
		connection = new ConnectionDetector(getActivity());

		text_month.setText(monthname);
		text_year.setText(year + "");
		Date cDate = new Date();
		final String fDate = new SimpleDateFormat("yyyyMM").format(cDate);
		new AsyncTaskProjectedCost().execute();

		rd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_active) {
					spin_active.setEnabled(true);
					spin_closed.setEnabled(false);
				} else if (checkedId == R.id.rd_closed) {
					spin_active.setEnabled(false);
					spin_closed.setEnabled(true);
				}

			}
		});
		close_btn_active.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				spin_active.setText("");

			}
		});
		cloase_btn_closed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				spin_closed.setText("");

			}
		});
		submit_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

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
							GetSheetData.class);
					intent.putExtra("ProjectCode", ProjectCode);
					intent.putExtra("MonthYear", fDate);
					startActivity(intent);
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
			project_cost_array = new ArrayList<ProjectedCostGetData>();
			strClosedProjectName = new ArrayList<String>();
			strActiveProjectName = new ArrayList<String>();
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

}
