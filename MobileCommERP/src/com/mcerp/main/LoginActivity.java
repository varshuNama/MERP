package com.mcerp.main;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.AsyncTaskDataLogin;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.util.AppPreferences;

public class LoginActivity extends Activity implements OnClickListener,OnEditorActionListener{
	ConnectionDetector connectionDetector;
	Button submitButton;
	EditText user_name, password;
	TelephonyManager mngrimei;
	String UserName, Password;
	AppPreferences prefs;
	String registration_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initializeVariable();
		submitButton.setOnClickListener(this);		
		password.setOnEditorActionListener(this);

	}
	
	private void checkAndStroreGCMId(){
		if (prefs != null && !prefs.getRegistered()){
			PlayServicesHelper playService = new PlayServicesHelper(LoginActivity.this);
			registration_id = playService.getRegistrationId();
			Log.d("Registration id", registration_id);
			prefs.setGCMRegID(registration_id);
			}
		
	}

	private void initializeVariable() {
		
		connectionDetector = new ConnectionDetector(this);
		submitButton = (Button) findViewById(R.id.btn_submit);
		user_name = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		prefs=AppPreferences.getInstance(LoginActivity.this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit:
			UserName = user_name.getText().toString();
			Password = password.getText().toString();
			checkAndStroreGCMId();

			if (!connectionDetector.isConnectingToInternet()) {
				new SweetAlertDialog(LoginActivity.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Check Network Connection!").show();
			}
			else if(UserName.equals("") || Password.equals("")){
				new SweetAlertDialog(LoginActivity.this,
						SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
						.setContentText("Username or Password should not be blank.").show();

			}
			else if(UserName.contains("@")){
				String usernamearray[]=UserName.split("@");
				try{
					if(usernamearray[1].equals("mcpsinc.com"))
					{
						prefs.setFlag(false);
						new AsyncTaskDataLogin(LoginActivity.this, UserName, Password, "0",prefs.getGCMRegID())
								.execute();
					}
					else{
						new SweetAlertDialog(LoginActivity.this,
								SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
								.setContentText("Invalid Username or Password.").show();
					}
					
				}
				catch (Exception e) {
					new SweetAlertDialog(LoginActivity.this,
							SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
							.setContentText("Please Enter Correct data").show();
				}
				
			}
			else {
				UserName=UserName+"@mcpsinc.com"; 
				prefs.setFlag(false);
				new AsyncTaskDataLogin(LoginActivity.this, UserName, Password, "0",prefs.getGCMRegID())
						.execute();
			}

		
			break;
		
		default:
			break;
		}
	}
	
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if(actionId == EditorInfo.IME_ACTION_DONE){
			submitButton.performClick();
			
			}
		return false;
	}	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
