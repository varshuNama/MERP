package com.mcerp.livendtimesheet;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
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

import com.mcerp.adapter.ApproveLeaveAdapter;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.main.R.id;
import com.mcerp.main.R.layout;
import com.mcerp.model.HomeModel;
import com.mcerp.model.Model;
import com.mcerp.util.AppPreferences;

public class ApproveLeave extends Activity implements OnClickListener {
	ListView approvelist;
	SweetAlertDialog pDialog, dialog;
	ConnectionDetector connectionDetector;
	HomeModel modeldata;
	String UserName, Password, UserId;
	AppPreferences prefs;
	LinearLayout backbtn, submitbtn;
	String responsegetdata, responsapprovedata;
	TextView approvetextdata;
	LinearLayout linearget;
	ArrayList<String> holdvalue;
	ArrayList<Model> arraylistGetLeave;
	ArrayList<String> arrayidall;
	ArrayList<String> arrayempname;
	ArrayList<String> arrayemapcode;
	ArrayList<String> arrayempid;
	ArrayList<String> arrayempemail;
	ArrayList<String> arrayempnod;
	ArrayList<String> arrayempleavefrom;
	ArrayList<String> arrayempleaveto;
	ArrayList<String> arrayempleavereason;
	ArrayList<String> arrayempproject;
	ArrayList<String> arrayempcircle;
	ArrayList<String> arrayempleavetype;
	ArrayList<String> arrayereject;

