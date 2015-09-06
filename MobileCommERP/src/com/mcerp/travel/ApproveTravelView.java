package com.mcerp.travel;

import java.util.ArrayList;

import com.mcerp.adapter.NewTimesheetAdapter;
import com.mcerp.adapter.ViewtraveldataAdapter;
import com.mcerp.asyncheck.ApproveAsktask;
import com.mcerp.main.NewSendTimesheet;
import com.mcerp.main.R;
import com.mcerp.model.Approvetravelmodel;
import com.mcerp.util.AppPreferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ApproveTravelView extends Activity {

	AppPreferences prefs;
	ListView listprojecttravel;
	ViewtraveldataAdapter adapter;
	LinearLayout backbtntravelview,projecthdrTravel;
	TextView noRecordprojecttravel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewtraveldetails_empwise);
		init(ApproveTravelView.this);
		new ApproveAsktask(ApproveTravelView.this,prefs.getUserID()).execute();
	}
	private void init(ApproveTravelView act) {
		prefs=AppPreferences.getInstance(ApproveTravelView.this);
		noRecordprojecttravel=(TextView) findViewById(R.id.noRecordprojecttravel);
		listprojecttravel=(ListView)act. findViewById(R.id.listprojecttravel_view);

		backbtntravelview=(LinearLayout) findViewById(R.id.backbtntravelview);
		projecthdrTravel=(LinearLayout) findViewById(R.id.teavel_accept_header);
		backbtntravelview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
	public void getlistdata(ApproveTravelView act,ArrayList<Approvetravelmodel> approvetravelmodelslist)
	{
		init(act);
		adapter = new ViewtraveldataAdapter(act, approvetravelmodelslist);
		listprojecttravel.setAdapter(adapter);
		noRecordprojecttravel.setVisibility(View.GONE);
		listprojecttravel.setVisibility(View.VISIBLE);
		projecthdrTravel.setVisibility(View.VISIBLE);

	}
	public void nolistdata(ApproveTravelView act,String response)
	{
		init(act);
		noRecordprojecttravel.setVisibility(View.VISIBLE);
		noRecordprojecttravel.setText(response);
		listprojecttravel.setVisibility(View.GONE);
		projecthdrTravel.setVisibility(View.GONE);

	}
}
