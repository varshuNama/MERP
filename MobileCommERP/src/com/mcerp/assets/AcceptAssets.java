package com.mcerp.assets;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.AssetReportDataAdapter;
import com.mcerp.asyncheck.AsyncTaskAcceptAssetDetail;
import com.mcerp.asyncheck.AsyncTaskGetAcceptAssets;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;

public class AcceptAssets extends Activity implements OnClickListener{

	String[] strEmp = { "Admin", "Other Employee" };
	LinearLayout back,acceptheader;
	TextView norecord,norecordinaccept;
	ListView listview;
	Button acceptdetail;
	LinearLayout viewassetss;

	AssetReportDataAdapter adapter;
	ConnectionDetector connection;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_asset);
		init();
		new AsyncTaskGetAcceptAssets(AcceptAssets.this).execute();

	}

	private void init() {

		back=(LinearLayout) findViewById(R.id.bkasset);
		connection=new ConnectionDetector(this);
		norecord=(TextView) findViewById(R.id.noRecordAcceptAssets);
		listview=(ListView) findViewById(R.id.listAcceptAssetsView);
		acceptheader=(LinearLayout) findViewById(R.id.acceptassetsHeader);
		norecordinaccept=(TextView) findViewById(R.id.norecordinaccept);
		viewassetss=(LinearLayout) findViewById(R.id.viewDataAssets);
		back.setOnClickListener(this);
		viewassetss.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bkasset:
			Intent intent=new Intent(AcceptAssets.this,NavigationActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.viewDataAssets:
			String strAssetId = null,strTransferFrom = null,strTransferType = null;;
			if (adapter != null) {

				for (Iterator<AssetModelData> i = (adapter.getList())
						.iterator(); i.hasNext();) {
					AssetModelData item = i.next();
					if(item.isCheckBoxChecked())
					{
						strAssetId=item.getAssetId();
						strTransferFrom=item.getEmpCode();
						strTransferType=item.getTrnsfrType();
					}

				}

			}
			if (connection.isConnectingToInternet())
				new AsyncTaskAcceptAssetDetail(AcceptAssets.this,strAssetId,strTransferFrom,strTransferType).execute();
			else {
				new SweetAlertDialog(AcceptAssets.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}

			break;

		default:
			break;
		}


	}
	public void callAcceptAssetAdapter(ArrayList<AssetModelData> arraylist,String responsedata){
		if (arraylist.size() != 0 && arraylist != null) {
			listview.setVisibility(View.VISIBLE);
			acceptheader.setVisibility(View.VISIBLE);
			norecord.setVisibility(View.GONE);
			viewassetss.setVisibility(View.VISIBLE);
			adapter = new AssetReportDataAdapter(AcceptAssets.this,arraylist,"ACCEPTASSET");
			listview.setAdapter(adapter);

		} else {
			listview.setVisibility(View.GONE);
			acceptheader.setVisibility(View.GONE);
			viewassetss.setVisibility(View.GONE);
			norecord.setVisibility(View.VISIBLE);
			norecord.setText(responsedata);

		}

	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent=new Intent(AcceptAssets.this,NavigationActivity.class);
		startActivity(intent);
		finish();
	}

}
