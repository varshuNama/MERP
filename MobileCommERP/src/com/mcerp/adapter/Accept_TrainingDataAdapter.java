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
import android.widget.Spinner;
import android.widget.TextView;

import com.mcerp.gts.Accept_Training;
import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;
import com.mcerp.model.TrainingModelData;

public class Accept_TrainingDataAdapter extends BaseAdapter {
	Accept_Training context;

	
	ArrayList<TrainingModelData> arraylistdata = new ArrayList<TrainingModelData>();


	public Accept_TrainingDataAdapter(Accept_Training acceptDetail,
			ArrayList<TrainingModelData> arraylist) {
		context = acceptDetail;
		arraylistdata = arraylist;

	}

	public ArrayList<TrainingModelData> getList() {
		return arraylistdata;
	}

	class ViewHolder {
		public TextView accepttrainingname, accepttraininglocation,
				accepttraining_duration, accepttrainin_travel_start_time,
				accepttrainin_travel_end_time;
		Spinner rd;
		protected AssetModelData data;
		EditText trainingremarks;
		int ref;

		public RadioGroup radioGrouptrainingApproveReject;
		RadioButton rd_traniningapprove, rd_trainingreject;

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

		row = mInflater.inflate(R.layout.row_accept_training, null);

		holder = new ViewHolder();
		holder.accepttrainingname = (TextView) row
				.findViewById(R.id.accepttrainingname);
		holder.accepttraininglocation = (TextView) row
				.findViewById(R.id.accepttraininglocation);
		holder.accepttraining_duration = (TextView) row
				.findViewById(R.id.accepttraining_duration);
		holder.accepttrainin_travel_start_time = (TextView) row
				.findViewById(R.id.accepttrainin_travel_start_time);
		holder.accepttrainin_travel_end_time = (TextView) row
				.findViewById(R.id.accepttrainin_travel_end_time);
		holder.radioGrouptrainingApproveReject = (RadioGroup) row
				.findViewById(R.id.radioGrouptrainingApproveReject);
		holder.rd_traniningapprove = (RadioButton) row
				.findViewById(R.id.rd_traniningapprove);
		holder.trainingremarks= (EditText) row.findViewById(R.id.training_remarks_detail);
		holder.rd_trainingreject = (RadioButton) row
				.findViewById(R.id.rd_trainingreject);
		holder.checkboxstatus = (CheckBox) row.findViewById(R.id.checkBox1);
		holder.checkboxstatus
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
						arraylistdata.get(position).setCheckboxstatus(true);
							
						} else {
							arraylistdata.get(position).setCheckboxstatus(false);
							
						}

					}
				});
		holder.radioGrouptrainingApproveReject
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (checkedId == R.id.rd_traniningapprove) {
							arraylistdata.get(position).setYes_no_status("1");
							
							arraylistdata.get(position).setRadioApprove(true);
							arraylistdata.get(position).setRadioReject(false);

						} else {

							
							arraylistdata.get(position).setYes_no_status("9");
							
							arraylistdata.get(position).setRadioApprove(false);
							arraylistdata.get(position).setRadioReject(true);

						}
					}
				});
		holder.trainingremarks.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).getAccept_trianingedit();
			}
		});
		
		holder.trainingremarks.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).setRemarks(arg0.toString());
			}
		});


		holder.accepttrainingname.setText(arraylistdata.get(position)
				.getTraining());
		holder.accepttraininglocation.setText(arraylistdata.get(position)
				.getLocation());
		holder.accepttraining_duration.setText(arraylistdata.get(position)
				.getNoOfDays());
		holder.accepttrainin_travel_start_time.setText(arraylistdata.get(
				position).getTravelStart());
		holder.accepttrainin_travel_end_time.setText(arraylistdata
				.get(position).getTravelEnd());
		holder.checkboxstatus.setChecked(arraylistdata.get(position)
				.getCheckboxstatus());
		holder.rd_traniningapprove.setChecked(arraylistdata.get(position).isRadioApprove());
		holder.rd_trainingreject.setChecked(arraylistdata.get(position).isRadioReject());
		holder.trainingremarks.setText(arraylistdata.get(position).getRemarks());
		
		return row;
	}
}
