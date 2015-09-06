package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.Model;

public class ApproveLeaveAdapter extends ArrayAdapter<Model> {
	private ArrayList<Model> list;
	private final Activity context;
	boolean checkAll_flag = false;
	boolean checkItem_flag = false;

	public ArrayList<Model> getList() {
		return list;
	}

	public void updateResults(ArrayList<Model> results) {
		list = results;
		notifyDataSetChanged();
	}

	public ApproveLeaveAdapter(Activity context, ArrayList<Model> results) {
		super(context, R.layout.row_get_leave_data, results);
		this.context = context;
		list = results;
	}

	class ViewHolder {
		TextView leaveto, leavefrom, reason, empname, project, circle, type;
		CheckBox checkBox;
		Spinner spin_approvereject;
		protected Model data;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			convertView = inflator.inflate(R.layout.row_get_leave_data, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.data = new Model(context);
			viewHolder.leaveto = (TextView) convertView
					.findViewById(R.id.leaveto);
			viewHolder.leavefrom = (TextView) convertView
					.findViewById(R.id.leavefrom);
			viewHolder.project = (TextView) convertView
					.findViewById(R.id.project);
			viewHolder.type = (TextView) convertView.findViewById(R.id.type);
			viewHolder.reason = (TextView) convertView
					.findViewById(R.id.reason);
			viewHolder.circle = (TextView) convertView
					.findViewById(R.id.circle);
			viewHolder.empname = (TextView) convertView.findViewById(R.id.name);
			viewHolder.checkBox = (CheckBox) convertView
					.findViewById(R.id.checkApproveLeave);
			viewHolder.spin_approvereject = (Spinner) convertView
					.findViewById(R.id.spinner_approve_reject_leave);
			convertView.setTag(viewHolder);
			viewHolder.spin_approvereject.setAdapter(viewHolder.data
					.getAdapter());
			viewHolder.spin_approvereject
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							viewHolder.data.setSelected(arg2);
							if (arg2 == 0)
								list.get(position).setSelected(1);
							else if (arg2 == 1) {
								list.get(position).setSelected(3);
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}

					});

			viewHolder.checkBox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							int getPosition = (Integer) buttonView.getTag();
							list.get(getPosition).setChecked(
									buttonView.isChecked());

						}
					});

			viewHolder.checkBox.setTag(position);
			convertView.setTag(viewHolder);// This line is important.

		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.leaveto.setText(list.get(position).getLeaveTo());
		viewHolder.checkBox.setChecked(list.get(position).isChecked());
		viewHolder.leavefrom.setText(list.get(position).getLeaveFrom());
		viewHolder.project.setText(list.get(position).getProject());
		viewHolder.reason.setText(list.get(position).getReason());
		viewHolder.circle.setText(list.get(position).getCircle());
		viewHolder.empname.setText(list.get(position).getName());
		viewHolder.type.setText(list.get(position).getType());
		viewHolder.checkBox.setChecked(list.get(position).isChecked());

		return convertView;
	}
}
