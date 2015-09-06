package com.mcerp.adapter;

import java.util.ArrayList;

import com.mcerp.gts.Gts_View_Training_Report;
import com.mcerp.main.R;
import com.mcerp.model.Gts_View_Training_Report_Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Gts_View_Training_Report_adapter extends BaseAdapter {
	Gts_View_Training_Report context;

	ArrayList<Gts_View_Training_Report_Model> arraylistdata = new ArrayList<Gts_View_Training_Report_Model>();

	public Gts_View_Training_Report_adapter(Gts_View_Training_Report act,
			ArrayList<Gts_View_Training_Report_Model> arraylist) {
		context = act;
		arraylistdata = arraylist;

	}

	public ArrayList<Gts_View_Training_Report_Model> getlist() {
		return arraylistdata;

	}

	class ViewHolder {
		TextView gts_view_report_training, gts_view_report_customer, gts_view_report_training_place, gts_view_report_training_date,
		gts_view_report_training_end, gts_view_report_travel_start, gts_view_report_travel_end, gts_view_report_status;

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
	public View getView(final int position, View convertview, ViewGroup parent) {
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
		ViewHolder viewHolder;
		
		
		convertview= mInflater.inflate(R.layout.row_gts_view_training_report, null);
		viewHolder = new ViewHolder();
		viewHolder.gts_view_report_training=  (TextView) convertview.findViewById(R.id.gts_view_report_training);
		viewHolder.gts_view_report_customer = (TextView) convertview.findViewById(R.id.gts_view_report_customer);
		viewHolder.gts_view_report_training_place= (TextView) convertview.findViewById(R.id.gts_view_report_training_place);
		viewHolder.gts_view_report_training_date=  (TextView) convertview.findViewById(R.id.gts_view_report_training_date);
		
		viewHolder.gts_view_report_travel_start= (TextView) convertview.findViewById(R.id.gts_view_report_travel_start);
		viewHolder.gts_view_report_travel_end=  (TextView) convertview.findViewById(R.id.gts_view_report_travel_end);
		viewHolder.gts_view_report_status = (TextView) convertview.findViewById(R.id.gts_view_report_status);
		
		viewHolder.gts_view_report_training.setText(arraylistdata.get(position).getTrainingName());
		viewHolder.gts_view_report_customer.setText(arraylistdata.get(position).getCustomerName());
		viewHolder.gts_view_report_training_place.setText(arraylistdata.get(position).getTrainingPlace());
		viewHolder.gts_view_report_training_date.setText(arraylistdata.get(position).getTrainingDate());
		viewHolder.gts_view_report_travel_start.setText(arraylistdata.get(position).getTravelStartDate());
		viewHolder.gts_view_report_travel_end.setText(arraylistdata.get(position).getTravelEndDate());
		viewHolder.gts_view_report_status.setText(arraylistdata.get(position).getActive());
		
		
		

		return convertview;
	}

}
