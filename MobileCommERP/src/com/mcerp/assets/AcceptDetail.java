package com.mcerp.assets;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.AcceptDetailAssetDataAdapter;
import com.mcerp.asyncheck.AsyncTaskTransferAssets;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.model.AssetModelData;
import com.mcerp.util.AppPreferences;

public class AcceptDetail extends Activity implements OnClickListener {
	String responseData, message, transtype;
	ArrayList<AssetModelData> arraylist = new ArrayList<AssetModelData>();
	ListView listacceptasset;
	ConnectionDetector connectiondetector;
	LinearLayout backbtn, acceptassetbtn, acceptdetailhdr;
	TextView norecord;
	int flag = 0;
	SweetAlertDialog dialog, mydialog2;
	ArrayList<String> arrayCategoryName;
	ArrayList<String> arrayAssetName;
	ArrayList<String> arrayAssetSrNo;
	ArrayList<String> arrayAssetTagNo;
	ArrayList<String> arrayAssetRemarks;
	ArrayList<String> arrayAssetStatus;
	ArrayList<String> arrayAssetIssueId;
	ArrayList<String> arrayAssetDetailId;
	ArrayList<String> arrayAssetKeyId;
	String flagdata;

	AppPreferences prefs;
	AcceptDetailAssetDataAdapter adapter;
	String responsData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_detail);
		init();

	}

	private void init() {
		listacceptasset = (ListView) findViewById(R.id.listacceptasset);
		responseData = getIntent().getExtras().getString("RESPONSE");
		connectiondetector = new ConnectionDetector(this);
		backbtn = (LinearLayout) findViewById(R.id.detailback);
		acceptassetbtn = (LinearLayout) findViewById(R.id.senddata);
		acceptdetailhdr = (LinearLayout) findViewById(R.id.acceptdetailhdr);
		norecord = (TextView) findViewById(R.id.noRecordacceptDetail);
		message = getIntent().getExtras().getString("MESSAGE");
		transtype = getIntent().getExtras().getString("TRANSFERTYPE");
		prefs = AppPreferences.getInstance(this);
		backbtn.setOnClickListener(this);
		acceptassetbtn.setOnClickListener(this);

		try {
			parseData();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (arraylist.size() != 0 && arraylist != null) {
			listacceptasset.setVisibility(View.VISIBLE);
			acceptdetailhdr.setVisibility(View.VISIBLE);
			norecord.setVisibility(View.GONE);

			adapter = new AcceptDetailAssetDataAdapter(AcceptDetail.this,
					arraylist);
			listacceptasset.setAdapter(adapter);

		} else {
			listacceptasset.setVisibility(View.GONE);
			acceptdetailhdr.setVisibility(View.GONE);
			norecord.setVisibility(View.VISIBLE);
			norecord.setText(responseData);

		}

	}

	private void parseData() throws JSONException {
		if (message.equals("success")) {

			JSONArray jsonarray = new JSONArray(responseData);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject json = jsonarray.getJSONObject(i);
				AssetModelData data = new AssetModelData(AcceptDetail.this);
				data.setId_D(json.getString("ID"));
				data.setDetailId_D(json.getString("DetailID"));
				data.setAssetName_D(json.getString("AssetName"));
				data.setCategoryName_D(json.getString("categoryName"));
				data.setProjectCode_D(json.getString("ProjectCode"));
				data.setProjMgrEmail_D(json.getString("ProjMgrEmail"));
				data.setProjmgrcode_D(json.getString("ProjMgrCode"));
				data.setProjName_D(json.getString("ProjName"));
				data.setCircleId_D(json.getString("CircleId"));
				data.setCircleName_D(json.getString("CircleName"));
				data.setProjMgrName_D(json.getString("ProjMgrName"));
				data.setTransferFrom_D(json.getString("TransferFrom"));
				data.setIssuedBy_D(json.getString("IssuedBy"));
				data.setIssuedByEmail_D(json.getString("IssuedByEmail"));
				data.setAssetIssueKey_D(json.getString("AssetIssueKey"));
				data.setAssetSrNo_D(json.getString("AssetSrNo"));
				data.setAssetTagNo_D(json.getString("AssetTagNo"));
				data.setAssetDate_D(json.getString("AssetDate"));

				arraylist.add(data);

			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.senddata:
			arrayCategoryName = new ArrayList<String>();
			arrayAssetName = new ArrayList<String>();
			arrayAssetSrNo = new ArrayList<String>();
			arrayAssetTagNo = new ArrayList<String>();
			arrayAssetRemarks = new ArrayList<String>();
			arrayAssetStatus = new ArrayList<String>();
			arrayAssetIssueId = new ArrayList<String>();
			arrayAssetDetailId = new ArrayList<String>();
			arrayAssetKeyId = new ArrayList<String>();
			if (adapter != null) {
				for (Iterator<AssetModelData> i = (adapter.getList())
						.iterator(); i.hasNext();) {
					AssetModelData item = i.next();
					arrayCategoryName.add(item.getCategoryName_D());
					arrayAssetName.add(item.getAssetName_D());
					arrayAssetSrNo.add(item.getAssetSrNo_D());
					arrayAssetTagNo.add(item.getAssetTagNo_D());
					arrayAssetRemarks.add(item.getRemarks());
					arrayAssetStatus.add(item.getSelected() + "");
					arrayAssetIssueId.add(item.getId_D());
					arrayAssetDetailId.add(item.getDetailId_D());
					arrayAssetKeyId.add(item.getAssetIssueKey_D());
				}
			}
			for (int i = 0; i < arrayAssetRemarks.size(); i++) {
				try {
					if (arrayAssetRemarks.get(i).equals("")) {
						flag = 1;
						break;

					}
				} catch (Exception e) {
					flag = 1;
					e.printStackTrace();
				}

			}

			if (flag == 1) {
				new SweetAlertDialog(AcceptDetail.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Remarks Should Not be blank.").show();
			} else {
				if (connectiondetector.isConnectingToInternet()) {
					new AsyncTaskAcceptAssetByEmployee().execute("");
				} else {
					new SweetAlertDialog(AcceptDetail.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
				}
			}

			break;
		case R.id.detailback:
			Intent intent = new Intent(AcceptDetail.this, AcceptAssets.class);
			startActivity(intent);
			finish();

			break;

		default:
			break;
		}

	}

	/************************ AsynctAsk For ***************************/
	/* Submit Data For Approve leave */

	public class AsyncTaskAcceptAssetByEmployee extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new SweetAlertDialog(AcceptDetail.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			dialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {
				response = MethodSoap.getAcceptAssetByEmployee(prefs
						.getUserID(), prefs.getEmpCode(), prefs.getEmpName(),
						prefs.getUserName(), arraylist.get(0).getIssuedBy_D(),
						arraylist.get(0).getTransferFrom_D(), arraylist.get(0)
								.getIssuedByEmail_D(), arrayCategoryName,
						arrayAssetName, arrayAssetSrNo, arrayAssetTagNo,
						arrayAssetRemarks, arrayAssetStatus, arrayAssetIssueId,
						arrayAssetDetailId, arrayAssetKeyId, transtype);
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsData = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				dialog.dismiss();
			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {

					dialog.dismiss();

					mydialog2 = new SweetAlertDialog(AcceptDetail.this,
							SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("Successfully")
							.setContentText(responsData)
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {
											Intent intent = new Intent(
													AcceptDetail.this,
													AcceptAssets.class);
											startActivity(intent);
											finish();
											mydialog2.dismiss();
										}
									});
					mydialog2.show();

				} else if (result.equals("fail")) {
					Toast.makeText(AcceptDetail.this, responsData,
							Toast.LENGTH_LONG).show();
					dialog.dismiss();
				} else if (!connectiondetector.isConnectingToInternet()) {

					new SweetAlertDialog(AcceptDetail.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					dialog.dismiss();
				} else {
					new SweetAlertDialog(AcceptDetail.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Does not get Proper Response !")
							.show();
					dialog.dismiss();
				}
			} catch (Exception e) {
				dialog.dismiss();
			}

		}
	}

}
