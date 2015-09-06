package com.mcerp.gts;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mcerp.adapter.List_to_Complete_Training_Adapter;
import com.mcerp.asyncheck.List_To_Complete_Training_Asyntask;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.List_To_Complete_Training_model;
import com.mcerp.util.AppPreferences;

public class Complete_Training extends Activity {

	String responseData, message, EmpId;
	ConnectionDetector connectiondetector;

	TextView noRecordaccept_training_txt;
	List_to_Complete_Training_Adapter adapter;

	ListView list_to_copmlete_training_list;

	LinearLayout list_to_complete_training_backbtn,list_to_complete_training;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list_to_complete_training);
		
		list_to_complete_training_backbtn=(LinearLayout) findViewById(R.id.list_to_complete_training_backbtn);
		list_to_complete_training_backbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		


		new List_To_Complete_Training_Asyntask(Complete_Training.this).execute();
	}

	public void getlist_to_complete_training(Complete_Training act,
			ArrayList<List_To_Complete_Training_model> list_to_complete) {
		list_to_copmlete_training_list = (ListView) act
				.findViewById(R.id.list_to_copmlete_training_list);
		noRecordaccept_training_txt = (TextView) act.findViewById(R.id.noRecordaccept_training_txt);
		noRecordaccept_training_txt.setVisibility(View.GONE);
		adapter = new List_to_Complete_Training_Adapter(act, list_to_complete);
		list_to_copmlete_training_list.setAdapter(adapter);
		
	}
	public void getlist_to_complete_training_fail(Complete_Training act
			,String response) {
		list_to_copmlete_training_list = (ListView) act
				.findViewById(R.id.list_to_copmlete_training_list);
		list_to_copmlete_training_list.setVisibility(View.GONE);
		noRecordaccept_training_txt = (TextView) act.findViewById(R.id.noRecordaccept_training_txt);
		noRecordaccept_training_txt.setVisibility(View.VISIBLE);
		noRecordaccept_training_txt.setText(response);
		list_to_complete_training = (LinearLayout) act.findViewById(R.id.list_to_complete_training);
		list_to_complete_training.setVisibility(View.GONE);
		
		
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
