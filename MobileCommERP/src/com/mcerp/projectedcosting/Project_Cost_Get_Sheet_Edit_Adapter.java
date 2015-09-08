package com.mcerp.projectedcosting;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.main.R;
import com.mcerp.model.ProjectCostEditSheetModel;
import com.mcerp.model.ProjectCostGetSheetModel;

public class Project_Cost_Get_Sheet_Edit_Adapter extends BaseAdapter {
	GetSheetEditData context;
	static double data = 0.0;

	public static ArrayList<ProjectCostEditSheetModel> arraylistdata = new ArrayList<ProjectCostEditSheetModel>();

	public Project_Cost_Get_Sheet_Edit_Adapter(GetSheetEditData act,
			ArrayList<ProjectCostEditSheetModel> arraylist) {
		context = act;
		arraylistdata = arraylist;

	}

	public ArrayList<ProjectCostEditSheetModel> getList() {
		return arraylistdata;
	}

	class ViewHolder {
		public TextView month, project_code, project_name, total_cost;
		Button view_button;

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
		final ViewHolder viewHolder;

		LayoutInflater mInflater = context.getLayoutInflater();
		view = mInflater.inflate(R.layout.row_pojected_edit_get_sheet_data, null);
		viewHolder = new ViewHolder();
		viewHolder.month = (TextView) view
				.findViewById(R.id.project_cost_edit_month);
		viewHolder.project_code = (TextView) view
				.findViewById(R.id.project_cost_edit_project_code);

		viewHolder.view_button = (Button) view
				.findViewById(R.id.project_cost_view_button);
		viewHolder.project_name = (TextView) view
				.findViewById(R.id.project_cost_project_name);

		viewHolder.total_cost = (TextView) view
				.findViewById(R.id.project_cost_edit_totalcost);

		viewHolder.month.setText(arraylistdata.get(position).getMonthdate());
		viewHolder.project_code.setText(arraylistdata.get(position)
				.getProjectcode());
		viewHolder.project_name.setText(arraylistdata.get(position)
				.getProjectname());
		viewHolder.total_cost.setText(arraylistdata.get(position)
				.getTotalcost());
		viewHolder.view_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context,
						SaveAfterEditGetSheetData.class);

				intent.putExtra("CostID", arraylistdata.get(position)
						.getId());
				intent.putExtra("SheetID", arraylistdata.get(position)
						.getSheet_id());
				context.startActivity(intent);
				context.finish();

			}
		});

		return view;
	}

}