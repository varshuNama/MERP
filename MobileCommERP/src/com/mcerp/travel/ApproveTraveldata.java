package com.mcerp.travel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mcerp.adapter.ApprovetravelDetailsAdapter;
import com.mcerp.asyncheck.ApproveAcceptAsktask;
import com.mcerp.asyncheck.ApprovetravelbymanagerAskTask;
import com.mcerp.main.R;
import com.mcerp.model.Approvetraveldetailsmodel;
import com.mcerp.util.AppPreferences;

public class ApproveTraveldata extends Activity {

	AppPreferences prefs;
	ListView listapprove_travel_details;
	ApprovetravelDetailsAdapter adapter;
	LinearLayout backbtntravelempwise;
	String managerid,managername,manageremail,empid,empname;
	String Empemail,Empname,Empid;
	LinearLayout approve__travel_senddata;
	ArrayList<String> id;
	ArrayList<String> Location;
	ArrayList<String> ProjName;
	ArrayList<String> TransportMode;
	ArrayList<String> TransportModeDesc;
	ArrayList<String> TravelDate;
	ArrayList<String> Unit;
	ArrayList<String> UnitCost;
	ArrayList<String> pm_approvecast;
	ArrayList<String> arrayApproveStatus;
	ArrayList<String> arrayRemarks;
	ApproveTraveldata act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.approvetraveldetails_empwise);
		prefs=AppPreferences.getInstance(ApproveTraveldata.this);
		managerid=prefs.getUserID();
		managername=prefs.getEmpName();
		manageremail=prefs.getUserName();
		
		
		Bundle bundle = getIntent().getExtras();
		Empemail = bundle.getString("EmpEmail");   
		Empname = bundle.getString("EmpName");   
		Empid=bundle.getString("EmpId");  
		backbtntravelempwise=(LinearLayout) findViewById(R.id.backbtntravelempwise);
		backbtntravelempwise.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ApproveTraveldata.this,ApproveTravelView.class);
				startActivity(intent);
				finish();
			}
		});

		new ApproveAcceptAsktask(ApproveTraveldata.this,managerid,Empid,Empemail,Empname).execute();
	}
	public void getlistdata(final ApproveTraveldata act,ArrayList<Approvetraveldetailsmodel> approvetravelmodelslist)
	{
		listapprove_travel_details=(ListView)act. findViewById(R.id.listapprove_travel_details);
		approve__travel_senddata=(LinearLayout)act. findViewById(R.id.approve__travel_senddata);
		this.act=act;
		id=new ArrayList<String>();
		Location=new ArrayList<String>();
		ProjName=new ArrayList<String>();
		TransportMode=new ArrayList<String>();
		TransportModeDesc=new ArrayList<String>();
		TravelDate=new ArrayList<String>();
		Unit=new ArrayList<String>();
		UnitCost=new ArrayList<String>();
		pm_approvecast=new ArrayList<String>();
		arrayApproveStatus=new ArrayList<String>();
		arrayRemarks=new ArrayList<String>();
		adapter = new ApprovetravelDetailsAdapter(act, approvetravelmodelslist);
		listapprove_travel_details.setAdapter(adapter);
		approve__travel_senddata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//ApprovetravelDetailsAdapter adapter1=new ApprovetravelDetailsAdapter();
				ArrayList<Approvetraveldetailsmodel> approvetraveldetailsmodelslist=adapter.getList();
				System.out.println(approvetraveldetailsmodelslist);
				for (int i = 0; i < approvetraveldetailsmodelslist.size(); i++) {
					if(approvetraveldetailsmodelslist.get(i).getCheckboxstatus()==true){
					id.add(approvetraveldetailsmodelslist.get(i).getId());
					Location.add(approvetraveldetailsmodelslist.get(i).getLocation());
					ProjName.add(approvetraveldetailsmodelslist.get(i).getProjName());
					TravelDate.add(approvetraveldetailsmodelslist.get(i).getTravelDate());
					Unit.add(approvetraveldetailsmodelslist.get(i).getUnit());
					UnitCost.add(approvetraveldetailsmodelslist.get(i).getUnitCost());
					TransportMode.add(approvetraveldetailsmodelslist.get(i).getTransportMode());
					TransportModeDesc.add(approvetraveldetailsmodelslist.get(i).getTransportModeDesc());
					pm_approvecast.add(approvetraveldetailsmodelslist.get(i).getPm_approvecast());
					arrayApproveStatus.add(approvetraveldetailsmodelslist.get(i).getYes_no_status());
					arrayRemarks.add(approvetraveldetailsmodelslist.get(i).getRemarks());
					}

				}
				new ApprovetravelbymanagerAskTask(act,managerid,managername,manageremail,Empname,Empemail,id,Location,ProjName,TravelDate,Unit,
						UnitCost,TransportMode,TransportModeDesc,pm_approvecast,arrayApproveStatus,arrayRemarks).execute();
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent=new Intent(ApproveTraveldata.this,ApproveTravelView.class);
		startActivity(intent);
		finish();
		
	}
}
