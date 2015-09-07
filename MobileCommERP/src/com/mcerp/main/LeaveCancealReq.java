package com.mcerp.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.mcerp.adapter.LeaveCancealReq_Adapter;
import com.mcerp.asyncheck.AsynctaskLeaveCancelReq;
import com.mcerp.asyncheck.Asynctask_SubmitCancel_Leave_Req;
import com.mcerp.asyncheck.AsynctasksubmitAcceptTraining;
import com.mcerp.gts.Accept_Training;
import com.mcerp.main.R.layout;
import com.mcerp.model.LeaveCancealReqModelData;
import com.mcerp.model.TrainingModelData;
import com.mcerp.util.AppPreferences;

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
import android.widget.Toast;

public class LeaveCancealReq extends Activity implements OnClickListener {
	AppPreferences prefs;
	//String[] myMonthlist;
	//Spinner monthSpinner;
//	ArrayAdapter adaptermonth;
	Button getbtn;
	LinearLayout backbutton, leave_cancel_submit_btn,
			leave_cancel_textheader, leave_cancel_hdr;
	ListView listview;
	TextView leave_cancel_req, leave_cancel_textrecordnofound;
	LeaveCancealReq_Adapter adapter;
	ArrayList<String> arrayLeaveID;
	ArrayList<String> arrayCancelRemark;
	ArrayList<String> arrayPmName;
	ArrayList<String> arrayPmEmail;
	ArrayList<String> arrayLeaveDate;
	ArrayList<String> arrayLeaveDay;
	ArrayList<String> arrayProjName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(layout.activity_leave_canceal_request);
		new AsynctaskLeaveCancelReq(LeaveCancealReq.this).execute();
		init();
	}

	private void init() {
		//monthSpinner = (Spinner) findViewById(R.id.spinnermonth);

	//	myMonthlist = getResources().getStringArray(R.array.month_arrays);
	//	getbtn = (Button) findViewById(R.id.getbtn);
		backbutton = (LinearLayout) findViewById(R.id.leave_cancel_backbtn);
		leave_cancel_submit_btn = (LinearLayout) findViewById(R.id.leave_cancel_submit_btn);
		//leave_cancelation_header = (LinearLayout) findViewById(R.id.leave_cancelation_header);
		listview = (ListView) findViewById(R.id.listViewcancelleavereq);
		leave_cancel_textheader = (LinearLayout) findViewById(R.id.leave_cancel_textheader);
		backbutton.setOnClickListener(this);
		leave_cancel_submit_btn.setOnClickListener(this);
		//leave_cancelation_header.setOnClickListener(this);
		//getbtn.setOnClickListener(this);

		/*adaptermonth = ArrayAdapter
				.createFromResource(LeaveCancealReq.this,
						R.array.word_month_arrays,
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
		}*/

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.leave_cancel_backbtn:
			Intent intent = new Intent(LeaveCancealReq.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;
		/*case R.id.getbtn:
			String mm = monthSpinner.getSelectedItem().toString();
			int mPos = monthSpinner.getSelectedItemPosition();
			String wordmonthSpinner = myMonthlist[mPos];
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String yyyy = null;
			yyyy = "" + year;

			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			if (mPos + 1 > month) {
				Toast.makeText(LeaveCancealReq.this,
						"Month should not be greater then Current Month",
						Toast.LENGTH_SHORT).show();

			} else {

				new AsynctaskLeaveCancelReq(LeaveCancealReq.this).execute();

			}
			break;*/
		case R.id.leave_cancel_submit_btn:
			prefs = AppPreferences.getInstance(this);
			arrayLeaveID = new ArrayList<String>();
			arrayCancelRemark = new ArrayList<String>();

			arrayPmName = new ArrayList<String>();
			arrayPmEmail = new ArrayList<String>();
			arrayLeaveDate = new ArrayList<String>();
			arrayLeaveDay = new ArrayList<String>();
			arrayProjName = new ArrayList<String>();

			if (adapter != null) {

				for (Iterator<LeaveCancealReqModelData> i = (adapter.getList())
						.iterator(); i.hasNext();) {
					LeaveCancealReqModelData ab = i.next();

					if (!ab.getLeave_cancel_checkbox() == false) {
						if (!ab.getLeavecancelReqRemarks().trim().equals("")) {
							arrayLeaveID.add(ab.getId());
							arrayCancelRemark.add(ab.getLeavecancelReqRemarks());
							arrayPmName.add(ab.getProjMgrName());
							arrayPmEmail.add(ab.getProjMgrEmail());
							arrayLeaveDate.add(ab.getLeaveDate());
							arrayLeaveDay.add(ab.getLeaveDay());
							arrayProjName.add(ab.getProjname());

						} else {
							Toast.makeText(LeaveCancealReq.this,
									"Remarks should not be blank.",
									Toast.LENGTH_LONG).show();
							break;
						}
					}

				}
				if (arrayLeaveID.size() == 0) {
					Toast.makeText(LeaveCancealReq.this,
							"Please Check Aleast one Row", Toast.LENGTH_SHORT)
							.show();
				} else {
					new Asynctask_SubmitCancel_Leave_Req(LeaveCancealReq.this,
							arrayLeaveID, arrayCancelRemark,
							prefs.getUserName(), prefs.getEmpName(),
							prefs.getEmpCode(), arrayPmName, arrayPmEmail,
							arrayLeaveDate, arrayLeaveDay, arrayProjName)
							.execute();
				}

			}

			break;

		default:
			break;

		}

	}

	public void getleavecancel_list(LeaveCancealReq act,
			ArrayList<LeaveCancealReqModelData> leavecancel_model_list) {
		adapter = new LeaveCancealReq_Adapter(act, leavecancel_model_list);
		listview.setAdapter(adapter);

	}

	public void getleavecancel_listfor_fail(LeaveCancealReq act, String response) {

		listview = (ListView) act.findViewById(R.id.listViewcancelleavereq);

		/*leave_cancelation_header = (LinearLayout) act
				.findViewById(R.id.leave_cancelation_header);*/
		leave_cancel_submit_btn = (LinearLayout) act
				.findViewById(R.id.leave_cancel_submit_btn);
		leave_cancel_textrecordnofound = (TextView) findViewById(R.id.leave_cancel_textrecordnofound);
		leave_cancel_textrecordnofound.setVisibility(View.VISIBLE);
		leave_cancel_textrecordnofound.setText(response);
	//	leave_cancelation_header.setVisibility(View.GONE);

		leave_cancel_submit_btn.setVisibility(View.GONE);
		leave_cancel_hdr = (LinearLayout) act
				.findViewById(R.id.leave_cancel_hdr);
		leave_cancel_hdr.setVisibility(View.GONE);

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(LeaveCancealReq.this,
				NavigationActivity.class);
		startActivity(intent);
		finish();
	}

}
