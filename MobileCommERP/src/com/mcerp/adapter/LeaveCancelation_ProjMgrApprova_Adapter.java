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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mcerp.main.LeaveCancelation_ProjMgrApprova;
import com.mcerp.main.R;
import com.mcerp.model.LeaveCancelation_ProjMgrApprova_Model;

public class LeaveCancelation_ProjMgrApprova_Adapter extends BaseAdapter {
	LeaveCancelation_ProjMgrApprova context;

	ArrayList<LeaveCancelation_ProjMgrApprova_Model> arraylistdata = new ArrayList<LeaveCancelation_ProjMgrApprova_Model>();

	public LeaveCancelation_ProjMgrApprova_Adapter(
			LeaveCancelation_ProjMgrApprova con,
			ArrayList<LeaveCancelation_ProjMgrApprova_Model> arraylist) {
		context = con;
		arraylistdata = arraylist;

	}

	public ArrayList<LeaveCancelation_ProjMgrApprova_Model> getList() {
		return arraylistdata;
	}

	class ViewHolder {
		public TextView LeaveCancelation_ProjMgrApprova_emp_name,
				LeaveCancelation_ProjMgrApprova_emp_email,
				LeaveCancelation_ProjMgrApprova_project_name,
				LeaveCancelation_ProjMgrApprova_leave_date,
				LeaveCancelation_ProjMgrApprova_leave_day,
				LeaveCancelation_ProjMgrApprova_cancelremark;

		public RadioGroup LeaveCancelation_ProjMgrApprova_ApproveReject;
		RadioButton LeaveCancelation_ProjMgrApprova_approve,
				LeaveCancelation_ProjMgrApprova_reject;

		CheckBox checkboxstatus;

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

		row = mInflater.inflate(R.layout.row_leave_cancelation_projmgrapproval,
				null);

		holder = new ViewHolder();
		holder.LeaveCancelation_ProjMgrApprova_emp_name = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_emp_name);
		holder.LeaveCancelation_ProjMgrApprova_emp_email = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_emp_email);
		holder.LeaveCancelation_ProjMgrApprova_project_name = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_project_name);
		holder.LeaveCancelation_ProjMgrApprova_leave_date = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_leave_date);
		holder.LeaveCancelation_ProjMgrApprova_leave_day = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_leave_day);
		holder.LeaveCancelation_ProjMgrApprova_cancelremark = (TextView) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_cancelremark);

		holder.LeaveCancelation_ProjMgrApprova_ApproveReject = (RadioGroup) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_ApproveReject);
		holder.LeaveCancelation_ProjMgrApprova_approve = (RadioButton) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_approve);
		holder.LeaveCancelation_ProjMgrApprova_reject = (RadioButton) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_reject);
		holder.checkboxstatus = (CheckBox) row
				.findViewById(R.id.LeaveCancelation_ProjMgrApprova_checkBox1);
		holder.checkboxstatus
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							arraylistdata.get(position).setCheckboxstatus(true);

						} else {
							arraylistdata.get(position)
									.setCheckboxstatus(false);

						}

					}
				});
		holder.LeaveCancelation_ProjMgrApprova_ApproveReject
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (checkedId == R.id.LeaveCancelation_ProjMgrApprova_approve) {
							arraylistdata.get(position).setYes_no_status("3");

							arraylistdata.get(position).setRadioApprove(true);
							arraylistdata.get(position).setRadioReject(false);

						} else {

							arraylistdata.get(position).setYes_no_status("9");

							arraylistdata.get(position).setRadioApprove(false);
							arraylistdata.get(position).setRadioReject(true);

						}
					}
				});

		holder.LeaveCancelation_ProjMgrApprova_emp_name.setText(arraylistdata
				.get(position).getEmpName());
		holder.LeaveCancelation_ProjMgrApprova_emp_email.setText(arraylistdata
				.get(position).getEmail());
		holder.LeaveCancelation_ProjMgrApprova_project_name
				.setText(arraylistdata.get(position).getProjName());
		holder.LeaveCancelation_ProjMgrApprova_leave_date.setText(arraylistdata
				.get(position).getLeaveDate());
		holder.LeaveCancelation_ProjMgrApprova_leave_day.setText(arraylistdata
				.get(position).getLeaveDay());
		holder.checkboxstatus.setChecked(arraylistdata.get(position)
				.getCheckboxstatus());
		holder.LeaveCancelation_ProjMgrApprova_approve.setChecked(arraylistdata
				.get(position).isRadioApprove());
		holder.LeaveCancelation_ProjMgrApprova_reject.setChecked(arraylistdata
				.get(position).isRadioReject());
		holder.LeaveCancelation_ProjMgrApprova_cancelremark
				.setText(arraylistdata.get(position).getCancelRemark());

		return row;
	}
}
