package com.mcerp.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.Approvetraveldetailsmodel;
import com.mcerp.travel.ApproveTraveldata;


public  class ApprovetravelDetailsAdapter extends BaseAdapter {
	ApproveTraveldata context;

	public static ArrayList<Approvetraveldetailsmodel> arraylistdata = new ArrayList<Approvetraveldetailsmodel>();



	public ApprovetravelDetailsAdapter(ApproveTraveldata act,
			ArrayList<Approvetraveldetailsmodel> arraylist) {
		context = act;
		arraylistdata = arraylist;
		

	}
	public ArrayList<Approvetraveldetailsmodel> getList() {
		return arraylistdata;
	}
	public ApprovetravelDetailsAdapter() {

	}

	class ViewHolder {
		public TextView approve_travel_details_traveldate_row_txt, 
		approve_travel_details_location_row_txt,
		approve_travel_details_transportmode_row_txt,
		approve_travel_details_travelunit_row_txt, 
		approve_travel_details_unitcast_row_txt;
		EditText approve_travel_details_Pm_approve_unit_row_edit,approve_travel_details_Pm_remarks_row_edit;
		public RadioGroup radioGroup;
		RadioButton radioapprovetravel,radiorejecttravel;
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
	@SuppressLint("ViewHolder")
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder ;

		LayoutInflater mInflater = context.getLayoutInflater();
		view = mInflater.inflate(R.layout.approve_travel_details_row,null);
		viewHolder = new ViewHolder();
		viewHolder.approve_travel_details_traveldate_row_txt = (TextView) view.findViewById(R.id.approve_travel_details_traveldate_row_txt);

		viewHolder.approve_travel_details_location_row_txt = (TextView) view.findViewById(R.id.approve_travel_details_location_row_txt);

		viewHolder.approve_travel_details_transportmode_row_txt = (TextView) view.findViewById(R.id.approve_travel_details_transportmode_row_txt);

		viewHolder.approve_travel_details_travelunit_row_txt = (TextView) view.findViewById(R.id.approve_travel_details_travelunit_row_txt);

		viewHolder.approve_travel_details_unitcast_row_txt = (TextView) view.findViewById(R.id.approve_travel_details_unitcast_row_txt);

		viewHolder.approve_travel_details_Pm_approve_unit_row_edit = (EditText) view.findViewById(R.id.approve_travel_details_Pm_approve_unitcost_row_edit);

		viewHolder.approve_travel_details_Pm_remarks_row_edit = (EditText) view.findViewById(R.id.approve_travel_details_Pm_remarks_row_edit);
		viewHolder.radioGroup = (RadioGroup) view.findViewById(R.id.approve_travel_details_Pm_approve_row_radiogroup);
		viewHolder.radioapprovetravel = (RadioButton) view.findViewById(R.id.radioapprovetravel);
		viewHolder.radiorejecttravel = (RadioButton) view.findViewById(R.id.radiorejecttravel);
		viewHolder.checkboxstatus= (CheckBox) view.findViewById(R.id.approve_travel_details_checkbox);


		viewHolder.radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {



				if(checkedId==R.id.radioapprovetravel){
					arraylistdata.get(position).setYes_no_status("2");
					arraylistdata.get(position).setRadioFirst(true);
					arraylistdata.get(position).setRadiSecond(false);


				}
				else{
					arraylistdata.get(position).setYes_no_status("9");
					arraylistdata.get(position).setRadioFirst(false);
					arraylistdata.get(position).setRadiSecond(true);

				}

			}
		});

		viewHolder.checkboxstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if(isChecked==true){
					arraylistdata.get(position).setCheckboxstatus(true);
				}
				else{
					arraylistdata.get(position).setCheckboxstatus(false);
				}

			}
		}			); 		
		viewHolder.approve_travel_details_Pm_approve_unit_row_edit.addTextChangedListener(new TextWatcher() {

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

				arraylistdata.get(position).setPm_approvecast(arg0.toString());
			}
		});
		viewHolder.approve_travel_details_Pm_remarks_row_edit.addTextChangedListener(new TextWatcher() {

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

		viewHolder.approve_travel_details_traveldate_row_txt.setText(arraylistdata.get(position).getTravelDate());
		viewHolder.approve_travel_details_location_row_txt.setText(arraylistdata.get(position).getLocation());
		viewHolder.approve_travel_details_transportmode_row_txt.setText(arraylistdata.get(position).getTransportModeDesc());
		viewHolder.approve_travel_details_travelunit_row_txt.setText(arraylistdata.get(position).getUnit());
		viewHolder.approve_travel_details_unitcast_row_txt.setText(arraylistdata.get(position).getUnitCost());
		viewHolder.approve_travel_details_Pm_approve_unit_row_edit.setText(arraylistdata.get(position).getPm_approvecast());
		viewHolder.approve_travel_details_Pm_remarks_row_edit.setText(arraylistdata.get(position).getRemarks());
		viewHolder.checkboxstatus.setChecked(arraylistdata.get(position).getCheckboxstatus());
		viewHolder.radioapprovetravel.setChecked(arraylistdata.get(position).getRadioFirst());
		viewHolder.radiorejecttravel.setChecked(arraylistdata.get(position).getRadiSecond());
		return view;


	}
}