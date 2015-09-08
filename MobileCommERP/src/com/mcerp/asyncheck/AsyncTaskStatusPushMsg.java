package com.mcerp.asyncheck;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.connection.ConnectionDetector;
import com.mcerp.model.AssetModelData;
import com.mcerp.model.PushModel;
import com.mcerp.notification.InboxNotificationActivity;

public class AsyncTaskStatusPushMsg extends AsyncTask<String, String, String> {

	private String response = null;
	private String Message = null;
	String responseData;
	InboxNotificationActivity con;
	SweetAlertDialog pDialog;
	ConnectionDetector connectionDetector;
	ArrayList<PushModel> msgArray;

	public AsyncTaskStatusPushMsg(InboxNotificationActivity context) {

		con = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		connectionDetector = new ConnectionDetector(con);
		msgArray = new ArrayList<PushModel>();
		pDialog = new SweetAlertDialog(con, SweetAlertDialog.PROGRESS_TYPE)
				.setTitleText("Loading");
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {

		try {

			response = MethodSoap.SendPushMessage();
			JSONObject jsonobj = new JSONObject(response);
			Message = jsonobj.getString("message");
			responseData=jsonobj.getString("DataArr");
			
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

			con.getPushMsg(msgArray);
			pDialog.dismiss();

		} else if (!connectionDetector.isConnectingToInternet()) {
			new SweetAlertDialog(con, SweetAlertDialog.ERROR_TYPE)
					.setTitleText("Oops...")
					.setContentText("Internet Connection not available!")
					.show();
			pDialog.dismiss();
		} else {
			new SweetAlertDialog(con, SweetAlertDialog.ERROR_TYPE)
					.setTitleText("Oops...")
					.setContentText("Does not get Proper Response !").show();
			pDialog.dismiss();
		}

	}

}