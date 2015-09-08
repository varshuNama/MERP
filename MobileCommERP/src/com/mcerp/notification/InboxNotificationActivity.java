package com.mcerp.notification;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mcerp.adapter.MyAdapterpush;
import com.mcerp.asyncheck.AsyncTaskStatusPushMsg;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.PushModel;

public class InboxNotificationActivity extends Activity {
	TextView textmessage;
	Button okBtn, CancleBtn;
	LinearLayout back;
	ListView list;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_notification);
		list = (ListView) findViewById(R.id.list_view_inbox_msg);
		back = (LinearLayout) findViewById(R.id.notify_back);

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Intent inttent=new Intent(InboxNotificationActivity.this,NavigationActivity.class);
				startActivity(inttent);	*/
				finish();
			}
		});
		new AsyncTaskStatusPushMsg(InboxNotificationActivity.this).execute();

	}

	public void getPushMsg(ArrayList<PushModel> msgarr) {

		MyAdapterpush adapter = new MyAdapterpush(this, msgarr);
		list.setAdapter(adapter);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		/*Intent inttent=new Intent(InboxNotificationActivity.this,NavigationActivity.class);
		startActivity(inttent);	*/
		finish();
	}

}
