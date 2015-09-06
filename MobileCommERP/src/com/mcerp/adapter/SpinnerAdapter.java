package com.mcerp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.TransferAssetsModel;

public class SpinnerAdapter extends ArrayAdapter<TransferAssetsModel> {

	Context activity;
	public Resources res;
	LayoutInflater inflater;
	ArrayList<TransferAssetsModel> array;

	public SpinnerAdapter(Context context, int textViewResourceId,
			ArrayList<TransferAssetsModel> objects) {
			super(context, textViewResourceId, objects);
			activity=context;
			array=objects;
			}


	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	
	public View getCustomView(int position, View convertView, ViewGroup parent) {

		/********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
		View row = inflater.inflate(R.layout.row_spinner, parent, false);

		
		TextView sub = (TextView) row.findViewById(R.id.sub);

		
		return row;
	}
}
