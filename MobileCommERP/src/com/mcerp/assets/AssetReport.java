package com.mcerp.assets;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.AssetReportDataAdapter;
import com.mcerp.asyncheck.AsyncTaskAssetIssuedReport;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;

public class AssetReport extends Activity implements OnClickListener {
	LinearLayout backAsset, linearHeader;

	ListView listViewAsset;
	ConnectionDetector connection;
	AssetReportDataAdapter adapter;
	Button getDetail;
	ArrayList<AssetModelData> arrayData;
	TextView assetTYPE, assetCATEGORY, assetSRNO, assetTAGNO, assetISUDATE,
			assetREMARKS, norecord, titlename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asset_report);
		new AsyncTaskAssetIssuedReport(AssetReport.this).execute();
		init();

	}

	private void init() {

		listViewAsset = (ListView) findViewById(R.id.listAssetsView);
		linearHeader = (LinearLayout) findViewById(R.id.assetsHeader);
		backAsset = (LinearLayout) findViewById(R.id.backasset);
		titlename = (TextView) findViewById(R.id.title);
		norecord = (TextView) findViewById(R.id.noRecordAsset);

		backAsset.setOnClickListener(this);

	}

	public void callAssetReportSuccessAdapter(
			ArrayList<AssetModelData> reportdata) {
		arrayData = reportdata;
		listViewAsset.setVisibility(View.VISIBLE);
		linearHeader.setVisibility(View.VISIBLE);
		norecord.setVisibility(View.GONE);
		if ( arrayData != null
				&& arrayData.size() > 0) {
			adapter = new AssetReportDataAdapter(AssetReport.this, arrayData,"ASSETREPORT");
			listViewAsset.setAdapter(adapter);
		}

	}

	public void callAssetReportFailAdapter(String responsedata) {
		listViewAsset.setVisibility(View.GONE);
		linearHeader.setVisibility(View.GONE);
		norecord.setVisibility(View.VISIBLE);
		norecord.setText(responsedata);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(AssetReport.this, NavigationActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.backasset:
			Intent intent = new Intent(AssetReport.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

}
