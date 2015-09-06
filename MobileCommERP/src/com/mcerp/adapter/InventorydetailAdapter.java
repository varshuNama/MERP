package com.mcerp.adapter;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.mcerp.assets.Inventorydetails;
import com.mcerp.main.R;
import com.mcerp.model.InventoryModel;

public class InventorydetailAdapter extends BaseAdapter {
	Inventorydetails act;
	ListView inventory_listview;
	public static ArrayList<InventoryModel> arraylistdata = new ArrayList<InventoryModel>();
	private ArrayList<InventoryModel> dummyList;
	static int scanPosition;
	Uri mLastPhoto = null;
	final static int REQUEST_TAKE_PICTURE = 1000;
	private static final int REQUEST_SEND_IMAGE = 2000;
	public InventorydetailAdapter(Inventorydetails act,
			ArrayList<InventoryModel> arraylist, ListView inventory_listview) {
		this.inventory_listview=inventory_listview;
		this.act = act;
		arraylistdata = arraylist;

	}


	public ArrayList<InventoryModel> getList() { return
			arraylistdata; }

	public InventorydetailAdapter() {

	}

	class ViewHolder {
		public TextView assert_name_txt, assert_categoryname_txt,
		assert_sr_no_txt, assert_tag_no_txt;
		public ImageView img_photo;
		
		EditText edit_barcode;

		CheckBox checkboxstatus;
		LinearLayout inventory_scan_btn_layout;
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
		ViewHolder viewHolder;

		LayoutInflater mInflater = act.getLayoutInflater();
		view = mInflater.inflate(R.layout.inventory_row_layout, null);
		viewHolder = new ViewHolder();
		viewHolder.img_photo=(ImageView) view.findViewById(R.id.img_photo);
		viewHolder.assert_name_txt = (TextView) view
				.findViewById(R.id.inventory_assert_type_txt);

		viewHolder.assert_categoryname_txt = (TextView) view
				.findViewById(R.id.inventory_category_txt);

		viewHolder.assert_sr_no_txt = (TextView) view
				.findViewById(R.id.inventory_sr_no_txt);

		viewHolder.assert_tag_no_txt = (TextView) view
				.findViewById(R.id.inventory_tag_no_txt);

		viewHolder.edit_barcode = (EditText) view
				.findViewById(R.id.inventory_barcode_edit);

		viewHolder.checkboxstatus = (CheckBox) view
				.findViewById(R.id.inventory_checkbox);
		viewHolder.inventory_scan_btn_layout = (LinearLayout) view
				.findViewById(R.id.inventory_scan_btn_layout);
		viewHolder.img_photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//startPhotoTaker();
				
			}
		});

		viewHolder.inventory_scan_btn_layout
		.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				scanPosition=position;
				Intent i = new Intent(act, CaptureActivity.class);
				act.startActivityForResult(i,1);

			}
		});

		viewHolder.checkboxstatus
		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked == true) {
					arraylistdata.get(position)
					.setCheckbox_status(true);
				} else {
					arraylistdata.get(position).setCheckbox_status(
							false);
				}

			}
		});

		viewHolder.assert_name_txt.setText(arraylistdata.get(position)
				.getAssert_name());
		viewHolder.assert_categoryname_txt.setText(arraylistdata.get(position)
				.getAssert_categoryname());
		viewHolder.assert_sr_no_txt.setText(arraylistdata.get(position)
				.getAssert_sr_no());
		viewHolder.assert_tag_no_txt.setText(arraylistdata.get(position)
				.getAssert_tag_no());
		viewHolder.edit_barcode.setText(arraylistdata.get(position)
				.getAssert_barcode());

		viewHolder.checkboxstatus.setChecked(arraylistdata.get(position)
				.getCheckbox_status());

		return view;
	}
	


	public void test(String barcodeMsg) {
		try{
		InventoryModel inventoryModel=arraylistdata.get(InventorydetailAdapter.scanPosition);
		InventoryModel inventoryreplace=new InventoryModel();
		inventoryreplace.setAssetStockId(inventoryModel.getAssetStockId());
		inventoryreplace.setAssert_name(inventoryModel.getAssert_name());
		inventoryreplace.setAssert_categoryname(inventoryModel.getAssert_categoryname());
		inventoryreplace.setAssert_sr_no(inventoryModel.getAssert_sr_no());
		inventoryreplace.setAssert_tag_no(inventoryModel.getAssert_tag_no());	
		inventoryreplace.setAcceptStatus(inventoryModel.getAcceptStatus());
		inventoryreplace.setAssetIssueDate(inventoryModel.getAssetIssueDate());
		inventoryreplace.setAssert_barcode(barcodeMsg);
		
        arraylistdata.set(InventorydetailAdapter.scanPosition, inventoryreplace);
	
		notifyDataSetChanged();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}