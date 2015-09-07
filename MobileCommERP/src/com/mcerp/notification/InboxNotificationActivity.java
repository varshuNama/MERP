package com.mcerp.notification;

import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.adapter.MyAdapterpush;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;

public class InboxNotificationActivity extends Activity {
	TextView textmessage;
	Button okBtn, CancleBtn;
	SweetAlertDialog pDialog;
	
	ConnectionDetector connectionDetector;
	LinearLayout back;
	String[] msg_arr={"snmbdnsdbnsbdnbsndbsnbdnsbdnbsndbsnbdnsbd","sb dbnnsdnbs db sbd bs dbs d"," s d s dsndnnsbdsbnjaskjaksjaksjakjskajsk",
			"dnmsndmsandmnasmdnamnanKNKAK","BCVBXVCBVXBCVBXVCXHJJKASKJASHDKJKAKJKA","SBkjsshkdbbdbfndbnbdnfbdn"};
  MyAdapterpush adapter;
  ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_notification);
		
		back=(LinearLayout) findViewById(R.id.notify_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		list=(ListView) findViewById(R.id.list_view_inbox_msg);
	    adapter=new MyAdapterpush(this, msg_arr);
	    list.setAdapter(adapter);
	}

	class AsyncTaskStatusPushMsg extends AsyncTask<String, String, String> {

		private String response = null;
		private String Message = null;
		String status = null;

		AsyncTaskStatusPushMsg(String msgstatus) {
			status = msgstatus;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new SweetAlertDialog(InboxNotificationActivity.this,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {

			try {
				Log.d("STATUS", status);
				response = MethodSoap.SendPushMessage(status);
				JSONObject jsonobj = new JSONObject(response);
				Message = jsonobj.getString("message");
				Log.d("Message", Message);
				if (Message.equals("success")) {
					Toast.makeText(InboxNotificationActivity.this,
							"Your message is send successfully.",
							Toast.LENGTH_LONG).show();

				} else {
					Toast.makeText(InboxNotificationActivity.this,
							"Your message is fail.", Toast.LENGTH_LONG).show();

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
				pDialog.dismiss();

			} else if (!connectionDetector.isConnectingToInternet()) {
				new SweetAlertDialog(InboxNotificationActivity.this, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Internet Connection not available!")
						.show();
				pDialog.dismiss();
			} else {
				new SweetAlertDialog(InboxNotificationActivity.this, SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Does not get Proper Response !")
						.show();
				pDialog.dismiss();
			}

		}
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
