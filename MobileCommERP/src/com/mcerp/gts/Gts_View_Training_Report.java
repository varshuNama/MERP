package com.mcerp.gts;

import java.util.ArrayList;

import com.mcerp.adapter.Gts_View_Training_Report_adapter;
import com.mcerp.asyncheck.AsyncTaskGts_View_Training_Report;
import com.mcerp.main.R;
import com.mcerp.model.Gts_View_Training_Report_Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Gts_View_Training_Report extends Activity{
	
	Gts_View_Training_Report_adapter adapter;
	ListView gts_view_report_list;
	String year,month,empid;
	TextView gts_view_report_noRecord;
	LinearLayout list_to_complete_training_linearlist,list_to_complete_training,list_to_complete_training_backbtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gts_view_training_report);
		list_to_complete_training_backbtn= (LinearLayout) findViewById(R.id.list_to_complete_training_backbtn);
		list_to_complete_training_backbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		Intent intent= getIntent();
		
		 Bundle bundle = intent.getExtras();
		 
	        if(bundle!=null)
	        {
	        	year =bundle.getString("year");
	        
	            month =bundle.getString("month");
	       
	           
	            empid= bundle.getString("empid");
	            
	        }
		
		
		
		
		new AsyncTaskGts_View_Training_Report(Gts_View_Training_Report.this,empid, year,month).execute();
	}


	public void getlist_to_gts_view_training_report(
			Gts_View_Training_Report act,
			ArrayList<Gts_View_Training_Report_Model> list_to_complete) {
		gts_view_report_list=(ListView) act.findViewById(R.id.gts_view_report_list);
		gts_view_report_list.setVisibility(View.VISIBLE);
		gts_view_report_noRecord=(TextView) act.findViewById(R.id.gts_view_report_noRecord);
		gts_view_report_noRecord.setVisibility(View.GONE);
		
		
		adapter = new Gts_View_Training_Report_adapter(act,list_to_complete );
		
		gts_view_report_list.setAdapter(adapter);
	}


	public void getlist_to_gts_view_training_report_fail(
			Gts_View_Training_Report act,String response) {
		gts_view_report_noRecord=(TextView) act.findViewById(R.id.gts_view_report_noRecord);
		gts_view_report_noRecord.setText(response);
		gts_view_report_noRecord.setVisibility(View.VISIBLE);
		
		gts_view_report_list=(ListView) act.findViewById(R.id.gts_view_report_list);
		
		
		gts_view_report_list.setVisibility(View.GONE);
		list_to_complete_training_linearlist= (LinearLayout) act.findViewById(R.id.list_to_complete_training_linearlist);
		list_to_complete_training_linearlist.setVisibility(View.VISIBLE);
		
		list_to_complete_training= (LinearLayout) act.findViewById(R.id.list_to_complete_training);
		list_to_complete_training.setVisibility(View.GONE);
		
	}
   @Override
public void onBackPressed() {
	super.onBackPressed();
	finish();
}
}
