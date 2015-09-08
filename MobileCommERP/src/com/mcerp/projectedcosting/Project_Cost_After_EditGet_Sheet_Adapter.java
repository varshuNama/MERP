package com.mcerp.projectedcosting;

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
import android.widget.TextView;

import com.mcerp.main.R;
import com.mcerp.model.ProjectCostAfterEditSaveModel;

public class Project_Cost_After_EditGet_Sheet_Adapter extends BaseAdapter {
	SaveAfterEditGetSheetData context;
	static double data = 0.0;

	public static ArrayList<ProjectCostAfterEditSaveModel> arraylistdata = new ArrayList<ProjectCostAfterEditSaveModel>();

	public Project_Cost_After_EditGet_Sheet_Adapter(
			SaveAfterEditGetSheetData act,
			ArrayList<ProjectCostAfterEditSaveModel> arraylist) {
		context = act;
		arraylistdata = arraylist;

	}

	public ArrayList<ProjectCostAfterEditSaveModel> getList() {
		return arraylistdata;
	}

	class ViewHolder {
		public TextView resource_id, total_cost;
		EditText quantity, unit_price;

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
		// if (convertView == null) {

		LayoutInflater mInflater = context.getLayoutInflater();
		view = mInflater.inflate(
				R.layout.row_pojected_after_edit_save_get_sheet_data, null);
		viewHolder = new ViewHolder();
		viewHolder.resource_id = (TextView) view
				.findViewById(R.id.edit_project_cost_getsheet_resourceid);
		viewHolder.total_cost = (TextView) view
				.findViewById(R.id.edit_project_cost_getsheet_totalprice);
		viewHolder.quantity = (EditText) view
				.findViewById(R.id.edit_project_cost_getsheet_quantity);

		viewHolder.unit_price = (EditText) view
				.findViewById(R.id.edit_project_cost_getsheet_uitprice);

		viewHolder.checkboxstatus = (CheckBox) view
				.findViewById(R.id.edit_approve_travel_details_checkbox);

		viewHolder.checkboxstatus
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked == true) {
							arraylistdata.get(position).setCheckboxstatus(true);
						} else {
							arraylistdata.get(position)
									.setCheckboxstatus(false);
						}

					}
				});

		viewHolder.quantity.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				viewHolder.total_cost.setText(addNumbers(viewHolder));
				arraylistdata.get(position)
						.setTatalcost(addNumbers(viewHolder));

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).setQantity(arg0.toString());

			}
		});

		viewHolder.unit_price.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				viewHolder.total_cost.setText(addNumbers(viewHolder));
				arraylistdata.get(position)
						.setTatalcost(addNumbers(viewHolder));
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				arraylistdata.get(position).setUnit_test(arg0.toString());

			}
		});

		viewHolder.resource_id.setText(arraylistdata.get(position)
				.getResourceName());
		viewHolder.quantity.setText(arraylistdata.get(position).getQantity());
		viewHolder.unit_price.setText(arraylistdata.get(position)
				.getUnit_test());
		viewHolder.checkboxstatus.setChecked(arraylistdata.get(position)
				.isCheckboxstatus());
		viewHolder.total_cost.setText(arraylistdata.get(position)
				.getTatalcost());
		return view;
	}

	private String addNumbers(ViewHolder viewHolder) {

		String result = "0";
		try {
			int number1;
			int number2;
			if (viewHolder.quantity.getText().toString() != ""
					&& viewHolder.quantity.getText().length() > 0) {
				number1 = Integer.parseInt(viewHolder.quantity.getText()
						.toString());
			} else {
				number1 = 0;
			}
			if (viewHolder.unit_price.getText().toString() != ""
					&& viewHolder.unit_price.getText().length() > 0) {
				number2 = Integer.parseInt(viewHolder.unit_price.getText()
						.toString());
			} else {
				number2 = 0;
			}

			result = Integer.toString(number1 * number2);
		} catch (Exception e) {
              e.printStackTrace();
		}
		return result;
	}
}
