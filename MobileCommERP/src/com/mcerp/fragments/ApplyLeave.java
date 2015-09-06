package com.mcerp.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.util.AppPreferences;

public class ApplyLeave extends Fragment implements OnClickListener {
	EditText datefrom, dateto, reason;
	String officetype = "H", leavetype = "H", halfleave = "SH", dateFrom,
			dateTo;;
	String correctformateFrom, correctformatTo, Reason;
	private int mYear, mMonth, mDay;
	SweetAlertDialog pDialog;
	View halfview, viewleaveTo;
	TextView halftext, leavetotext;
	LinearLayout linearhalf;

	AppPreferences prefs;
	ConnectionDetector connection;
	RadioGroup rd_office, rd_leavetype, rd_half;
	RadioButton rd_HO, rd_OP, rd_FullD, rd_HalfD, rd_FirstH, rd_SecondH;

	String Response = null;
	Button btn_proceed;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)

	{

		rootView = inflater.inflate(R.layout.activity_apply_leave, container,
				false);
		init();
		return rootView;
	}

	private void init() {
		prefs = AppPreferences.getInstance(getActivity());
		btn_proceed = (Button) rootView.findViewById(R.id.proceed);
		connection = new ConnectionDetector(getActivity());
		datefrom = (EditText) rootView.findViewById(R.id.dateFrom);
		dateto = (EditText) rootView.findViewById(R.id.dateTo);
		reason = (EditText) rootView.findViewById(R.id.reason);
		halftext = (TextView) rootView.findViewById(R.id.texthalf);
		linearhalf = (LinearLayout) rootView.findViewById(R.id.linearhalf);
		leavetotext = (TextView) rootView.findViewById(R.id.leaveToText);
		halfview = rootView.findViewById(R.id.viewhalf);
		viewleaveTo = rootView.findViewById(R.id.viewleaveto);
		rd_office = (RadioGroup) rootView
				.findViewById(R.id.radioGroupOfficeType);
		rd_leavetype = (RadioGroup) rootView
				.findViewById(R.id.radioGroupLeaveType);
		rd_half = (RadioGroup) rootView.findViewById(R.id.radioGroupHalfDay);
		rd_HO = (RadioButton) rootView.findViewById(R.id.rd_headoff);
		rd_OP = (RadioButton) rootView.findViewById(R.id.rd_onproject);
		rd_FirstH = (RadioButton) rootView.findViewById(R.id.rd_firsthalf);
		rd_SecondH = (RadioButton) rootView.findViewById(R.id.rd_secondhalf);
		rd_FullD = (RadioButton) rootView.findViewById(R.id.rd_fullday);
		rd_HalfD = (RadioButton) rootView.findViewById(R.id.rd_halfday);

		viewleaveTo.setVisibility(View.GONE);
		dateto.setVisibility(View.GONE);
		leavetotext.setVisibility(View.GONE);
		dateto.setEnabled(false);
		datefrom.setOnClickListener(this);
		dateto.setOnClickListener(this);
		reason.setOnClickListener(this);
		btn_proceed.setOnClickListener(this);
		rd_office.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_headoff) {

					officetype = "H";

				} else if (checkedId == R.id.rd_onproject) {

					officetype = "OTH";

				}

			}
		});
		rd_leavetype.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_fullday) {

					leavetype = "F";
					dateto.setVisibility(View.VISIBLE);
					dateto.setEnabled(true);
					leavetotext.setVisibility(View.VISIBLE);
					viewleaveTo.setVisibility(View.VISIBLE);
					halfview.setVisibility(View.GONE);
					halftext.setVisibility(View.GONE);
					linearhalf.setVisibility(View.GONE);

				} else if (checkedId == R.id.rd_halfday) {

					leavetype = "H";
					dateto.setEnabled(false);
					halfview.setVisibility(View.VISIBLE);
					halftext.setVisibility(View.VISIBLE);
					linearhalf.setVisibility(View.VISIBLE);
					viewleaveTo.setVisibility(View.GONE);
					dateto.setVisibility(View.GONE);
					leavetotext.setVisibility(View.GONE);

				}

			}
		});
		rd_half.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_firsthalf) {

					halfleave = "FH";

				} else if (checkedId == R.id.rd_secondhalf) {

					halfleave = "SH";

				}

			}
		});

	}

	@Override
	public void onClick(View v) {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.getDefault());
		switch (v.getId()) {
		case R.id.dateFrom:

			final Calendar c = Calendar.getInstance();

			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);

			c.getMaximum(mMonth);

			// Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(getActivity(),
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							// Display Selected date in textbox

							datefrom.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);

						}
					}, mYear, mMonth, mDay);
			Calendar minDate = Calendar.getInstance();
			try {
				minDate.setTime(formatter.parse(mDay + "." + mMonth + "."
						+ mYear));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DatePicker datePicker = dpd.getDatePicker();
			datePicker.setMinDate(minDate.getTimeInMillis());

			dpd.show();
			break;
		case R.id.dateTo:

			final Calendar c1 = Calendar.getInstance();

			mYear = c1.get(Calendar.YEAR);
			mMonth = c1.get(Calendar.MONTH);
			mDay = c1.get(Calendar.DAY_OF_MONTH);

			// Launch Date Picker Dialog
			DatePickerDialog dpdd = new DatePickerDialog(getActivity(),
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {

							dateto.setText(dayOfMonth + "-" + (monthOfYear + 1)
									+ "-" + year);

						}
					}, mYear, mMonth, mDay);
			Calendar MinDate = Calendar.getInstance();
			try {
				MinDate.setTime(formatter.parse(mDay + "." + mMonth + "."
						+ mYear));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DatePicker DatePicker = dpdd.getDatePicker();
			DatePicker.setMinDate(MinDate.getTimeInMillis());

			dpdd.show();
			break;
		case R.id.proceed:

			dateTo = dateto.getText().toString();
			dateFrom = datefrom.getText().toString();
			Reason = reason.getText().toString();
			if (dateto.isEnabled()) {
				if (dateTo.equals("") || dateFrom.equals("")
						|| Reason.equals("")) {

					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("All Enteries are necessary.")
							.show();

				} else {
					getData();
				}
			} else if (dateFrom.equals("") || Reason.equals("")) {
				new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("All Enteries are necessary.").show();

			} else {
				getData();
			}
			break;

		default:
			break;
		}

	}

	public class AsyncTaskAppleLeave extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new SweetAlertDialog(getActivity(),
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String Message = null, response = null;

			try {
				response = MethodSoap.getLeaveRequest(prefs.getUserID(),
						prefs.getEmpCode(), prefs.getEmpName(),
						prefs.getUserName(), officetype, leavetype, halfleave,
						correctformateFrom, correctformatTo, Reason);
				JSONObject jsonobj = new JSONObject(response);
				Message = jsonobj.getString("message");
				Response = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				pDialog.dismiss();
			}

			return Message;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {
					pDialog.dismiss();
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("Successfully")
							.setContentText(Response).show();
					datefrom.setText("");
					dateto.setText("");
					reason.setText("");
					rd_office.setSelected(false);
					rd_leavetype.setSelected(false);
					rd_half.setSelected(false);

				} else if (result.equals("fail")) {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Sorry...").setContentText(Response)
							.show();
					pDialog.dismiss();

				} else if (!connection.isConnectingToInternet()) {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Does not get Proper Response !")
							.show();
					pDialog.dismiss();
				}
			} catch (Exception e) {
				pDialog.dismiss();
			}
		}
	}

	public void getData() {
		String[] strdatefrom = dateFrom.split("-");
		if (strdatefrom[0].length() < 2) {
			strdatefrom[0] = "0" + strdatefrom[0];
		}
		if (strdatefrom[1].length() < 2) {
			strdatefrom[1] = "0" + strdatefrom[1];
		}
		correctformateFrom = strdatefrom[2] + strdatefrom[1] + strdatefrom[0];

		if (dateTo.equals("")) {
			dateto.setEnabled(false);
		}

		if (dateto.isEnabled()) {
			String[] strdateto = dateTo.split("-");
			if (strdateto[0].length() < 2) {
				strdateto[0] = "0" + strdateto[0];
			}
			if (strdateto[1].length() < 2) {
				strdateto[1] = "0" + strdateto[1];
			}
			correctformatTo = strdateto[2] + strdateto[1] + strdateto[0];
		} else {
			correctformatTo = correctformateFrom;
		}

		Log.d("Date From", correctformateFrom);
		Log.d("Date To", correctformatTo);
		Reason = reason.getText().toString();

		new AsyncTaskAppleLeave().execute("");

	}

}
