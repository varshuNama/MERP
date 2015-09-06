package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.LeaveReportModel;

public class LeaveReportAdapter extends BaseAdapter {

	Context con;
	ArrayList<LeaveReportModel> arraylist = new ArrayList<LeaveReportModel>();

	public LeaveReportAdapter(Context context, ArrayList<LeaveReportModel> array) {
		super();
		con = context;
		arraylist = array;

	}

	class ViewHolder {
		public TextView monthyear, earnedleave, carryforward, leaveinhand,
				leavetaken, pendingassets, forward;
		public LinearLayout linearreport, linear1, linear2, linear3, linear4,
				linear5, linear6, linear7;

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
	public View getView(int position, View row, ViewGroup parent) {
		LayoutInflater mInflater = ((Activity) con).getLayoutInflater();
		ViewHolder holder;

		if (row == null)
			row = mInflater.inflate(R.layout.row_leave_report, null);

		holder = new ViewHolder();
		holder.linearreport = (LinearLayout) row.findViewById(R.id.lineaereport);
		holder.linear1 = (LinearLayout) row.findViewById(R.id.linear1);
		holder.linear2 = (LinearLayout) row.findViewById(R.id.linear2);
		holder.linear3 = (LinearLayout) row.findViewById(R.id.linear3);
		holder.linear4 = (LinearLayout) row.findViewById(R.id.linear4);
		holder.linear5 = (LinearLayout) row.findViewById(R.id.linear5);
		holder.linear6 = (LinearLayout) row.findViewById(R.id.linear6);
		holder.linear7 = (LinearLayout) row.findViewById(R.id.linear7);

		holder.linearreport.setWeightSum(7);
	

		holder.monthyear = (TextView) row.findViewById(R.id.month_year);
		holder.earnedleave = (TextView) row.findViewById(R.id.earnedleave);
		holder.carryforward = (TextView) row.findViewById(R.id.carry_forward);
		holder.leaveinhand = (TextView) row.findViewById(R.id.leave_in_hand);
		holder.leavetaken = (TextView) row.findViewById(R.id.leave_taken);
		holder.pendingassets = (TextView) row.findViewById(R.id.pending_assets);
		holder.forward = (TextView) row.findViewById(R.id.forward);

		holder.monthyear.setText(arraylist.get(position).getMonthDesc());
		holder.earnedleave.setText(arraylist.get(position).getMonth_Leave());
		holder.carryforward.setText(arraylist.get(position).getCarry_Forward());
		holder.leaveinhand.setText(arraylist.get(position).getLeave_In_Hand());
		holder.leavetaken.setText(arraylist.get(position).getLeave_Taken());
		holder.pendingassets.setText(arraylist.get(position).getApproval_pending());
		holder.forward.setText(arraylist.get(position).getForward());

		return row;
	}
}