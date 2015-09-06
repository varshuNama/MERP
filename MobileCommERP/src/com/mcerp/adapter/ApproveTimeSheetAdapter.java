package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcerp.asyncheck.AsyncTaskApproveTimesheetSearch;
import com.mcerp.main.R;
import com.mcerp.model.ApproveTimesheetModel;

public class ApproveTimeSheetAdapter extends BaseAdapter {

	Activity con;
	ArrayList<ApproveTimesheetModel> arraylist = new ArrayList<ApproveTimesheetModel>();

	public ApproveTimeSheetAdapter(Activity context, ArrayList<ApproveTimesheetModel> array) {
		super();
		con = context;
		arraylist = array;

	}

	class ViewHolder {
		public TextView monthyear, earnedleave, carryforward, leaveinhand,
				leavetaken, pendingassets, forward,nod;
		public LinearLayout linearreport,linear0, linear1, linear2, linear3, linear4,
				linear5, linear6, linear7,serachimg;
	

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arraylist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View row, ViewGroup parent) {
		LayoutInflater mInflater =  con.getLayoutInflater();
		ViewHolder holder;

		if (row == null)
			row = mInflater.inflate(R.layout.row_approve_timesheet, null);

		holder = new ViewHolder();
		holder.linearreport = (LinearLayout) row.findViewById(R.id.lineaereport);
		holder.linear7 = (LinearLayout) row.findViewById(R.id.linear0);
		holder.linear1 = (LinearLayout) row.findViewById(R.id.linear1);
		holder.linear2 = (LinearLayout) row.findViewById(R.id.linear2);
		holder.linear3 = (LinearLayout) row.findViewById(R.id.linear3);
		holder.linear4 = (LinearLayout) row.findViewById(R.id.linear4);
		holder.linear5 = (LinearLayout) row.findViewById(R.id.linear5);
		holder.linear6 = (LinearLayout) row.findViewById(R.id.linear6);
		holder.linear7 = (LinearLayout) row.findViewById(R.id.linear7);
		

		holder.linearreport.setWeightSum(9);
	

		holder.monthyear = (TextView) row.findViewById(R.id.month);
		holder.serachimg=(LinearLayout) row.findViewById(R.id.searimg);
		holder.earnedleave = (TextView) row.findViewById(R.id.empname);
		holder.carryforward = (TextView) row.findViewById(R.id.project);
		holder.leaveinhand = (TextView) row.findViewById(R.id.projecttype);
		holder.leavetaken = (TextView) row.findViewById(R.id.projectstart);
		holder.pendingassets = (TextView) row.findViewById(R.id.location);
		holder.forward = (TextView) row.findViewById(R.id.timesheetstart);
		holder.nod = (TextView) row.findViewById(R.id.nod);

		holder.monthyear.setText(arraylist.get(position).getMonth());
		holder.earnedleave.setText(arraylist.get(position).getEmpName());
		holder.carryforward.setText(arraylist.get(position).getProject());
		holder.leaveinhand.setText(arraylist.get(position).getProjType());
		holder.leavetaken.setText(arraylist.get(position).getProjStart());
		holder.pendingassets.setText(arraylist.get(position).getCircle());
		holder.forward.setText(arraylist.get(position).getTSStart());
		holder.nod.setText(arraylist.get(position).getNOD());
		
		holder.serachimg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AsyncTaskApproveTimesheetSearch(con,arraylist.get(position).getEmpId(),arraylist.get(position).getMonthYear()).execute();

				
			}
		});

		return row;
	}
}