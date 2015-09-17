package com.mcerp.main;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.HomeModel;

public class Home extends Fragment {
	ArrayList<HomeModel> arraylist = new ArrayList<HomeModel>();
	View rootView;
	TextView project, projectmanager, location, onproject, leavesheet,
			timesheet, trainingsheet, totalassets, totalpendingassests,
			assetspendingfromadmin, assetspenidingfromemp;

	public Home(ArrayList<HomeModel> homearray) {
		arraylist = homearray;
	}
	public Home() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_home, container, false);
		project = (TextView) rootView.findViewById(R.id.project);
		projectmanager = (TextView) rootView.findViewById(R.id.projmgr);
		location = (TextView) rootView.findViewById(R.id.location);
		onproject = (TextView) rootView.findViewById(R.id.onproject);
		leavesheet = (TextView) rootView.findViewById(R.id.leaverequest);
		timesheet = (TextView) rootView.findViewById(R.id.timesheetrequest);
		trainingsheet = (TextView) rootView.findViewById(R.id.trainingrequest);
		totalassets = (TextView) rootView.findViewById(R.id.totalassets);
		totalpendingassests = (TextView) rootView
				.findViewById(R.id.totalpendingassets);
		assetspendingfromadmin = (TextView) rootView
				.findViewById(R.id.assetspendingfrnadmin);
		assetspenidingfromemp = (TextView) rootView
				.findViewById(R.id.assetsfrmpendingemp);
		try {
			project.setText(arraylist.get(0).getProject());
			projectmanager.setText(arraylist.get(0).getProjMgr());
			location.setText(arraylist.get(0).getLocation());
			onproject.setText(arraylist.get(0).getProjStart());
			leavesheet.setText(arraylist.get(0).getLeaveReq());
			timesheet.setText(arraylist.get(0).getTimesheetReq());
			trainingsheet.setText(arraylist.get(0).getTrainingReq());
			totalassets.setText(arraylist.get(0).getTotalAssets());
			totalpendingassests.setText(arraylist.get(0).getToAdminPending());
			assetspendingfromadmin.setText(arraylist.get(0)
					.getFromAdminPending());
			assetspenidingfromemp.setText(arraylist.get(0).getFromEmpPending());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootView;
	}

}
