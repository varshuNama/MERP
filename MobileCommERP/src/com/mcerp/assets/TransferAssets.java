package com.mcerp.assets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.AsyncTaskTransferAssets;
import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.TransferAssetsModel;
import com.mcerp.util.AppPreferences;

public class TransferAssets extends Fragment {

	View rootView;
	TextView emp, empproj;
	View view0, view1;
	RadioGroup rdgroup;
	RadioButton rd_admin, rd_emp;
	EditText dateData, reason;
	int mYear, mMonth, mDay;
	String[] strCircle, strEmpName, strAssetsList;
	Spinner empspin, empcirclespin;
	MultiSelectionSpinner assetlistSpin;
	SweetAlertDialog pDDialog, mydialog2;
	AppPreferences prefs;
	Button transfer_btn;
	String myresult;
	ConnectionDetector connection;
	String data1, data2, data3, data4;
	int flag=0;

	
	ArrayList<TransferAssetsModel> reportdata1, reportdata2, reportdata3,
			reportdata4;
	ArrayList<String> arrayAssetID;
	ArrayList<String> arrayAssetDesc;
	ArrayList<String> assetsmasnjaArray;
	ArrayList<String> assetsIdArray;
	ArrayList<String> arrayAssetDetailId;
	ArrayList<String> arrayAssetIssueId;
	ArrayList<String> arrayAssetStockId;
	
	NavigationActivity act;
	String emp_select, asset_select, date_select, remarks_select,
			circle_select, ciecleid_select, transferkeyid, ProjMgrEmail,
			ProjMgrName, ProjCode, ProjMgrCode, ProjName;

	public TransferAssets(NavigationActivity navigationActivity) {
		act = navigationActivity;
	}

