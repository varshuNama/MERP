package com.mcerp.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.ApproveTimeSheetAdapter;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.ApproveTimesheetModel;
import com.mcerp.util.AppPreferences;

public class ApproveTimeSheet extends Activity implements OnClickListener {
	String responseData, Message = null;
	LinearLayout backbutton,linearHeader;
	ListView list;
	String MonthYear_Send;
	TextView norecord;
	Spinner spinyear;
	Button searchButton;
	ApproveTimeSheetAdapter adapter;
	AppPreferences prefs;
	ConnectionDetector connection;
	SweetAlertDialog pDialog;
	String responsedata;
	String[] month_send;
	ArrayList<ApproveTimesheetModel> arraylistapprovetime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approvetimesheet);
		init();
		new AsyncTaskApproveTimesheet().execute("");

	}

	private void init() {

		backbutton = (LinearLayout) findViewById(R.id.backBtnApprove);
		list = (ListView) findViewById(R.id.listViewapprovetime);
		searchButton = (Button) findViewById(R.id.searchBtn);
		prefs = AppPreferences.getInstance(this);
		spinyear = (Spinner) findViewById(R.id.spinnerYear);
		norecord=(TextView) findViewById(R.id.noRecordFound);
		linearHeader= (LinearLayout) findViewById(R.id.linearheader);
		connection = new ConnectionDetector(ApproveTimeSheet.this);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH+1 ,-1);
		SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
		String month_name_one = month_date.format(cal.getTime());
		SimpleDateFormat month_datee = new SimpleDateFormat("MM");
		String month_name_onee = month_datee.format(cal.getTime());
		
		
		
		
		Calendar call=Calendar.getInstance();
		cal.add(Calendar.MONTH ,1);
		SimpleDateFormat month_date_current = new SimpleDateFormat("MMMM");
		String month_name_two = month_date_current.format(cal.getTime());
		SimpleDateFormat month_date_currentt = new SimpleDateFormat("MM");
		String month_name_two0 = month_date_currentt.format(cal.getTime());
		
		
		
		
		int year = call.get(Calendar.YEAR);
		
		 month_send=new String[]{""+year+month_name_onee,""+year+month_name_two0};

		
		Log.d("month_send",month_send[0]+""+month_send[1]+"");
		String month[] = { month_name_one+"-"+year, month_name_two+"-"+year};
		ArrayAdapter<String> adapter_view = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, month);
		adapter_view
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinyear.setAdapter(adapter_view);
		spinyear.setSelection(0);
		MonthYear_Send=month_send[0];
		
		backbutton.setOnClickListener(this);
		searchButton.setOnClickListener(this);

	}

	/******************** ApproveTimeSheet Webservice *************************/
	public class AsyncTaskApproveTimesheet extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			arraylistapprovetime = new ArrayList<ApproveTimesheetModel>();
			pDialog = new SweetAlertDialog(ApproveTimeSheet.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			
			try {
				response = MethodSoap.getApproveTimeSheet(prefs.getUserID(),
						MonthYear_Send);
				JSONObject jsonobj = new JSONObject(response);
						message = jsonobj.getString("message");
						responseData = jsonobj.getString("DataArr");
						if (message.equals("success")) {
							JSONArray jsonarray = new JSONArray(responseData);
							for (int i = 0; i < jsonarray.length(); i++) {
								JSONObject json = jsonarray.getJSONObject(i);
								ApproveTimesheetModel rep = new ApproveTimesheetModel();
								rep.setId(json.getString("Id"));
								rep.setMonthYear(json.getString("MonthYear"));
								rep.setEmpId(json.getString("EmpId"));
								rep.setEmpName(json.getString("EmpName"));
								rep.setCircle(json.getString("Circle"));
								rep.setMonth(json.getString("Month"));
								rep.setProject(json.getString("Project"));
								rep.setNOD(json.getString("NOD"));
								rep.setProjType(json.getString("ProjType"));
								rep.setProjStart(json.getString("ProjStart"));
								rep.setTSStart(json.getString("TSStart"));
								rep.setCurrMonth(json.getString("CurrMonth"));
								rep.setApprovelFlag(json.getString("ApprovalFlag"));
								arraylistapprovetime.add(rep);
							}
					}
				

			} catch (Exception e) {
				e.printStackTrace();
				pDialog.dismiss();
			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("success")) {

					if (arraylistapprovetime.size() != 0
							&& arraylistapprovetime != null) {
						norecord.setVisibility(View.GONE);
						list.setVisibility(View.VISIBLE);
						linearHeader.setVisibility(View.VISIBLE);
						adapter = new ApproveTimeSheetAdapter(ApproveTimeSheet.this, arraylistapprovetime);
						list.setAdapter(adapter);
						
					}
					pDialog.dismiss();
				} else if (result.equals("fail")) {
					list.setVisibility(View.GONE);
					linearHeader.setVisibility(View.GONE);
					norecord.setVisibility(View.VISIBLE);
					norecord.setText(responseData);
                    pDialog.dismiss();

				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(ApproveTimeSheet.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDialog.dismiss();
				} else {

					new SweetAlertDialog(ApproveTimeSheet.this,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Does not get Proper Response !")
							.show();
					pDialog.dismiss();
				}
			} catch (Exception e) {
				pDialog.dismiss();

			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(ApproveTimeSheet.this,
				NavigationActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtnApprove:
			Intent intent = new Intent(ApproveTimeSheet.this,
					NavigationActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.searchBtn:
			if(spinyear.getSelectedItemPosition()==0)
			{
				MonthYear_Send=month_send[0];
			}else{
				MonthYear_Send=month_send[1];
			}
			if (connection.isConnectingToInternet())
				new AsyncTaskApproveTimesheet().execute("");
			else {
				new SweetAlertDialog(ApproveTimeSheet.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
			}
			break;

		default:
			break;
		}
	}
}