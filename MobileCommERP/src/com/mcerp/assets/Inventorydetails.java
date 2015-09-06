package com.mcerp.assets;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcerp.adapter.InventorydetailAdapter;
import com.mcerp.asyncheck.GetInventorydetailsAsk;
import com.mcerp.asyncheck.SubmitInventorydetailsAsk;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.InventoryModel;
import com.mcerp.util.AppPreferences;

public class Inventorydetails extends Activity implements OnClickListener{
	LinearLayout backinventory_btn;
	ListView inventory_listview;
	TextView inventory_login_username,inventory_login_user_empcode;
	AppPreferences prefs;
	static InventorydetailAdapter adapter;
	LinearLayout inventory_senddata;
	ArrayList<String>  arrayAssetStockIdlist=new ArrayList<String>();
	ArrayList<String>  arrayAssetRemarkslist=new ArrayList<String>();
	ArrayList<String>  arrayAssetBarCodelist=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_main_layout);
		init();
		new GetInventorydetailsAsk(Inventorydetails.this).execute();
	}

	private void init()
	{
		prefs=AppPreferences.getInstance(Inventorydetails.this);
		inventory_login_username=(TextView) findViewById(R.id.inventory_login_username);
		inventory_login_user_empcode=(TextView) findViewById(R.id.inventory_login_user_empcode);
		backinventory_btn=(LinearLayout) findViewById(R.id.backinventory_btn);
		inventory_login_username.setText(prefs.getEmpName());
		inventory_login_user_empcode.setText(prefs.getEmpCode());

		backinventory_btn.setOnClickListener(this);
	}

	public void getinventorylist_data(Inventorydetails act,
			ArrayList<InventoryModel> inventorydata_model_list) {
		inventory_listview = (ListView) act
				.findViewById(R.id.inventory_listview);
		backinventory_btn = (LinearLayout) act
				.findViewById(R.id.backinventory_btn);

		adapter = new InventorydetailAdapter(act, inventorydata_model_list,inventory_listview);
		inventory_listview.setAdapter(adapter);
		inventory_senddata=(LinearLayout) findViewById(R.id.inventory_senddata);
		inventory_senddata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ArrayList<InventoryModel> updatedlistdata=adapter.getList();
				for(int i=0;i<=updatedlistdata.size()-1;i++){

					if(updatedlistdata.get(i).getCheckbox_status()==true)
					{
						arrayAssetStockIdlist.add(updatedlistdata.get(i).getAssetStockId());
						arrayAssetRemarkslist.add("test");
						//arrayAssetBarCodelist.add(updatedlistdata.get(i).getAssert_barcode());
						arrayAssetBarCodelist.add(updatedlistdata.get(i).getAssert_barcode());
					}
				}
				if(arrayAssetStockIdlist.size()==0){
					Toast.makeText(Inventorydetails.this, "please check at least one assert", Toast.LENGTH_SHORT).show();
				}
				else{
					new SubmitInventorydetailsAsk(Inventorydetails.this,prefs.getUserID(),arrayAssetStockIdlist,arrayAssetRemarkslist,arrayAssetBarCodelist).execute();
				}

			}
		});
	}
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	{  
		super.onActivityResult(requestCode, resultCode, data);  
		// check if the request code is same as what is passed  here it is 2  
		if(resultCode==1)  
		{  
			String message=data.getStringExtra("MESSAGE");   
			System.out.println(message);

			adapter.test(message);

		}  
	}  
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(Inventorydetails.this, NavigationActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.backinventory_btn:
			Intent intent = new Intent(Inventorydetails.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}
}