	public TransferAssets() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.activity_transfer_assets,
				container, false);
		connection = new ConnectionDetector(getActivity());
		new AsyncTaskTransferAssets(act).execute();

		return rootView;
	}

	private void parseData() throws JSONException {
		reportdata1 = new ArrayList<TransferAssetsModel>();
		reportdata2 = new ArrayList<TransferAssetsModel>();
		reportdata3 = new ArrayList<TransferAssetsModel>();
		reportdata4 = new ArrayList<TransferAssetsModel>();

		JSONArray jsonarray = new JSONArray(data1);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject json = jsonarray.getJSONObject(i);
			TransferAssetsModel data = new TransferAssetsModel();
			data.setAsset_Id(json.getString("AssetId"));
			data.setAsset_Des(json.getString("AssetDesc"));
			reportdata1.add(data);

		}

		JSONArray jsonarray2 = new JSONArray(data2);
		for (int i = 0; i < jsonarray2.length(); i++) {
			JSONObject json = jsonarray2.getJSONObject(i);
			TransferAssetsModel data = new TransferAssetsModel();
			data.setProjMgrEmail(json.getString("ProjMgrEmail"));
			data.setProjMrgName(json.getString("ProjMgrName"));
			data.setProjCode(json.getString("ProjCode"));
			data.setProjMgrCode(json.getString("ProjMgrCode"));
			data.setProjName(json.getString("ProjName"));
			reportdata2.add(data);

		}

		JSONArray jsonarray3 = new JSONArray(data3);
		for (int i = 0; i < jsonarray3.length(); i++) {
			JSONObject json = jsonarray3.getJSONObject(i);
			TransferAssetsModel data = new TransferAssetsModel();
			data.setEmpName(json.getString("EmpName"));
			data.setKeyId(json.getString("KeyId"));

			reportdata3.add(data);

		}

		JSONArray jsonarray4 = new JSONArray(data4);
		for (int i = 0; i < jsonarray4.length(); i++) {
			JSONObject json = jsonarray4.getJSONObject(i);
			TransferAssetsModel data = new TransferAssetsModel();
			data.setCircleID(json.getString("CircleID"));
			data.setCircleName(json.getString("CircleName"));

			reportdata4.add(data);

		}
		strCircle = new String[reportdata4.size()];
		strEmpName = new String[reportdata3.size()];
		strAssetsList = new String[reportdata1.size()];
		for (int i = 0; i < reportdata4.size(); i++) {
			strCircle[i] = reportdata4.get(i).getCircleName();
		}
		for (int i = 0; i < reportdata3.size(); i++) {
			strEmpName[i] = reportdata3.get(i).getEmpName();
		}
		for (int i = 0; i < reportdata1.size(); i++) {
			strAssetsList[i] = reportdata1.get(i).getAsset_Des();
		}

	}

	private void init(NavigationActivity con) {

		view0 = con.findViewById(R.id.assetview1);
		view1 = con.findViewById(R.id.viewasset2);
		emp = (TextView) con.findViewById(R.id.emp_text);
		empproj = (TextView) con.findViewById(R.id.empproj1);
		rdgroup = (RadioGroup) con.findViewById(R.id.radioGroupAsset);
		rd_admin = (RadioButton) con.findViewById(R.id.rd_admin);
		rd_emp = (RadioButton) con.findViewById(R.id.rd_emp);
		reason = (EditText) con.findViewById(R.id.reason);
		empspin = (Spinner) con.findViewById(R.id.empspin);
		empcirclespin = (Spinner) con.findViewById(R.id.empcirclespin);
		assetlistSpin = (MultiSelectionSpinner) con
				.findViewById(R.id.assetslistspin);
		prefs = AppPreferences.getInstance(act);
	
		

		dateData = (EditText) con.findViewById(R.id.datedata);
		reason.setText("");
		dateData.setText("");
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.getDefault());
		dateData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();

				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				c.getMaximum(mMonth);

				// Launch Date Picker Dialog
				DatePickerDialog dpd = new DatePickerDialog(act,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								String month, day;
								dateData.setText(dayOfMonth + "-"
										+ (monthOfYear + 1) + "-" + year);
								if ((monthOfYear + 1) < 10) {
									month = "0" + (monthOfYear + 1);
								} else {
									month = (monthOfYear + 1) + "";
								}

								if ((dayOfMonth) < 10) {
									day = "0" + dayOfMonth;
								} else {
									day = "" + dayOfMonth;
								}

								date_select = year + "-" + (month) + "-" + day;

							}
						}, mYear, mMonth, mDay);
				Calendar minDate = Calendar.getInstance();
				try {
					minDate.setTime(formatter.parse(mDay + "." + mMonth + "."
							+ mYear));
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DatePicker datePicker = dpd.getDatePicker();
				datePicker.setMinDate(minDate.getTimeInMillis());

				dpd.show();

			}
		});

		transfer_btn = (Button) con.findViewById(R.id.transfer);

		transfer_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 arrayAssetID = new ArrayList<String>();
				 arrayAssetDesc = new ArrayList<String>();
				 assetsmasnjaArray = new ArrayList<String>();
				 assetsIdArray = new ArrayList<String>();
				emp_select = empspin.getSelectedItem().toString();
				circle_select = empcirclespin.getSelectedItem().toString();
				remarks_select = reason.getText().toString();
				for (int i = 0; i < reportdata4.size(); i++) {
					if (circle_select
							.equals(reportdata4.get(i).getCircleName())) {
						ciecleid_select = reportdata4.get(i).getCircleID();
						break;
					}

				}

				for (int i = 0; i < reportdata3.size(); i++) {
					if (emp_select.equals(reportdata3.get(i).getEmpName())) {
						transferkeyid = reportdata3.get(i).getKeyId();
						break;
					}

				}

				for (int i = 0; i < reportdata2.size(); i++) {
					ProjMgrEmail = reportdata2.get(i).getProjMgrEmail();
					ProjMgrName = reportdata2.get(i).getProjMrgName();
					ProjCode = reportdata2.get(i).getProjCode();
					ProjMgrCode = reportdata2.get(i).getProjMgrCode();
					ProjName = reportdata2.get(i).getProjName();

				}

				List<String> assetsArray = Arrays.asList(assetlistSpin
						.getSelectedItemsAsString().split(","));

				for (int i = 0; i < assetsArray.size(); i++) {
					assetsmasnjaArray.add(assetsArray.get(i).trim());
				}
				
				for (int i = 0; i < assetsmasnjaArray.size(); i++) {
					for (int j = 0; j < reportdata1.size(); j++) {
						if (assetsmasnjaArray
								.get(i)
								.trim()
								.equals(reportdata1.get(j).getAsset_Des()
										.trim())) {
							assetsIdArray.add(reportdata1.get(j).getAsset_Id());

						}
					}
				}

				if(flag==0)
				      {
					
							if (assetsIdArray.size() > 0 && assetsIdArray != null
									&& assetsArray.size() > 0 && assetsArray != null) {
			
								new AsyncSendDataTransferAssetsToEmployee().execute();
							} else {
								Toast.makeText(act, "Please Select Assets .",
										Toast.LENGTH_LONG).show();
							}

			}else if(flag==1){
				if (assetsIdArray.size() > 0 && assetsIdArray != null
						&& assetsArray.size() > 0 && assetsArray != null) {

					new AsyncSendDataTransferAssetsToAdmin().execute();
				} else {
					Toast.makeText(act, "Please Select Assets .",
							Toast.LENGTH_LONG).show();
				}

				
			}
			}
		});

	}

	public void callTransferData(NavigationActivity act, String d1, String d2,
			String d3, String d4) {
		data1 = d1;
		data2 = d2;
		data3 = d3;
		data4 = d4;
		try {
			parseData();
			init(act);
			BindDataInSpinner(act);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void BindDataInSpinner(NavigationActivity activity) {

		/******************* Employee Name List *******************/
		ArrayAdapter<String> adapter_emp = new ArrayAdapter<String>(activity,
				android.R.layout.simple_spinner_item, strEmpName);
		adapter_emp
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		empspin.setAdapter(adapter_emp);
		empspin.setSelection(0);

		/******************* Circle List *******************/
		ArrayAdapter<String> adapter_emp_proj = new ArrayAdapter<String>(
				activity, android.R.layout.simple_spinner_item, strCircle);

		empcirclespin.setAdapter(adapter_emp_proj);
		empcirclespin.setSelection(0);

		/***************** Assets List ***************/

		assetlistSpin.setItems(strAssetsList);

		rdgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rd_admin) {
					view0.setVisibility(View.GONE);
					view1.setVisibility(View.GONE);
					emp.setVisibility(View.GONE);
					empproj.setVisibility(View.VISIBLE);
					empspin.setVisibility(View.GONE);
					empcirclespin.setVisibility(View.VISIBLE);
					flag=1;

				} else if (checkedId == R.id.rd_emp) {
					view0.setVisibility(View.VISIBLE);
					view1.setVisibility(View.VISIBLE);
					emp.setVisibility(View.VISIBLE);
					empproj.setVisibility(View.VISIBLE);
					empspin.setVisibility(View.VISIBLE);
					empcirclespin.setVisibility(View.VISIBLE);
					flag=0;

				}

			}
		});
	}

	/******************************Assets Transfer To Employee****************************************/

	public class AsyncSendDataTransferAssetsToEmployee extends
			AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDDialog = new SweetAlertDialog(act,
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			pDDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String message = null, response = null;
			try {

				response = MethodSoap.SendDataTransferAssets(prefs.getUserID(),
						prefs.getEmpCode(), prefs.getEmpName(),
						prefs.getUserName(), ciecleid_select, date_select,
						remarks_select, circle_select, transferkeyid,
						emp_select, ProjCode, ProjName, ProjMgrCode,
						ProjMgrEmail, ProjMgrName, assetsmasnjaArray,
						assetsIdArray);

				JSONObject jsonobj = new JSONObject(response);
				message = jsonobj.getString("message");
				myresult = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				pDDialog.dismiss();
				return message;
			}

			return message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (!result.equals("") && !result.equals("null")
						&& result.equals("Success")) {

					pDDialog.dismiss();
					mydialog2 = new SweetAlertDialog(act,
							SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("Successfully")
							.setContentText(myresult)
							.setConfirmText("ok")
							.setConfirmClickListener(
									new SweetAlertDialog.OnSweetClickListener() {
										@Override
										public void onClick(SweetAlertDialog sDialog) {
											new AsyncTaskTransferAssets(act).execute();
											mydialog2.dismiss();
										}
									});
					mydialog2.show();

				} else if (result.equals("fail")) {
					new SweetAlertDialog(act,
							SweetAlertDialog.NORMAL_TYPE)
							.setTitleText("Oops...").setContentText(myresult)
							.show();
					pDDialog.dismiss();

				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(act,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText(
									"Internet Connection not available!")
							.show();
					pDDialog.dismiss();
				} else {

					new SweetAlertDialog(act,
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Does not get Proper Response !")
							.show();
					pDDialog.dismiss();
				}
			} catch (Exception e) {
				e.printStackTrace();
				pDDialog.dismiss();
			}

		}
	}
/***********************************Assets Transfer To Admin***********************************/
	public class AsyncSendDataTransferAssetsToAdmin extends
	AsyncTask<String, String, String> {

@Override
protected void onPreExecute() {
	super.onPreExecute();
	pDDialog = new SweetAlertDialog(act,
			SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
	pDDialog.show();

}

@Override
protected String doInBackground(String... params) {
	String message = null, response = null;
	try {

		response = MethodSoap.SendDataTransferAssetsToAdmin(prefs.getUserID(),
				prefs.getEmpCode(), prefs.getEmpName(),
				prefs.getUserName(), ciecleid_select, date_select,
				remarks_select, circle_select, transferkeyid, ProjCode, ProjName, ProjMgrCode,
				ProjMgrEmail, ProjMgrName, assetsmasnjaArray,
				assetsIdArray);

		JSONObject jsonobj = new JSONObject(response);
		message = jsonobj.getString("message");
		myresult = jsonobj.getString("DataArr");

	} catch (Exception e) {
		e.printStackTrace();
		pDDialog.dismiss();
		return message;
	}

	return message;

}

protected void onPostExecute(String result) {
	super.onPostExecute(result);
	try {
		if (!result.equals("") && !result.equals("null")
				&& result.equals("Success")) {

			pDDialog.dismiss();
			mydialog2 = new SweetAlertDialog(act,
					SweetAlertDialog.SUCCESS_TYPE)
					.setTitleText("Successfully")
					.setContentText(myresult)
					.setConfirmText("ok")
					.setConfirmClickListener(
							new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sDialog) {
									new AsyncTaskTransferAssets(act).execute();
									mydialog2.dismiss();
								}
							});
			mydialog2.show();

		} else if (result.equals("fail")) {
			new SweetAlertDialog(act,
					SweetAlertDialog.NORMAL_TYPE)
					.setTitleText("Oops...").setContentText(myresult)
					.show();
			pDDialog.dismiss();

		} else if (!connection.isConnectingToInternet()) {

			new SweetAlertDialog(act,
					SweetAlertDialog.ERROR_TYPE)
					.setTitleText("Oops...")
					.setContentText(
							"Internet Connection not available!")
					.show();
			pDDialog.dismiss();
		} else {

			new SweetAlertDialog(act,
					SweetAlertDialog.ERROR_TYPE)
					.setTitleText("Oops...")
					.setContentText("Does not get Proper Response !")
					.show();
			pDDialog.dismiss();
		}
	} catch (Exception e) {
		e.printStackTrace();
		pDDialog.dismiss();
	}

}
}
}
