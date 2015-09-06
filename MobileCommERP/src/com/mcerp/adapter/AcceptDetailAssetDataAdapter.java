package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;

public class AcceptDetailAssetDataAdapter extends BaseAdapter {
	Context context;

	ArrayAdapter<String> adapter;
	ArrayList<AssetModelData> arraylistdata = new ArrayList<AssetModelData>();

	public ArrayList<AssetModelData> getList() {
		return arraylistdata;
	}

	public AcceptDetailAssetDataAdapter(Context acceptDetail,
			ArrayList<AssetModelData> arraylist) {
		context = acceptDetail;
		arraylistdata = arraylist;


	}

	class ViewHolder {
		public TextView data1, data2, data3, data4;
		Spinner rd;
		protected AssetModelData data;
		EditText remarks;
		int ref;
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
		View view = null;

		if (convertView == null) {
			LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
			view = mInflater.inflate(R.layout.row_accept_detail_assets,
					null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.data = new AssetModelData(context);
			viewHolder.data1 = (TextView) view.findViewById(R.id.acceptdetail1);
			viewHolder.data2 = (TextView) view
					.findViewById(R.id.acceptdetail2);
			viewHolder.data3 = (TextView) view
					.findViewById(R.id.acceptdetail3);
			viewHolder.data4 = (TextView) view
					.findViewById(R.id.acceptdetail4);
			viewHolder.rd = (Spinner) view
					.findViewById(R.id.spinner_approve_reject);
			viewHolder.rd.setAdapter(viewHolder.data.getAdapter());
			viewHolder.rd.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					viewHolder.data.setSelected(arg2);   
					if(arg2==0)
					arraylistdata.get(position).setSelected(2);
					else if(arg2==1)
					{
						arraylistdata.get(position).setSelected(3);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}

			});

			viewHolder.remarks = (EditText) view
					.findViewById(R.id.remarks_detail);

			viewHolder.remarks.addTextChangedListener(new TextWatcher() {

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
			view.setTag(viewHolder);

		} 

		ViewHolder viewHolder = (ViewHolder) view.getTag();

		viewHolder.rd.setSelection(arraylistdata.get(position).getSelected());
		viewHolder.data1.setText(arraylistdata.get(position).getAssetName_D());
		viewHolder.data2.setText(arraylistdata.get(position).getCategoryName_D());
		viewHolder.data3.setText(arraylistdata.get(position).getAssetSrNo_D());
		viewHolder.data4.setText(arraylistdata.get(position).getAssetTagNo_D());

		return view;
	}

}
