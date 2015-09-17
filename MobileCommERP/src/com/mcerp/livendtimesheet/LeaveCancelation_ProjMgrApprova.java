package com.mcerp.livendtimesheet;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.adapter.LeaveCancelation_ProjMgrApprova_Adapter;
import com.mcerp.asyncheck.AsyncTask_LeaveCancelation_ProjMgrApprova;
import com.mcerp.asyncheck.AsynctasksubmitAcceptTraining;
import com.mcerp.asyncheck.AsynctasksubmitLeaveCancelation_ProjMgrApprova;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.gts.Accept_Training;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.main.R.id;
import com.mcerp.main.R.layout;
import com.mcerp.model.LeaveCancelation_ProjMgrApprova_Model;
import com.mcerp.model.TrainingModelData;
import com.mcerp.util.AppPreferences;

public class LeaveCancelation_ProjMgrApprova extends Activity implements
		OnClickListener {

	String responseData, message, empId;

	TextView noRecordLeaveCancelation_ProjMgrApprova;

	ConnectionDetector connectiondetector;
	AppPreferences prefs;
	LeaveCancelation_ProjMgrApprova_Adapter adapter;

	ListView LeaveCancelation_ProjMgrApprova_list;

	LinearLayout LeaveCancelation_ProjMgrApprova_submit_btn,
			LeaveCancelation_ProjMgrApprova_backbtn,
			LeaveCancelation_ProjMgrApprova_hdr;

	ArrayList<String> arrayLeaveId;
	ArrayList<String> arrayApprovalFlag;
	ArrayList<String> arrayApproveReason;
	ArrayList<String> arrayReqEmpName;
	ArrayList<String> arrayReqEmpEmail;
	ArrayList<String> arrayLeaveDate;
	ArrayList<String> arrayLeaveDay;
	ArrayList<String> arrayProjectName;
	ArrayList<String> arrayLeaveMonthYear;
	ArrayList<String> arrayReqEmpId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leave_cancelation_projmgrapproval);
		init();
		new AsyncTask_LeaveCancelation_ProjMgrApprova(
				LeaveCancelation_ProjMgrApprova.this).execute();

	}

	private void init() {
		LeaveCancelation_ProjMgrApprova_backbtn = (LinearLayout) findViewById(R.id.LeaveCancelation_ProjMgrApprova_backbtn);
		noRecordLeaveCancelation_ProjMgrApprova = (TextView) findViewById(R.id.noRecordLeaveCancelation_ProjMgrApprova);
		LeaveCancelation_ProjMgrApprova_submit_btn = (LinearLayout) findViewById(R.id.LeaveCancelation_ProjMgrApprova_submit_btn);
		LeaveCancelation_ProjMgrApprova_submit_btn.setOnClickListener(this);

		LeaveCancelation_ProjMgrApprova_backbtn
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(
								LeaveCancelation_ProjMgrApprova.this,
								NavigationActivity.class);
						startActivity(intent);
						finish();
					}
				});

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.LeaveCancelation_ProjMgrApprova_submit_btn:
			prefs = AppPreferences.getInstance(this);
			arrayLeaveId = new ArrayList<String>();
			arrayApprovalFlag = new ArrayList<String>();
			arrayApproveReason = new ArrayList<String>();
			arrayReqEmpName = new ArrayList<String>();
			arrayReqEmpEmail = new ArrayList<String>();
			arrayLeaveDate = new ArrayList<String>();
			arrayLeaveDay = new ArrayList<String>();
			arrayProjectName = new ArrayList<String>();
			arrayLeaveMonthYear = new ArrayList<String>();
			arrayReqEmpId = new ArrayList<String>();

			if (adapter != null) {

				for (Iterator<LeaveCancelation_ProjMgrApprova_Model> i = (adapter
						.getList()).iterator(); i.hasNext();) {
					LeaveCancelation_ProjMgrApprova_Model ab = i.next();

					if (!ab.getCheckboxstatus() == false) {

						arrayLeaveId.add(ab.getId());

						arrayApprovalFlag.add(ab.getYes_no_status());
						arrayApproveReason.add(ab.getCancelRemark());
						arrayReqEmpName.add(ab.getEmpName());
						arrayReqEmpEmail.add(ab.getEmail());
						arrayReqEmpId.add(ab.getReqEmpId());
						arrayLeaveDate.add(ab.getLeaveDate());
						arrayLeaveDay.add(ab.getLeaveDay());
						arrayProjectName.add(ab.getProjName());
						arrayLeaveMonthYear.add(ab.getLeaveDateMnthYr());

					}

				}
				if (arrayLeaveId.size() == 0) {
					Toast.makeText(LeaveCancelation_ProjMgrApprova.this,
							"Please Check Aleast one Row", Toast.LENGTH_SHORT)
							.show();
				} else {
					new AsynctasksubmitLeaveCancelation_ProjMgrApprova(
							LeaveCancelation_ProjMgrApprova.this,
							prefs.getUserID(), prefs.getEmpName(),
							prefs.getUserName(), arrayLeaveId,
							arrayApprovalFlag, arrayApproveReason,
							arrayReqEmpId, arrayReqEmpName, arrayReqEmpEmail,
							arrayLeaveDate, arrayLeaveDay, arrayProjectName,
							arrayLeaveMonthYear).execute();
				}

			}

			break;

		default:
			break;

		}

	}

	public void leavecancelation_projMgrapproval(
			LeaveCancelation_ProjMgrApprova act,
			ArrayList<LeaveCancelation_ProjMgrApprova_Model> leavecancelation_projMgrapprova_model_list) {
		LeaveCancelation_ProjMgrApprova_list = (ListView) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_list);
		LeaveCancelation_ProjMgrApprova_backbtn = (LinearLayout) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_backbtn);
		LeaveCancelation_ProjMgrApprova_submit_btn = (LinearLayout) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_submit_btn);
		noRecordLeaveCancelation_ProjMgrApprova = (TextView) findViewById(R.id.noRecordLeaveCancelation_ProjMgrApprova);

		noRecordLeaveCancelation_ProjMgrApprova.setVisibility(View.GONE);
		LeaveCancelation_ProjMgrApprova_submit_btn.setVisibility(View.VISIBLE);
		adapter = new LeaveCancelation_ProjMgrApprova_Adapter(act,
				leavecancelation_projMgrapprova_model_list);
		LeaveCancelation_ProjMgrApprova_list.setAdapter(adapter);

	}

	public void leavecancelation_projMgrapprova_fail(
			LeaveCancelation_ProjMgrApprova act, String response) {
		LeaveCancelation_ProjMgrApprova_list = (ListView) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_list);

		LeaveCancelation_ProjMgrApprova_hdr = (LinearLayout) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_hdr);
		LeaveCancelation_ProjMgrApprova_submit_btn = (LinearLayout) act
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_submit_btn);
		noRecordLeaveCancelation_ProjMgrApprova = (TextView) findViewById(R.id.noRecordLeaveCancelation_ProjMgrApprova);
		noRecordLeaveCancelation_ProjMgrApprova.setVisibility(View.VISIBLE);
		noRecordLeaveCancelation_ProjMgrApprova.setText(response);
		LeaveCancelation_ProjMgrApprova_list.setVisibility(View.GONE);
		LeaveCancelation_ProjMgrApprova_hdr.setVisibility(View.GONE);

		LeaveCancelation_ProjMgrApprova_submit_btn.setVisibility(View.GONE);

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(LeaveCancelation_ProjMgrApprova.this,
				NavigationActivity.class);
		startActivity(intent);
		finish();
	}

}
