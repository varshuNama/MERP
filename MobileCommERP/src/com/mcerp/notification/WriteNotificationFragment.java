package com.mcerp.notification;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.R;
import com.mcerp.util.AppPreferences;

public class WriteNotificationFragment extends Fragment implements
		OnClickListener {
	Button send_btn, reset_btn;
	View rootView;
	EditText text_message;
	String subject = "", message;
	ConnectionDetector connection;
	AppPreferences prefs;
	String responsedata, Response;
	SweetAlertDialog pDialog, dialog;
	int flag = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.write_notifications, container,
				false);
		init();

		return rootView;
	}

	private void init() {
		prefs=AppPreferences.getInstance(getActivity());
		connection = new ConnectionDetector(getActivity());
		send_btn = (Button) rootView.findViewById(R.id.send_btn);
		reset_btn = (Button) rootView.findViewById(R.id.reset_btn);
		text_message = (EditText) rootView.findViewById(R.id.message);

		send_btn.setOnClickListener(this);
		reset_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_btn:
			if (connection.isConnectingToInternet()) {
				message = text_message.getText().toString();
				if (message != "" && message.length() > 0) {
					new AsyncTaskComposeNotificationMessage().execute();

				} else {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.NORMAL_TYPE)
							.setTitleText("")
							.setContentText("Please enter your message.")
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(
												SweetAlertDialog sDialog) {

											sDialog.dismiss();
										}
									}).show();
				}
			} else {
				new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Not Connected To Internet").show();
			}

			break;
		case R.id.reset_btn:
			text_message.setText("");
			break;

		default:
			break;
		}

	}

	public class AsyncTaskComposeNotificationMessage extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new SweetAlertDialog(getActivity(),
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);

		}

		@Override
		protected String doInBackground(String... params) {
			String Message = null;
			try {
				responsedata = MethodSoap.sendComposeNotificationMsg(prefs.getUserID(), subject, message);
				JSONObject jsonobj = new JSONObject(responsedata);
				Message = jsonobj.getString("message");
				Response = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				Message = "";

			}

			return Message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (result != null && !result.equals("")
						&& !result.equals("null") && result.equals("success")) {
                    text_message.setText("");
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.NORMAL_TYPE)
							.setTitleText("Successfully")
							.setContentText("Your message has been sent.")
							.show();
					pDialog.dismiss();

				} else if (!connection.isConnectingToInternet()) {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Connection not available!").show();
					pDialog.dismiss();
				} else {
					pDialog.dismiss();
					if (flag == 1) {
						flag = 0;
						dialog = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Oops")
								.setContentText("Server Connection Problem.")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {

												sDialog.dismiss();
											}
										});
						dialog.show();

					} else {
						dialog = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Oops")
								.setContentText("Does Not Get Proper Respnse.")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											public void onClick(
													SweetAlertDialog sDialog) {

												sDialog.dismiss();
											}
										});
						dialog.show();

					}
				}
			} catch (Exception e) {

			}
		}

	}
}
