package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.TravelExpensesReport_Model;
import com.mcerp.travel.TravelExpensesTable_Report;

public class TravelExpensesReport_Adapter extends BaseAdapter {
	TravelExpensesTable_Report context;

	// ArrayAdapter<String> adapter;
	ArrayList<TravelExpensesReport_Model> arraylistdata = new ArrayList<TravelExpensesReport_Model>();

	/*
	 * public ArrayList<AssetModelData> getList() { return arraylistdata; }
	 */

	public TravelExpensesReport_Adapter(
			TravelExpensesTable_Report travelexpenses_Report,
			ArrayList<TravelExpensesReport_Model> arraylist) {
		context = travelexpenses_Report;
		arraylistdata = arraylist;

	}

	class ViewHolder {
		public TextView travel_expense_report_projec_name,
				travel_expense_report_location,
				travel_expense_report_travel_date, travel_expense_report_unit,
				travel_expense_report_unit_cost,
				travel_expense_report_transportmode_description,
				travel_expense_report_approve_unit_cost,
				travel_expense_report_status;
		/*
		 * Spinner rd; protected AssetModelData data; EditText remarks; int ref;
		 */
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

	@SuppressWarnings("null")
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
		ViewHolder viewHolder;

		if (convertView == null)
			convertView = mInflater.inflate(
					R.layout.row_travelexpenses_table_report, null);

		viewHolder = new ViewHolder();
		viewHolder.travel_expense_report_projec_name = (TextView) convertView
				.findViewById(R.id.travel_expense_report_projec_name);
		viewHolder.travel_expense_report_location = (TextView) convertView
				.findViewById(R.id.travel_expense_report_location);
		viewHolder.travel_expense_report_travel_date = (TextView) convertView
				.findViewById(R.id.travel_expense_report_travel_date);
		viewHolder.travel_expense_report_unit_cost = (TextView) convertView
				.findViewById(R.id.travel_expense_report_unit_cost);
		viewHolder.travel_expense_report_unit = (TextView) convertView
				.findViewById(R.id.travel_expense_report_unit);
		viewHolder.travel_expense_report_transportmode_description = (TextView) convertView
				.findViewById(R.id.travel_expense_report_transportmode_description);
		viewHolder.travel_expense_report_approve_unit_cost = (TextView) convertView
				.findViewById(R.id.travel_expense_report_approve_unit_cost);
		viewHolder.travel_expense_report_status = (TextView) convertView
				.findViewById(R.id.travel_expense_report_status);

		viewHolder.travel_expense_report_projec_name.setText(arraylistdata.get(
				position).getProjName());
		viewHolder.travel_expense_report_location.setText(arraylistdata.get(
				position).getLocation());
		viewHolder.travel_expense_report_travel_date.setText(arraylistdata.get(
				position).getTravelDate());
		viewHolder.travel_expense_report_unit_cost.setText(arraylistdata.get(
				position).getUnitCost());
		viewHolder.travel_expense_report_unit.setText(arraylistdata.get(
				position).getUnit());
		viewHolder.travel_expense_report_transportmode_description
				.setText(arraylistdata.get(position).getTransportModeDesc());
		viewHolder.travel_expense_report_approve_unit_cost
				.setText(arraylistdata.get(position).getApproveUnitCost());
		viewHolder.travel_expense_report_status.setText(arraylistdata.get(
				position).getStatus());

		return convertView;
	}

}
