package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcerp.asyncheck.AsyncTaskViewDetailTimeSheet;
import com.mcerp.main.R;
import com.mcerp.main.ViewTimeSheet;
import com.mcerp.model.ViewTimeSheetModel;

public class ViewTimeSheetAdapter extends BaseAdapter {

	ViewTimeSheet con;
	String flag;
	ArrayList<ViewTimeSheetModel> arraylist = new ArrayList<ViewTimeSheetModel>();

	public ViewTimeSheetAdapter(ViewTimeSheet context,
			ArrayList<ViewTimeSheetModel> array, String check) {
		super();
		con = context;
		arraylist = array;
		flag = check;

	}

	class ViewHolder {
		public TextView month, project, saved, pending, rejected, approved;
		public ImageView img;
		public LinearLayout linear,linearImg,linearapprove;

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
		LayoutInflater mInflater = ((Activity) con).getLayoutInflater();
		ViewHolder holder;

		if (row == null)
			row = mInflater.inflate(R.layout.row_view_timesheet, null);

		holder = new ViewHolder();

		holder.month = (TextView) row.findViewById(R.id.Month);
		holder.project = (TextView) row.findViewById(R.id.Project);
		holder.saved = (TextView) row.findViewById(R.id.Saved);
		holder.pending = (TextView) row.findViewById(R.id.Pending);
		holder.rejected = (TextView) row.findViewById(R.id.Rejected);
		holder.approved = (TextView) row.findViewById(R.id.Approved);
		holder.linearImg = (LinearLayout) row.findViewById(R.id.linearimg);
		holder.linear=(LinearLayout) row.findViewById(R.id.mainheader);
		holder.linearapprove=(LinearLayout) row.findViewById(R.id.linearapp);
		
		if (flag == "1") {
			holder.linear.setWeightSum(7);
			holder.linearImg.setVisibility(View.VISIBLE);
			holder.linearapprove.setVisibility(View.VISIBLE);
			holder.month.setText(arraylist.get(position).getMonth());
			holder.project.setText(arraylist.get(position).getProject());
			holder.saved.setText(arraylist.get(position).getSaved());
			holder.pending.setText(arraylist.get(position).getPending());
			holder.rejected.setText(arraylist.get(position).getRejected());
			holder.approved.setText(arraylist.get(position).getApproved());
			holder.linearImg.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					new AsyncTaskViewDetailTimeSheet(con, arraylist.get(
							position).getMonthYear(), "D", "2").execute("");

				}
			});
		} else {
			holder.linear.setWeightSum(5);
			holder.linearImg.setVisibility(View.GONE);
			holder.linearapprove.setVisibility(View.GONE);
			holder.month.setText(arraylist.get(position).getMonth());
			holder.project.setText(arraylist.get(position).getProject());
			holder.saved.setText(arraylist.get(position).getSaved());
			holder.pending.setText(arraylist.get(position).getRejected());
			holder.rejected.setText(arraylist.get(position).getApproved());
			
		}
		return row;
	}
}