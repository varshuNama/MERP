package com.mcerp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.constant.Constant;
import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;

public class AssetReportDataAdapter extends BaseAdapter {

	Context con;
	String flag;
	ArrayList<AssetModelData> arraylist = new ArrayList<AssetModelData>();
	int selectedPosition = 0;
	LayoutInflater mInflater;

	public ArrayList<AssetModelData> getList() {
		return arraylist;
	}

	public AssetReportDataAdapter(Context context,
			ArrayList<AssetModelData> array, String check) {
		super();
		con = context;
		arraylist = array;
		flag = check;
		mInflater = ((Activity) context).getLayoutInflater();

	}

	class ViewHolder {

		public TextView data1, data2, data3, data4, data5, data6, data7;
		RadioButton rd;
		
	}

	@Override
	public int getCount() {
		return arraylist.size();
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
	public View getView(final int position, View row, ViewGroup parent) {

		ViewHolder holder =  new ViewHolder();
		if ((flag.equals("ASSETREPORT")))

		{
			
			if (row == null)
				row = mInflater.inflate(R.layout.row_asset_report, null);

			holder.data1 = (TextView) row.findViewById(R.id.item1_assets);
			holder.data2 = (TextView) row.findViewById(R.id.item2_assets);
			holder.data3 = (TextView) row.findViewById(R.id.item3_assets);
			holder.data4 = (TextView) row.findViewById(R.id.item4_assets);
			holder.data5 = (TextView) row.findViewById(R.id.item5_assets);
			holder.data6 = (TextView) row.findViewById(R.id.item6_assets);
			holder.data7 = (TextView) row.findViewById(R.id.item7_assets);

			holder.data1.setText(arraylist.get(position).getAssetType());
			holder.data2.setText(arraylist.get(position).getCategory());
			holder.data3.setText(arraylist.get(position).getSerialNumber());
			holder.data4.setText(arraylist.get(position).getTagNumber());
			holder.data5.setText(arraylist.get(position).getIssuedDate());
			holder.data6.setText(arraylist.get(position).getRemarks());
			holder.data7
					.setText(arraylist.get(position).getPendingTransferTo());

		} else if ((flag.equals("ACCEPTASSET"))) {

			if (row == null)
				row = mInflater.inflate(R.layout.row_accept_assets, null);

			holder.data1 = (TextView) row.findViewById(R.id.asset_item1);
			holder.data2 = (TextView) row.findViewById(R.id.asset_item2);
			holder.data3 = (TextView) row.findViewById(R.id.asset_item3);
			holder.data4 = (TextView) row.findViewById(R.id.asset_item4);
			holder.rd = (RadioButton) row.findViewById(R.id.rd_asset);
			holder.rd.setChecked(position == selectedPosition);
			holder.rd.setTag(position);

			arraylist.get(position).setCheckBoxChecked(false);

			if (position == selectedPosition) {
				holder.rd.setChecked(position == selectedPosition);

				arraylist.get(position).setCheckBoxChecked(true);
			}

			holder.rd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					selectedPosition = position;
					Constant.Accept_Select_Radio_item_position = selectedPosition;
				notifyDataSetChanged();
				}
			});

			String text = " Date : " + arraylist.get(position).getAssetDate()
					+ " form :" + arraylist.get(position).getTransferFrom()
					+ " type : " + arraylist.get(position).getTrnsfrType()
					+ " asset : " + arraylist.get(position).getTotalAsset();
			Toast.makeText(con, text, Toast.LENGTH_LONG).show();
			holder.data1.setText(arraylist.get(position).getAssetDate());
			holder.data2.setText(arraylist.get(position).getTransferFrom());
			holder.data3.setText(arraylist.get(position).getTrnsfrType());
			holder.data4.setText(arraylist.get(position).getTotalAsset());
		}

		return row;
	}
}
