package com.mcerp.projectedcosting;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mcerp.main.R;
import com.mcerp.model.ProjectCostReortModel;

public class Project_Cost_Report_Adapter extends BaseAdapter {
	Project_Costing_Report context;
	

	public static ArrayList<ProjectCostReortModel> arraylistdata = new ArrayList<ProjectCostReortModel>();

	public Project_Cost_Report_Adapter(
			Project_Costing_Report act,
			ArrayList<ProjectCostReortModel> arraylist) {
		context = act;
		arraylistdata = arraylist;

	}

	
	class ViewHolder {
		public TextView monthYear,projectcode,projectname,netcost,projectcost,prefwdcost,actualcost,gap;
		
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
		final ViewHolder viewHolder;
		
		LayoutInflater mInflater = context.getLayoutInflater();
		view = mInflater.inflate(R.layout.row_project_cost_report, null);
		viewHolder = new ViewHolder();
		
		viewHolder.monthYear = (TextView) view.findViewById(R.id.monthyearreport);
		viewHolder.projectcode = (TextView) view.findViewById(R.id.projectcodereport);
		viewHolder.projectname = (TextView) view.findViewById(R.id.projectnamereport);
		viewHolder.netcost = (TextView) view.findViewById(R.id.net_cost);
		viewHolder.projectcost = (TextView) view.findViewById(R.id.projectcostreport);	
		viewHolder.prefwdcost = (TextView) view.findViewById(R.id.prefwd);
		viewHolder.actualcost = (TextView) view.findViewById(R.id.actual_cost);
		viewHolder.gap = (TextView) view.findViewById(R.id.gap);

   
		viewHolder.monthYear.setText(arraylistdata.get(position).getMonthYear());
		viewHolder.projectcode.setText(arraylistdata.get(position).getProjCode());
		viewHolder.projectname.setText(arraylistdata.get(position).getProjname());
		viewHolder.netcost.setText(arraylistdata.get(position).getNetProjectedCost());
		viewHolder.projectcost.setText(arraylistdata.get(position).getProjctedCost());
		viewHolder.prefwdcost.setText(arraylistdata.get(position).getPFwdCost());
		viewHolder.actualcost.setText(arraylistdata.get(position).getActualCost());
		viewHolder.gap.setText(arraylistdata.get(position).getCostGap());

		
		return view;
	}

	
}
