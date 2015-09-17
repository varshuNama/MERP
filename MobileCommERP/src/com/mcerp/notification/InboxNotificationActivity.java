package com.mcerp.notification;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.MyAdapterpush;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.PushModel;

public class InboxNotificationActivity extends Activity {
	TextView textmessage;
	Button okBtn, CancleBtn;
	LinearLayout back;
	ListView list;
	private String response = null;
	private String Message = null;
	String responseData;
	SweetAlertDialog pDialog,dialog;
	ConnectionDetector connectionDetector;
	ArrayList<PushModel> msgArray;
	MyAdapterpush adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_notification);
		init();
		new AsyncTaskStatusPushMsg().execute();
		
		

	}

	private void init() {
		list = (ListView) findViewById(R.id.list_view_inbox_msg);
		back = (LinearLayout) findViewById(R.id.notify_back);
		connectionDetector = new ConnectionDetector(InboxNotificationActivity.this);
		msgArray = new ArrayList<PushModel>();

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		finish();
	}

	public class AsyncTaskStatusPushMsg extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			pDialog = new SweetAlertDialog(InboxNotificationActivity.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);


		}

		@Override
		protected String doInBackground(String... params) {

			try {

				response = MethodSoap.SendPushMessage();
				JSONObject jsonobj = new JSONObject(response);
				Message = jsonobj.getString("message");
				responseData = jsonobj.getString("DataArr");

				if (Message.equals("success")) {
					JSONArray jsonarray = new JSONArray(responseData);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						PushModel rep = new PushModel();
						rep.setMsg(json.getString("Msg"));
						msgArray.add(rep);

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				pDialog.dismiss();
			}

			return Message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (Message != null && Message.equals("success")) {
				adapter = new MyAdapterpush(InboxNotificationActivity.this,
						msgArray);
				list.setAdapter(adapter);
				pDialog.dismiss();

			} else if (!connectionDetector.isConnectingToInternet()) {
				
				pDialog.dismiss();
				dialog = new SweetAlertDialog(InboxNotificationActivity.this,
						SweetAlertDialog.WARNING_TYPE)
						.setTitleText("Ooops")
						.setContentText("Please check your internet connection.")
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(
											SweetAlertDialog sDialog) {
										Intent intent = new Intent(InboxNotificationActivity.this,
												NavigationActivity.class);
										startActivity(intent);
										finish();

										sDialog.dismiss();
									}
								});
				dialog.show();
			} else {
				
				pDialog.dismiss();
				dialog = new SweetAlertDialog(InboxNotificationActivity.this,
						SweetAlertDialog.WARNING_TYPE)
						.setTitleText("Sorry")
						.setContentText("Does not get proper response.")
						.setConfirmText("ok")
						.setConfirmClickListener(
								new SweetAlertDialog.OnSweetClickListener() {
									@Override
									public void onClick(
											SweetAlertDialog sDialog) {
										Intent intent = new Intent(InboxNotificationActivity.this,
												NavigationActivity.class);
										startActivity(intent);
										finish();

										sDialog.dismiss();
									}
								});
				dialog.show();
			}

		}
	}
}
