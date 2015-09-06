package com.mcerp.adapter;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.Approvetravelmodel;
import com.mcerp.travel.ApproveTraveldata;
import com.mcerp.travel.ApproveTravelView;


public  class ViewtraveldataAdapter extends BaseAdapter {
	ApproveTravelView context;

	ArrayList<Approvetravelmodel> arraylistdata = new ArrayList<Approvetravelmodel>();

	/*public ArrayList<AssetModelData> getList() {
		return arraylistdata;
	}*/

	public ViewtraveldataAdapter(ApproveTravelView act,
			ArrayList<Approvetravelmodel> arraylist) {
		context = act;
		arraylistdata = arraylist;


	}

	class ViewHolder {
		public TextView project_travel_empid, project_travel_email, project_travel_empcode, project_travel_empname, travel_kmm_txt,travel_amount_txt;
		public LinearLayout  viewdata_travel;
		
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			LayoutInflater mInflater = context.getLayoutInflater();
			view = mInflater.inflate(R.layout.view_travel_details_row,
					null);
			final ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.project_travel_empcode = (TextView) view.findViewById(R.id.project_travel_empcode);

			viewHolder.project_travel_empname = (TextView) view.findViewById(R.id.project_travel_empname);

			viewHolder.travel_kmm_txt = (TextView) view.findViewById(R.id.travel_kmm_txt);

			viewHolder.travel_amount_txt = (TextView) view.findViewById(R.id.travel_amount_txt);
			viewHolder.viewdata_travel = (LinearLayout) view.findViewById(R.id.viewdata_travel);
			viewHolder.viewdata_travel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(context,ApproveTraveldata.class);
					//Create the bundle
					Bundle bundle = new Bundle();
					//Add your data from getFactualResults method to bundle
					bundle.putString("EmpEmail", arraylistdata.get(position).getEmail());
					bundle.putString("EmpName", arraylistdata.get(position).getEmpName());
					bundle.putString("EmpId", arraylistdata.get(position).getEmpId());
					
				
					//Add the bundle to the intent
					i.putExtras(bundle);
					context.startActivity(i);
					context.finish();
					
				}
			});
					
			view.setTag(viewHolder);

		} 

		ViewHolder viewHolder = (ViewHolder) view.getTag();

		
		
		viewHolder.project_travel_empcode.setText(arraylistdata.get(position).getEmpCode());
		viewHolder.project_travel_empname.setText(arraylistdata.get(position).getEmpName());
		viewHolder.travel_kmm_txt.setText(arraylistdata.get(position).getTravelKm());
		viewHolder.travel_amount_txt.setText(arraylistdata.get(position).getTravelAmount());
		

		return view;
	}

}
