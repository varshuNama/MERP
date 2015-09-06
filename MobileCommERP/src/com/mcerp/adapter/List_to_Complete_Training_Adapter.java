package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mcerp.asyncheck.AsynctaskCompleteTrainingButton;
import com.mcerp.gts.Complete_Training;
import com.mcerp.main.R;
import com.mcerp.model.List_To_Complete_Training_model;
import com.mcerp.util.AppPreferences;

public class List_to_Complete_Training_Adapter extends BaseAdapter  {
	Complete_Training context;
	AppPreferences prefs;
	String trainingid;

	// ArrayAdapter<String> adapter;
	ArrayList<List_To_Complete_Training_model> arraylistdata = new ArrayList<List_To_Complete_Training_model>();

	

	public List_to_Complete_Training_Adapter(
			Complete_Training act,
			ArrayList<List_To_Complete_Training_model> arraylist) {
		context = act;
		arraylistdata = arraylist;

	}
	
	public ArrayList<List_To_Complete_Training_model> getList() {
		return arraylistdata;
	}


	class ViewHolder {
		public TextView list_to_complete_training_name,
				list_to_complete_training_location,
				list_to_copmlete_training_start, list_to_copmlete_training_end;

		public Button list_to_copmlete_training_btn;
		
		
		
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
		
		convertView = mInflater.inflate(R.layout.row_list_to_complete_training, null);


		
		viewHolder = new ViewHolder();
		viewHolder.list_to_complete_training_name = (TextView) convertView
				.findViewById(R.id.list_to_complete_training_name);
		viewHolder.list_to_complete_training_location = (TextView) convertView
				.findViewById(R.id.list_to_complete_training_location);
		viewHolder.list_to_copmlete_training_start = (TextView) convertView
				.findViewById(R.id.list_to_copmlete_training_start);
		viewHolder.list_to_copmlete_training_end = (TextView) convertView
				.findViewById(R.id.list_to_copmlete_training_end);
		viewHolder.list_to_copmlete_training_btn = (Button) convertView
				.findViewById(R.id.list_to_copmlete_training_btn);
		
		
		viewHolder.list_to_copmlete_training_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				prefs=AppPreferences.getInstance(context);
				
				
				new AsynctaskCompleteTrainingButton(context,prefs.getUserID(),arraylistdata.get(position).getId()).execute();
				
			}
		});
		

		viewHolder.list_to_complete_training_name.setText(arraylistdata.get(
				position).getTraining());
		viewHolder.list_to_complete_training_location.setText(arraylistdata
				.get(position).getLocation());
		viewHolder.list_to_copmlete_training_start.setText(arraylistdata.get(
				position).getTrainingStart());
		viewHolder.list_to_copmlete_training_end.setText(arraylistdata.get(
				position).getTrainingEnd());

		return convertView;
	}

}
