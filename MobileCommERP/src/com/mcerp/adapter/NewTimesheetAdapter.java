package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.NewTimesheetModel;

public class NewTimesheetAdapter extends ArrayAdapter<NewTimesheetModel> {

	Activity context;
	ArrayList<NewTimesheetModel> arraylist = new ArrayList<NewTimesheetModel>();

	public ArrayList<NewTimesheetModel> getList() {
		return arraylist;
	}

	public void updateResults(ArrayList<NewTimesheetModel> results) {
		arraylist = results;
		notifyDataSetChanged();
	}

	public NewTimesheetAdapter(Activity context,
			ArrayList<NewTimesheetModel> results) {
		super(context, R.layout.row_timesheet, results);
		this.context = context;
		arraylist = results;
	}

	private class ViewHolder {
		TextView txtda, txtdate, txtday, txtleave, txtstatus;
		EditText activity, descp;
		int ref;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {

			viewHolder = new ViewHolder();
			LayoutInflater inflator = context.getLayoutInflater();

			convertView = inflator.inflate(R.layout.row_timesheet, null);
			viewHolder.txtda = (TextView) convertView
					.findViewById(R.id.sheetda);
			viewHolder.txtdate = (TextView) convertView
					.findViewById(R.id.sheetdate);
			viewHolder.txtday = (TextView) convertView
					.findViewById(R.id.shaeetday);
			viewHolder.txtleave = (TextView) convertView
					.findViewById(R.id.sheetleaveholiday);
			viewHolder.txtstatus = (TextView) convertView
					.findViewById(R.id.sheetstatus);
			viewHolder.activity = (EditText) convertView
					.findViewById(R.id.sheetactivity);
			viewHolder.descp = (EditText) convertView
					.findViewById(R.id.sheetdescription);
			convertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ref = position;
		viewHolder.activity.setText(arraylist.get(position).getTSActivity());
		viewHolder.descp.setText(arraylist.get(position).getTSDescription());
		viewHolder.txtda.setText(arraylist.get(position).getDA());
		viewHolder.txtdate.setText(arraylist.get(position).getTimsheetDate());
		viewHolder.txtday.setText(arraylist.get(position).getTimsheetWeekDay());
		viewHolder.txtleave.setText(arraylist.get(position).getLHStatus());
		viewHolder.txtstatus.setText(arraylist.get(position).getTSStatusDesc());

		viewHolder.activity.addTextChangedListener(new TextWatcher() {

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
				// TODO Auto-generated method stub
				arraylist.get(viewHolder.ref).setTSActivity(arg0.toString());
			}
		});

		viewHolder.descp.addTextChangedListener(new TextWatcher() {

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
				// TODO Auto-generated method stub
				arraylist.get(viewHolder.ref).setTSDescription(arg0.toString());
			}
		});

		return convertView;
	}

}