	ApproveLeaveAdapter adapter;
	int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approve_leave);
		init();

	}

	private void init() {
		approvelist = (ListView) findViewById(R.id.approvelistView);
		prefs = AppPreferences.getInstance(this);
		holdvalue = new ArrayList<String>();
		backbtn = (LinearLayout) findViewById(R.id.linearback);
		submitbtn = (LinearLayout) findViewById(R.id.submitBtn);
		linearget = (LinearLayout) findViewById(R.id.lineargetleave);
		connectionDetector = new ConnectionDetector(this);
		approvetextdata = (TextView) findViewById(R.id.approvetextleave);
		pDialog = new SweetAlertDialog(ApproveLeave.this,
				SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
		if (!connectionDetector.isConnectingToInternet()) {
			linearget.setVisibility(View.GONE);
			approvetextdata.setVisibility(View.VISIBLE);
			approvetextdata.setText("Please Check Internet Connection!!!!");
			Toast.makeText(ApproveLeave.this, "Internet Connection Problem",
					Toast.LENGTH_LONG).show();

		} else {
			new AsyncTaskGetLeave(pDialog).execute();
			linearget.setVisibility(View.VISIBLE);
		}

		backbtn.setOnClickListener(this);
		submitbtn.setOnClickListener(this);

	}

	public class AsyncTaskGetLeave extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);

		}

		public AsyncTaskGetLeave(SweetAlertDialog pdialog) {

			pDialog = pdialog;

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			arraylistGetLeave = new ArrayList<Model>();
			try {
				response = MethodSoap.getGetLeaveRequest(prefs.getUserID());
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsegetdata = jsonobj.getString("DataArr");
				if (message.equals("success")) {

					JSONArray jsonarray = new JSONArray(responsegetdata);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						Model data = new Model();
						data.setRejectOrApprove(1);
						data.setId_All(json.getString("Id_All"));
						data.setEmpId(json.getString("EmpId"));
						data.setEmpCode(json.getString("EmpCode"));
						data.setEmpEmail(json.getString("EmpEmail"));
						data.setName(json.getString("EmpName"));
						data.setReason(json.getString("LeaveReason"));
						data.setNOD(json.getString("NOD"));
						data.setLeaveFrom(json.getString("LeaveFrom"));
						data.setLeaveTo(json.getString("LeaveTo"));
						data.setProject(json.getString("Project"));
						data.setCircle(json.getString("Circle"));
						data.setType(json.getString("LeaveType"));

						arraylistGetLeave.add(data);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				message = "";
				pDialog.dismiss();

			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {

					if (arraylistGetLeave.size() != 0
							&& arraylistGetLeave != null) {
						approvelist.setVisibility(View.VISIBLE);
						adapter = new ApproveLeaveAdapter(ApproveLeave.this,
								arraylistGetLeave);
						approvelist.setAdapter(adapter);
						adapter.notifyDataSetChanged();

						approvetextdata.setVisibility(View.GONE);
						linearget.setVisibility(View.VISIBLE);
						submitbtn.setVisibility(View.VISIBLE);

					} else {
						approvetextdata.setVisibility(View.VISIBLE);
						approvelist.setVisibility(View.GONE);
						linearget.setVisibility(View.GONE);
						submitbtn.setVisibility(View.GONE);
					}
					pDialog.dismiss();
				} else if (result.equals("fail")) {
					approvetextdata.setVisibility(View.VISIBLE);
					submitbtn.setVisibility(View.GONE);
					linearget.setVisibility(View.GONE);
					approvelist.setVisibility(View.GONE);
					approvetextdata.setText(responsegetdata);
					pDialog.dismiss();

				} else if (!connectionDetector.isConnectingToInternet()) {
					approvetextdata.setText("Check Internet Connection!");
					new SweetAlertDialog(ApproveLeave.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {
					approvetextdata.setText("Does not get Proper Response !");
					if (flag == 1) {
						flag=0;
						new SweetAlertDialog(ApproveLeave.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Server Connection Problem.")
								.show();
						pDialog.dismiss();

					} else {
						new SweetAlertDialog(ApproveLeave.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Does not get Proper Response.").show();
						pDialog.dismiss();
					}
				}
			} catch (Exception e) {
				pDialog.dismiss();
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linearback:
			Intent intent = new Intent(ApproveLeave.this, NavigationActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.submitBtn:
			arrayidall = new ArrayList<String>();
			arrayempname = new ArrayList<String>();
			arrayemapcode = new ArrayList<String>();
			arrayempid = new ArrayList<String>();
			arrayempemail = new ArrayList<String>();
			arrayempnod = new ArrayList<String>();
			arrayempleavefrom = new ArrayList<String>();
			arrayempleaveto = new ArrayList<String>();
			arrayempleavereason = new ArrayList<String>();
			arrayempproject = new ArrayList<String>();
			arrayempcircle = new ArrayList<String>();
			arrayempleavetype = new ArrayList<String>();
			arrayereject = new ArrayList<String>();
			if (adapter != null) {
				for (Iterator<Model> i = (adapter.getList()).iterator(); i
						.hasNext();) {
					Model item = i.next();
					if (item.isChecked()) {
						arrayereject.add(item.getSelected() + "");
						arrayidall.add(item.getId_All());
						arrayemapcode.add(item.getEmpCode());
						arrayempcircle.add(item.getCircle());
						arrayempnod.add(item.getNOD());
						arrayempleavefrom.add(item.getLeaveFrom());
						arrayempleaveto.add(item.getLeaveTo());
						arrayempleavetype.add(item.getType());
						arrayempproject.add(item.getProject());
						arrayempname.add(item.getName());
						arrayempemail.add(item.getEmpEmail());
						arrayempleavereason.add(item.getReason());
						arrayempid.add(item.getEmpId());
					}
				}
			}
			if (connectionDetector.isConnectingToInternet())
				new AsyncTaskApproveLeave().execute("");
			else {
				new SweetAlertDialog(ApproveLeave.this, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}

		default:
			break;
		}

	}

	/* Submit Data For Approve leave */

	public class AsyncTaskApproveLeave extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new SweetAlertDialog(ApproveLeave.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			dialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {
				response = MethodSoap.getLeaveApprove(prefs.getUserID(),
						prefs.getEmpName(), prefs.getUserName(), arrayidall,
						arrayempnod, arrayempname, arrayempemail,
						arrayemapcode, arrayempid, arrayempleavereason,
						arrayempleavefrom, arrayempleaveto, arrayempleavetype,
						arrayereject);
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsapprovedata = jsonobj.getString("DataArr");

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
					Toast.makeText(ApproveLeave.this, responsapprovedata,
							Toast.LENGTH_LONG).show();
					new AsyncTaskGetLeave(dialog).execute();

					dialog.dismiss();
				} else if (result.equals("fail")) {
					Toast.makeText(ApproveLeave.this, responsapprovedata,
							Toast.LENGTH_LONG).show();
					dialog.dismiss();
				} else if (!connectionDetector.isConnectingToInternet()) {
					approvetextdata.setText("Check Internet Connection!");
					new SweetAlertDialog(ApproveLeave.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					dialog.dismiss();
				} else {
					approvetextdata.setText("Does not get Proper Response !");
					new SweetAlertDialog(ApproveLeave.this,
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

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(ApproveLeave.this, NavigationActivity.class);
		startActivity(intent);
		finish();
	}

}
