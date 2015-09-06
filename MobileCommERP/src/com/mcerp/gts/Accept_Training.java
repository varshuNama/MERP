package com.mcerp.gts;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.adapter.Accept_TrainingDataAdapter;
import com.mcerp.asyncheck.AcceptTrainingAsktask;
import com.mcerp.asyncheck.AsynctasksubmitAcceptTraining;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.TrainingModelData;
import com.mcerp.util.AppPreferences;

public class Accept_Training extends Activity implements OnClickListener {

	String responseData, message, empId;

	TextView noRecordaccept_training_txt;

	ConnectionDetector connectiondetector;
	AppPreferences prefs;
	Accept_TrainingDataAdapter adapter;

	ListView accet_train_list;

	LinearLayout accept_traing_submit_btn, accept_traing_backbtn,
			accept_traing_linearlist, acceptdetailhdr;

	ArrayList<String> ArrayListTsId;
	ArrayList<String> ArrayListRemarks;
	ArrayList<String> ArrayListApprove;
	ArrayList<String> ArrayListTraining;
	ArrayList<String> ArrayListLocation;
	ArrayList<String> ArrayListCustomer;
	ArrayList<String> ArrayListTrainingStart;
	ArrayList<String> ArrayListTrainingEnd;
	ArrayList<String> ArrayListNoOfDays;
	ArrayList<String> ArrayListTravelStart;
	ArrayList<String> ArrayListTravelEnd;
	ArrayList<String> ArrayListDA;
	ArrayList<String> ArrayListEmailid;
	ArrayList<String> ArrayListEmailTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_training);
		init();
		new AcceptTrainingAsktask(Accept_Training.this).execute();

	}

	private void init() {
		accept_traing_backbtn = (LinearLayout) findViewById(R.id.accept_traing_backbtn);
		noRecordaccept_training_txt = (TextView) findViewById(R.id.noRecordaccept_training_txt);
		accept_traing_backbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});


	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.accept_traing_submit_btn:
			prefs = AppPreferences.getInstance(this);
			ArrayListTsId = new ArrayList<String>();
			ArrayListRemarks = new ArrayList<String>();
			ArrayListApprove = new ArrayList<String>();
			ArrayListTraining = new ArrayList<String>();
			ArrayListLocation = new ArrayList<String>();
			ArrayListCustomer = new ArrayList<String>();
			ArrayListTrainingStart = new ArrayList<String>();
			ArrayListTrainingEnd = new ArrayList<String>();
			ArrayListNoOfDays = new ArrayList<String>();
			ArrayListTravelStart = new ArrayList<String>();
			ArrayListTravelEnd = new ArrayList<String>();
			ArrayListDA = new ArrayList<String>();
			ArrayListEmailid = new ArrayList<String>();
			ArrayListEmailTo = new ArrayList<String>();

			if (adapter != null) {

				for (Iterator<TrainingModelData> i = (adapter.getList())
						.iterator(); i.hasNext();) {
					TrainingModelData ab = i.next();

					if (!ab.getCheckboxstatus() == false) {
						if (!ab.getRemarks().trim().equals("")) {
							ArrayListTsId.add(ab.getId());
							ArrayListRemarks.add(ab.getRemarks());
							ArrayListApprove.add(ab.getYes_no_status());
							ArrayListTraining.add(ab.getTraining());
							ArrayListLocation.add(ab.getLocation());
							ArrayListCustomer.add(ab.getCustomer());
							ArrayListTrainingStart.add(ab.getTrainingStart());
							ArrayListTrainingEnd.add(ab.getTrainingEnd());
							ArrayListNoOfDays.add(ab.getNoOfDays());
							ArrayListTravelStart.add(ab.getTravelStart());
							ArrayListTravelEnd.add(ab.getTravelEnd());
							ArrayListDA.add(ab.getDA());
							ArrayListEmailTo.add(ab.getResquestemailid());
						} else {
							Toast.makeText(Accept_Training.this,
									"Remarks should not be blank.",
									Toast.LENGTH_LONG).show();
							break;
						}
					}

				}
				if (ArrayListTsId.size() == 0) {
					Toast.makeText(Accept_Training.this,
							"Please Check Aleast one Row", Toast.LENGTH_SHORT)
							.show();
				} else {
					new AsynctasksubmitAcceptTraining(Accept_Training.this,
							ArrayListTsId, ArrayListRemarks, ArrayListApprove,
							prefs.getEmpName(), prefs.getUserName(),
							ArrayListTraining, ArrayListLocation,
							ArrayListCustomer, ArrayListTrainingStart,
							ArrayListTrainingEnd, ArrayListNoOfDays,
							ArrayListTravelStart, ArrayListTravelEnd,
							ArrayListDA, ArrayListEmailTo).execute();
				}

			}

			break;

		default:
			break;

		}

	}

	public void getaccept_traing_list_for_approvel(Accept_Training act,
			ArrayList<TrainingModelData> accepttraing_model_list) {
		accet_train_list = (ListView) act.findViewById(R.id.accept_trainig_list);
		accept_traing_backbtn = (LinearLayout) act.findViewById(R.id.accept_traing_backbtn);
		accept_traing_submit_btn = (LinearLayout) act.findViewById(R.id.accept_traing_submit_btn);
		noRecordaccept_training_txt = (TextView) findViewById(R.id.noRecordaccept_training_txt);
		
		noRecordaccept_training_txt.setVisibility(View.GONE);
		accept_traing_submit_btn.setVisibility(View.VISIBLE);
		accept_traing_submit_btn.setOnClickListener(this);
		adapter = new Accept_TrainingDataAdapter(act, accepttraing_model_list);
		accet_train_list.setAdapter(adapter);

	}

	public void getaccept_traing_list_for_fail(Accept_Training act,String response) {
		
		accet_train_list = (ListView) act.findViewById(R.id.accept_trainig_list);
		accept_traing_linearlist = (LinearLayout) act.findViewById(R.id.accept_traing_linearHeader);
		acceptdetailhdr = (LinearLayout) act.findViewById(R.id.acceptdetailhdr);
		accept_traing_submit_btn = (LinearLayout) act.findViewById(R.id.accept_traing_submit_btn);
		noRecordaccept_training_txt = (TextView) findViewById(R.id.noRecordaccept_training_txt);
		noRecordaccept_training_txt.setVisibility(View.VISIBLE);
		noRecordaccept_training_txt.setText(response);
		accet_train_list.setVisibility(View.GONE);
		acceptdetailhdr.setVisibility(View.GONE);
		accept_traing_linearlist.setVisibility(View.VISIBLE);
		accept_traing_submit_btn.setVisibility(View.GONE);

	}

}
