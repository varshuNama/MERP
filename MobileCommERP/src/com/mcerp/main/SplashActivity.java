package com.mcerp.main;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.util.AppPreferences;
import com.mcerp.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	protected boolean _active = true;
	protected int _splashTime = 1500;
	AppPreferences prefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		prefs=AppPreferences.getInstance(SplashActivity.this);
		Utility.initFonts(getApplicationContext());
		
		
		TextView header = (TextView) findViewById(R.id.txtheader);
		header.setTypeface(Utility.Ascom_Zwo_BoldPS);
		TextView headerERP = (TextView) findViewById(R.id.TextHeader);
		headerERP.setTypeface(Utility.Ascom_Zwo_BoldPS);
		
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(150);
						if (_active) {
							waited += 150;
						}
					}
				} catch (InterruptedException e) {
				} finally {
					startMainScreen();
				}
			}
		};
		PlayServicesHelper playService = new PlayServicesHelper(SplashActivity.this);
		if(playService.getRegistrationId().isEmpty()){
			ConnectionDetector connectiondetector = new ConnectionDetector(this);
			if(connectiondetector.isConnectingToInternet()){
				playService.checkPlayService();
				splashTread.start();
			}else{
				Toast.makeText(SplashActivity.this, "Please, connect to active internet connection", Toast.LENGTH_LONG).show();
				SplashActivity.this.finish();
			}
		}else{
			splashTread.start();
		}

	}

	protected void startMainScreen() {
		if(!prefs.getRegistered()){
			Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}else{
			Log.d("Value", prefs.getRegistered()+"");
			Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);			
			startActivity(intent);
			finish();
		}
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}