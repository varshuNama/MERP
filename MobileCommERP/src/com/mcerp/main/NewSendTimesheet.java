package com.mcerp.main;

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

import com.mcerp.adapter.NewTimesheetAdapter;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.NewTimesheetModel;
import com.mcerp.util.AppPreferences;

public class NewSendTimesheet extends Activity implements OnClickListener {
	String responseData, Message = null, responsesubmitdata;
	LinearLayout backimg, newtimetheader, SubmitBtn;
	ListView listview;
	String monthname;
	TextView norecord, textMonthName, textEmpName, textProjectMgr;
	ArrayList<NewTimesheetModel> reportdata;
	NewTimesheetAdapter adapter;

	AppPreferences prefs;
	ConnectionDetector connection;
	SweetAlertDialog pDialog;
	String responsedata;
	int flag = 0;

	ArrayList<String> arrayTSId;
	ArrayList<String> arrayProjAllocationId;
	ArrayList<String> arrayProjCode;
	ArrayList<String> arrayProjMgr;
	ArrayList<String> arrayProjCountry;
	ArrayList<String> arrayProjCircle;
	ArrayList<String> arrayProjStart;
	ArrayList<String> arrayProjTimesheetStart;
	ArrayList<String> arrayTSDId;
	ArrayList<String> arrayTSDate;
	ArrayList<String> arrayTSDay;
	ArrayList<String> arrayTSDAAmount;
	ArrayList<String> arrayTSDACurType;
	ArrayList<String> arrayTSActivity;
	ArrayList<String> arrayTSDescription;
	ArrayList<String> arrayTSStatus;
	ArrayList<String> arrayTSDAId;
	ArrayList<String> arrayTSAssetStockId;
	ArrayList<String> arrayTSTrainingId;
	ArrayList<String> arrayTSTimesheetType;
	ArrayList<String> arrayTSLeaveNOD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsendtimesheet);
		init();
		new AsyncTaskNewTimesheet(pDialog).execute();

	}

	private void init() {

		backimg = (LinearLayout) findViewById(R.id.backImg);
		newtimetheader = (LinearLayout) findViewById(R.id.newtimetheader);
		listview = (ListView) findViewById(R.id.listViewTimeSheet);
		textMonthName = (TextView) findViewById(R.id.textMonth);
		SubmitBtn = (LinearLayout) findViewById(R.id.submitBtn);
		pDialog = new SweetAlertDialog(NewSendTimesheet.this,
				SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
		norecord = (TextView) findViewById(R.id.newtextleave);
		prefs = AppPreferences.getInstance(this);
		connection = new ConnectionDetector(NewSendTimesheet.this);
		monthname = prefs.getMonthName();
		textMonthName.setText(monthname);
		SubmitBtn.setOnClickListener(this);
		backimg.setOnClickListener(this);

	}

	/******************** NewSendTimesheet Webservice *************************/
	public class AsyncTaskNewTimesheet extends
			AsyncTask<String, String, String> {

		public AsyncTaskNewTimesheet(SweetAlertDialog pdialog) {
			pDialog = pdialog;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {
				response = MethodSoap.getTimeSheet(prefs.getUserID(),
						prefs.getEmpCode(), prefs.getMonthYear(), "0");
				reportdata = new ArrayList<NewTimesheetModel>();
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsedata = jsonobj.getString("DataArr");
				if (message.equals("ts")) {

					JSONArray jsonarray = new JSONArray(responsedata);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						NewTimesheetModel data = new NewTimesheetModel();
						data.setPending(json.getString("Pending"));
						data.setApproved(json.getString("Approved"));
						data.setCountry(json.getString("Country"));
						data.setNoOfDays(json.getString("NoOfDays"));
						data.setTSDays(json.getString("TSDays"));
						data.setTimesheetId(json.getString("TimesheetId"));
						data.setProjAllocationId(json
								.getString("ProjAllocationId"));
						data.setEmpId(json.getString("EmpId"));
						data.setProjCode(json.getString("ProjCode"));
						data.setProjMgr(json.getString("ProjMgr"));
						data.setCircleMarket(json.getString("CircleMarket"));
						data.setProjType(json.getString("ProjType"));
						data.setProjStart(json.getString("ProjStart"));
						data.setProjEnd(json.getString("ProjEnd"));
						data.setTSStart(json.getString("TSStart"));
						data.setTSEnd(json.getString("TSEnd"));
						data.setTSFlag(json.getString("TSFlag"));
						data.setTSDate(json.getString("TSDate"));
						data.setLeave(json.getString("Leave"));
						data.setLeaveNOD(json.getString("LeaveNOD"));
						data.setHoliday(json.getString("Holiday"));
						data.setTSDayStatus(json.getString("TSDayStatus"));
						data.setTSDId(json.getString("TSDId"));
						data.setTimsheetDate(json.getString("TimsheetDate"));
						data.setTimsheetWeekDay(json
								.getString("TimsheetWeekDay"));
						data.setLHStatus(json.getString("LHStatus"));
						data.setTSStatus(json.getString("TSDayStatus"));
						data.setTSStatusDesc(json.getString("TSStatusDesc"));
						data.setTSActivity(json.getString("TSActivity"));
						data.setTSDescription(json.getString("TSDescription"));
						data.setDA(json.getString("DA"));
						data.setDAId(json.getString("DAId"));
						data.setDAAmount(json.getString("DAAmount"));
						data.setDACurType(json.getString("DACurType"));
						data.setAssetStockId(json.getString("AssetStockId"));
						data.setTSMailDetail(json.getString("TSMailDetail"));
						data.setProjectDesc(json.getString("ProjectDesc"));
						data.setTimesheetType(json.getString("TimesheetType"));
						data.setMailDesc(json.getString("MailDesc"));
						reportdata.add(data);

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
						&& result.equals("ts")) {

					if (reportdata.size() != 0 && reportdata != null) {
						newtimetheader.setVisibility(View.VISIBLE);
						listview.setVisibility(View.VISIBLE);
						SubmitBtn.setVisibility(View.VISIBLE);
						norecord.setVisibility(View.GONE);
						adapter = new NewTimesheetAdapter(
								NewSendTimesheet.this, reportdata);
						listview.setAdapter(adapter);
						pDialog.dismiss();

					} else {
						norecord.setVisibility(View.VISIBLE);
						listview.setVisibility(View.GONE);
						newtimetheader.setVisibility(View.GONE);
						norecord.setText(responsedata);
						SubmitBtn.setVisibility(View.VISIBLE);
						pDialog.dismiss();
					}

				} else if (result.equals("nt")) {
					norecord.setVisibility(View.VISIBLE);
					norecord.setText(responsedata);
					newtimetheader.setVisibility(View.GONE);
					listview.setVisibility(View.GONE);
					SubmitBtn.setVisibility(View.GONE);
					pDialog.dismiss();

				} else if (result.equals("fail")) {
					norecord.setVisibility(View.VISIBLE);
					norecord.setText(responsedata);
					newtimetheader.setVisibility(View.GONE);
					listview.setVisibility(View.GONE);
					SubmitBtn.setVisibility(View.GONE);

					pDialog.dismiss();

				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(NewSendTimesheet.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {
					new SweetAlertDialog(NewSendTimesheet.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Does not get Proper Response !")
							.show();
					pDialog.dismiss();
				}
			} catch (Exception e) {
				e.printStackTrace();
				pDialog.dismiss();
			}

		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(NewSendTimesheet.this,
				NavigationActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitBtn:
			arrayTSId = new ArrayList<String>();
			arrayProjAllocationId = new ArrayList<String>();
			arrayProjCode = new ArrayList<String>();
			arrayProjMgr = new ArrayList<String>();
			arrayProjCountry = new ArrayList<String>();
			arrayProjCircle = new ArrayList<String>();
			arrayProjStart = new ArrayList<String>();
			arrayProjTimesheetStart = new ArrayList<String>();
			arrayTSDId = new ArrayList<String>();
			arrayTSDate = new ArrayList<String>();
			arrayTSDay = new ArrayList<String>();
			arrayTSDAAmount = new ArrayList<String>();
			arrayTSDACurType = new ArrayList<String>();
			arrayTSActivity = new ArrayList<String>();
			arrayTSDescription = new ArrayList<String>();
			arrayTSStatus = new ArrayList<String>();
			arrayTSDAId = new ArrayList<String>();
			arrayTSAssetStockId = new ArrayList<String>();
			arrayTSTrainingId = new ArrayList<String>();
			arrayTSTimesheetType = new ArrayList<String>();
			arrayTSLeaveNOD = new ArrayList<String>();
			if (adapter != null) {
				for (Iterator<NewTimesheetModel> i = (adapter.getList())
						.iterator(); i.hasNext();) {
					NewTimesheetModel item = i.next();

					arrayTSId.add(item.getTSDId());
					arrayProjAllocationId.add(item.getProjAllocationId());
					arrayProjCode.add(item.getProjCode());
					arrayProjMgr.add(item.getProjMgr());
					arrayProjCountry.add(item.getCountry());
					arrayProjCircle.add(item.getCircleMarket());
					arrayProjStart.add(item.getProjStart());
					arrayProjTimesheetStart.add(item.getTSStart());
					arrayTSDId.add(item.getTSDId());
					arrayTSDate.add(item.getTSDate());
					arrayTSDay.add(item.getTimsheetWeekDay());
					arrayTSDAAmount.add(item.getDAAmount());
					arrayTSDACurType.add(item.getDACurType());
					arrayTSActivity.add(item.getTSActivity());
					arrayTSDescription.add(item.getTSDescription());
					arrayTSStatus.add(item.getTSStatus());
					arrayTSDAId.add(item.getDAId());
					arrayTSAssetStockId.add(item.getAssetStockId());
					arrayTSTrainingId.add(item.getTSDId());
					arrayTSTimesheetType.add(item.getTimesheetType());
					arrayTSLeaveNOD.add(item.getLeaveNOD());

				}
			}
			if (connection.isConnectingToInternet())
				new AsyncTaskSubmitTimesheet().execute("");
			else {
				new SweetAlertDialog(NewSendTimesheet.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}

			break;
		case R.id.backImg:
			Intent intent = new Intent(NewSendTimesheet.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

	/* Submit Data For Approve leave */

	public class AsyncTaskSubmitTimesheet extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new SweetAlertDialog(NewSendTimesheet.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {
				response = MethodSoap.getSubmitTimesheet(prefs.getUserID(),
						prefs.getEmpCode(), prefs.getEmpName(),
						prefs.getUserName(), prefs.getMonthName(),
						prefs.getMonthYear(), reportdata.get(0).getMailDesc(),
						arrayTSId, arrayProjAllocationId, arrayProjCode,
						arrayProjMgr, arrayProjCountry, arrayProjCircle,
						arrayProjStart, arrayProjTimesheetStart, arrayTSDId,
						arrayTSDate, arrayTSDay, arrayTSDAAmount,
						arrayTSDACurType, arrayTSActivity, arrayTSDescription,
						arrayTSStatus, arrayTSDAId, arrayTSAssetStockId,
						arrayTSTrainingId, arrayTSTimesheetType,
						arrayTSLeaveNOD);
				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				responsesubmitdata = jsonobj.getString("DataArr");

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
					Toast.makeText(NewSendTimesheet.this, responsesubmitdata,
							Toast.LENGTH_LONG).show();

					new AsyncTaskNewTimesheet(pDialog).execute("");
					pDialog.dismiss();
				} else if (result.equals("fail")) {
					Toast.makeText(NewSendTimesheet.this, responsesubmitdata,
							Toast.LENGTH_LONG).show();
					pDialog.dismiss();
				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(NewSendTimesheet.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {
					if (flag == 1) {
						flag = 0;
						new SweetAlertDialog(NewSendTimesheet.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Server connection problem !")
								.show();
						pDialog.dismiss();
					} else {
						new SweetAlertDialog(NewSendTimesheet.this,
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText(
										"Does not get Proper Response !")
								.show();
						pDialog.dismiss();
					}
				}
			} catch (Exception e) {
				pDialog.dismiss();
			}

		}
	}

}
