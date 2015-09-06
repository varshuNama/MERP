package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.TimesheetSearchModel;

public class ApproveTimesheetSearchAdapter extends ArrayAdapter<TimesheetSearchModel> {
	private ArrayList<TimesheetSearchModel> list;
	private final Activity context;
	boolean checkAll_flag = false;
	boolean checkItem_flag = false;

	public ArrayList<TimesheetSearchModel> getList() {
		return list;
	}
	public void updateResults(ArrayList<TimesheetSearchModel> results) {
		list = results;
        notifyDataSetChanged();
    }

	public ApproveTimesheetSearchAdapter(Activity context, ArrayList<TimesheetSearchModel> results) {
		super(context, R.layout.row_timesheet_search, results);
		this.context = context;
		list = results;
	}

	class ViewHolder {
		TextView date, day, activity, descp, status;
		CheckBox checkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		
			LayoutInflater inflator = context.getLayoutInflater();
			convertView = inflator.inflate(R.layout.row_timesheet_search, null);
			viewHolder = new ViewHolder();
			viewHolder.date = (TextView) convertView.findViewById(R.id.date);
			viewHolder.day = (TextView) convertView.findViewById(R.id.day);
			viewHolder.activity = (TextView) convertView.findViewById(R.id.activity);
			viewHolder.descp = (TextView) convertView.findViewById(R.id.description);
			viewHolder.status = (TextView) convertView.findViewById(R.id.status);
			
			viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkTime);
		
			convertView.setTag(viewHolder);

			
			viewHolder.checkBox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							int getPosition = (Integer) buttonView.getTag();
							list.get(getPosition).setChecked(buttonView.isChecked());

						}
					});

			viewHolder.checkBox.setTag(position); 
			

		

		viewHolder.date.setText(list.get(position).getDate());
		viewHolder.checkBox.setChecked(list.get(position).isChecked());
		viewHolder.day.setText(list.get(position).getDay());
		viewHolder.activity.setText(list.get(position).getActivity());
		viewHolder.descp.setText(list.get(position).getDescription());
		viewHolder.status.setText(list.get(position).getStatus());
		
		
		return convertView;
	}
}