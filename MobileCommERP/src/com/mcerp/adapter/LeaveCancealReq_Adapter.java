package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.mcerp.livendtimesheet.LeaveCancealReq;
import com.mcerp.main.R;
import com.mcerp.model.LeaveCancealReqModelData;

public class LeaveCancealReq_Adapter extends BaseAdapter {

	LeaveCancealReq context;

	ArrayList<LeaveCancealReqModelData> arraylistdata = new ArrayList<LeaveCancealReqModelData>();

	public LeaveCancealReq_Adapter(LeaveCancealReq leavedetail,
			ArrayList<LeaveCancealReqModelData> arraylist) {

		context = leavedetail;
		arraylistdata = arraylist;

	}

	public ArrayList<LeaveCancealReqModelData> getList() {
		return arraylistdata;
	}

	class ViewHolder {
		public TextView leave_cancel_date, leave_cancel_day,
				leave_cancel_prj_mng, leave_cancel_project_name;

		EditText leave_cancel_review;
		CheckBox leave_cancel_checkbox;

	}

	@Override
	public int getCount() {

		return arraylistdata.size();
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
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();

		ViewHolder holder;

		row = mInflater.inflate(R.layout.row_leave_canceal_request, null);
		holder = new ViewHolder();

		holder.leave_cancel_date = (TextView) row
				.findViewById(R.id.leave_cancel_date);
		holder.leave_cancel_day = (TextView) row
				.findViewById(R.id.leave_cancel_day);
		holder.leave_cancel_prj_mng = (TextView) row
				.findViewById(R.id.leave_cancel_prj_mng);
		holder.leave_cancel_project_name = (TextView) row
				.findViewById(R.id.leave_cancel_project_name);
		holder.leave_cancel_review = (EditText) row
				.findViewById(R.id.leave_cancel_review);
		holder.leave_cancel_checkbox = (CheckBox) row
				.findViewById(R.id.leave_cancel_checkbox);
		holder.leave_cancel_checkbox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							arraylistdata.get(position)
									.setLeave_cancel_checkbox(true);

						} else {
							arraylistdata.get(position)
									.setLeave_cancel_checkbox(false);

						}

					}
				});

		holder.leave_cancel_review.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).getLeavecancelreqedit();
			}
		});

		holder.leave_cancel_review.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).setLeavecancelReqRemarks(
						arg0.toString());
			}
		});

		holder.leave_cancel_date.setText(arraylistdata.get(position).getLeaveDate());
		holder.leave_cancel_day.setText(arraylistdata.get(position).getLeaveDay());
		
		holder.leave_cancel_prj_mng.setText(arraylistdata.get(position).getProjMgrName());
		holder.leave_cancel_project_name.setText(arraylistdata.get(position).getProjname());
		
		
		holder.leave_cancel_checkbox.setChecked(arraylistdata.get(position)
				.getLeave_cancel_checkbox());
		holder.leave_cancel_review.setText(arraylistdata.get(position).getLeavecancelReqRemarks());
		
		

		return row;
	}

}
