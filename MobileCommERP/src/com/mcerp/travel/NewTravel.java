package com.mcerp.travel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.mcerp.asyncheck.MethodSoap;
import com.mcerp.connection.ConnectionDetector;
import com.mcerp.main.NavigationActivity;
import com.mcerp.main.R;
import com.mcerp.model.NewTravelModel;
import com.mcerp.timpicker.MyTimePickerDialog;
import com.mcerp.timpicker.TimePicker;
import com.mcerp.util.AppPreferences;

public class NewTravel extends Fragment implements OnClickListener {
	View rootView;
	EditText startdate, enddate, starttime, endtime, fromlocation, tolocation,travelamt, travelkm, remarks;
	Spinner transmode;
	Button submit_btn;
	TextView empname, projectmgrname, projectcode, projectname;
	int flag = 0,hhhh, mmmm, ssss;
	String responseData,responsedata;
	String sd1, sd2, sd3, ed1, ed2, ed3,st1, st2, st3, et1, et2, et3;
	ImageView capture_btn, image_view;
	private int mYear, mMonth, mDay;
	AppPreferences prefs;
	SweetAlertDialog pDialog, mydialog3;
	long StarttimeInMilliseconds, EndttimeInMilliseconds,EnddateInMilliseconds, StartdateInMilliseconds;
	ArrayList<NewTravelModel> arraylistdata;
	ConnectionDetector connection;
	String startDATE, endDATE, startTIME, endTIME, FROMLOCATION, TOLOCATION,
	tansferMODE, TRAVELAMOUNT, TRAVELKM, Remarks, STARTDATE, ENDDATE, STARTTIME, 
	ENDTIME, TRANSFERMODE,ENCODEDIMAGE ="NA",myresult;
	final static int REQUEST_TAKE_PICTURE = 1000;
	private static final int REQUEST_SEND_IMAGE = 2000;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_project_travel_new,
				container, false);

		init();
		new AsyncTaskNewTravel().execute();
		return rootView;
	}
	
/************************************Initialize All the variables*********************************************************/
	private void init() {

		startdate = (EditText) rootView.findViewById(R.id.startDate);
		enddate = (EditText) rootView.findViewById(R.id.endDate);
		starttime = (EditText) rootView.findViewById(R.id.startTime);
		endtime = (EditText) rootView.findViewById(R.id.endtime);
		fromlocation = (EditText) rootView.findViewById(R.id.fromLocation);
		tolocation = (EditText) rootView.findViewById(R.id.toLocation);
		capture_btn = (ImageView) rootView.findViewById(R.id.camera_btn);
		image_view = (ImageView) rootView.findViewById(R.id.capture_image);
		travelamt = (EditText) rootView.findViewById(R.id.travelAmt);
		travelkm = (EditText) rootView.findViewById(R.id.travelKm);
		remarks = (EditText) rootView.findViewById(R.id.remarks_travel);
		submit_btn = (Button) rootView.findViewById(R.id.submit_travel);
		transmode = (Spinner) rootView.findViewById(R.id.transMode);
		empname = (TextView) rootView.findViewById(R.id.empname_travel);
		projectmgrname = (TextView) rootView.findViewById(R.id.projmgrname_travel);
		projectcode = (TextView) rootView.findViewById(R.id.projectcode_travel);
		projectname = (TextView) rootView.findViewById(R.id.projectname_travel);
		connection = new ConnectionDetector(getActivity());

		transmode.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					TRANSFERMODE = "AU";
					travelkm.setEnabled(false);
					travelamt.setEnabled(true);
					travelkm.setText("");
					break;
				case 1:
					TRANSFERMODE = "BI";
					travelkm.setEnabled(false);
					travelamt.setEnabled(true);
					travelkm.setText("");
					break;
				case 2:
					TRANSFERMODE = "BU";
					travelkm.setEnabled(true);
					travelamt.setEnabled(false);
					travelamt.setText("");
					break;
				case 3:
					TRANSFERMODE = "CA";
					travelkm.setEnabled(false);
					travelamt.setEnabled(true);
					travelkm.setText("");
					break;
				case 4:
					TRANSFERMODE = "FI";
					travelkm.setEnabled(false);
					travelamt.setEnabled(true);
					travelkm.setText("");
					break;
				case 5:
					TRANSFERMODE = "OC";
					travelkm.setEnabled(true);
					travelamt.setEnabled(false);
					travelamt.setText("");
					break;
				case 6:
					TRANSFERMODE = "TR";
					travelkm.setEnabled(false);
					travelamt.setEnabled(true);
					travelkm.setText("");
					break;
				default:
					break;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		startdate.setOnClickListener(this);
		enddate.setOnClickListener(this);
		starttime.setOnClickListener(this);
		endtime.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
		capture_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
				Locale.getDefault());
		switch (v.getId()) {
		
		/**********************************Show DatePicker for StartDate*********************************************/
		case R.id.startDate:
			final Calendar c = Calendar.getInstance();

			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);

			c.getMaximum(mMonth);

			// Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(getActivity(),
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							
							startdate.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);

						}
					}, mYear, mMonth, mDay);
			Calendar minDate = Calendar.getInstance();
			try {
				minDate.setTime(formatter.parse(mDay + "." + (mMonth + 1) + "."
						+ mYear));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			DatePicker datePicker = dpd.getDatePicker();
			datePicker.setMaxDate(minDate.getTimeInMillis());

			dpd.show();
			break;
			
	/**************************************************Show DatePicker for EndDate*********************************************/
		case R.id.endDate:

			final Calendar c1 = Calendar.getInstance();

			mYear = c1.get(Calendar.YEAR);
			mMonth = c1.get(Calendar.MONTH);
			mDay = c1.get(Calendar.DAY_OF_MONTH);

			// Launch Date Picker Dialog
			DatePickerDialog dpdd = new DatePickerDialog(getActivity(),
					new DatePickerDialog.OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {

							enddate.setText(dayOfMonth + "-"
									+ (monthOfYear + 1) + "-" + year);

						}
					}, mYear, mMonth, mDay);
			Calendar MinDate = Calendar.getInstance();

			try {
				MinDate.setTime(formatter.parse(mDay + "." + (mMonth + 1) + "."
						+ mYear));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			DatePicker DatePicker = dpdd.getDatePicker();
			DatePicker.setMaxDate(MinDate.getTimeInMillis());

			dpdd.show();
			break;
			
       /**********************************Show TimePicker for StartTime************************************************************/
		case R.id.startTime:

			Calendar now = Calendar.getInstance();
			hhhh = now.get(Calendar.HOUR_OF_DAY);
			mmmm = now.get(Calendar.MINUTE);
			ssss = now.get(Calendar.SECOND);
			MyTimePickerDialog mTimePicker = new MyTimePickerDialog(
					getActivity(), new MyTimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute, int seconds) {
							// TODO Auto-generated method stub
							starttime.setText(String.format("%02d", hourOfDay)
									+ ":" + String.format("%02d", minute) + ":"
									+ String.format("%02d", seconds));
						
						}

					}, hhhh, mmmm, ssss, true);

			mTimePicker.show();

			break;
			
       /**********************************Show TimePicker for EndTime************************************************************/
		case R.id.endtime:

			Calendar end = Calendar.getInstance();
			MyTimePickerDialog mmTimePicker = new MyTimePickerDialog(
					getActivity(), new MyTimePickerDialog.OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute, int seconds) {
							endtime.setText(String.format("%02d", hourOfDay)
									+ ":" + String.format("%02d", minute) + ":"
									+ String.format("%02d", seconds));
							
						}

					}, end.get(Calendar.HOUR_OF_DAY), end.get(Calendar.MINUTE),
					end.get(Calendar.SECOND), true);
			mmTimePicker.show();
			break;
		case R.id.camera_btn:

			startPhotoTaker();

			break;
			
     /**********************************Submit Data To Server***************************************/
		case R.id.submit_travel:		
			FROMLOCATION = fromlocation.getText().toString().trim();
			TOLOCATION = tolocation.getText().toString().trim();
			TRAVELAMOUNT = travelamt.getText().toString().trim();
			TRAVELKM = travelkm.getText().toString().trim();
			Remarks = remarks.getText().toString().trim();
			STARTDATE = startdate.getText().toString().trim();
			ENDDATE = enddate.getText().toString().trim();
			STARTTIME = starttime.getText().toString().trim();
			ENDTIME = endtime.getText().toString().trim();		
		
			if (TRANSFERMODE.equals("BU") || TRANSFERMODE.equals("OC")) {
				sendDataToBUSOwnCarServer();
			} else {
				sendDataToOtherVehicleServer();
			}
			break;
		default:
			break;
		}

	}

	/*********** Validation are on select Bus and Own car ***************/

	private void sendDataToBUSOwnCarServer() {
		if (Remarks.equals("") || STARTDATE.equals("") || ENDDATE.equals("")
				|| STARTTIME.equals("") || ENDTIME.equals("")
				|| FROMLOCATION.equals("") || TOLOCATION.equals("")
				|| TRAVELKM.equals("")) {

			Toast.makeText(getActivity(), "Fields cannot be left blank.",
					Toast.LENGTH_LONG).show();

		} else {
			StartdateInMilliseconds=ConverDateInToMilliseconds(STARTDATE);
			EnddateInMilliseconds=ConverDateInToMilliseconds(ENDDATE);
			StarttimeInMilliseconds=ConverTimeInToMilliseconds(STARTTIME);
			EndttimeInMilliseconds=ConverTimeInToMilliseconds(ENDTIME);
			
			startDATE = getData(startdate.getText().toString());
			endDATE = getData(enddate.getText().toString());
			startTIME = starttime.getText().toString();
			endTIME = endtime.getText().toString();

			if ((StartdateInMilliseconds + StarttimeInMilliseconds) < (EnddateInMilliseconds + EndttimeInMilliseconds)) {
				TRAVELAMOUNT = "0";
				new AsyncTaskSendTravelData().execute();
			} else {

				Toast.makeText(
						getActivity(),
						"Start date should not be grater than enddate or Starttime should not be grater than endtime.",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	/*********** Validation are select on other vehicle ***************/

	private void sendDataToOtherVehicleServer() {
		if (Remarks.equals("") || STARTDATE.equals("") || ENDDATE.equals("")
				|| STARTTIME.equals("") || ENDTIME.equals("")
				|| FROMLOCATION.equals("") || TOLOCATION.equals("")
				|| TRAVELAMOUNT.equals("")) {

			Toast.makeText(getActivity(), "Fields cannot be left blank.",
					Toast.LENGTH_LONG).show();

		} else {
			StartdateInMilliseconds=ConverDateInToMilliseconds(STARTDATE);
			EnddateInMilliseconds=ConverDateInToMilliseconds(ENDDATE);
			StarttimeInMilliseconds=ConverTimeInToMilliseconds(STARTTIME);
			EndttimeInMilliseconds=ConverTimeInToMilliseconds(ENDTIME);
			startDATE = getData(startdate.getText().toString());

			endDATE = getData(enddate.getText().toString());

			startTIME = starttime.getText().toString();

			endTIME = endtime.getText().toString();

			if ((StartdateInMilliseconds + StarttimeInMilliseconds) <= (EnddateInMilliseconds + EndttimeInMilliseconds)) {
				TRAVELKM = "0";
				new AsyncTaskSendTravelData().execute();
			} else {

				Toast.makeText(
						getActivity(),
						"Start date should not be grater than enddate or Starttime should not be grater than endtime.",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	/************************************AsyncTaskNewTravel************************************************/

	class AsyncTaskNewTravel extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			prefs = AppPreferences.getInstance(getActivity());
			pDialog = new SweetAlertDialog(getActivity(),
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");

			arraylistdata = new ArrayList<NewTravelModel>();
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
		}

		@Override
		protected String doInBackground(String... params) {
			String Message = null;
			try {
				responsedata = MethodSoap
						.getNewTravelGetData(prefs.getUserID());
				JSONObject jsonobj = new JSONObject(responsedata);
				Message = jsonobj.getString("message");
				myresult = jsonobj.getString("DataArr");
				if (Message.equals("success")) {

					JSONArray jsonarray = new JSONArray(myresult);
					for (int i = 0; i < jsonarray.length(); i++) {
						JSONObject json = jsonarray.getJSONObject(i);
						NewTravelModel data = new NewTravelModel();
						data.setProjAllocationId(json
								.getString("ProjAllocationId"));
						data.setProjCode(json.getString("ProjCode"));
						data.setProjName(json.getString("ProjName"));
						data.setProjMgrId(json.getString("ProjMgrId"));
						data.setProjMgrCode(json.getString("ProjMgrCode"));
						data.setProjMgrEmail(json.getString("ProjMgrEmail"));
						data.setProjMgrName(json.getString("ProjMgrName"));
						arraylistdata.add(data);

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				Message = "";
				pDialog.dismiss();

			}

			return Message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (result != null && !result.equals("")
						&& !result.equals("null") && result.equals("success")) {
					pDialog.dismiss();
					empname.setText(prefs.getEmpName().toString());
					projectmgrname.setText(arraylistdata.get(0)
							.getProjMgrName());
					projectcode.setText(arraylistdata.get(0).getProjCode());
					projectname.setText(arraylistdata.get(0).getProjName());

				} else if (!connection.isConnectingToInternet()) {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Connection not available!").show();
					pDialog.dismiss();
				} else {
					if (flag == 1) {
						flag = 0;
						mydialog3 = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Does not get proper response.")
								.setContentText("Server Connection Problem!")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												Intent intent = new Intent(
														getActivity(),
														NavigationActivity.class);
												startActivity(intent);
												((Activity) getActivity())
														.finish();

												mydialog3.dismiss();
											}
										});
						mydialog3.show();
					} else {
						mydialog3 = new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText("Does not get proper response.")
								.setContentText("No Record Found!!")
								.setConfirmText("ok")
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												Intent intent = new Intent(
														getActivity(),
														NavigationActivity.class);
												startActivity(intent);
												((Activity) getActivity())
														.finish();

												mydialog3.dismiss();
											}
										});
						mydialog3.show();
					}
				}
			} catch (Exception e) {

			}

		}
	}

	/*******************************************AsycTaskSendTravelData***********************************************/

	class AsyncTaskSendTravelData extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new SweetAlertDialog(getActivity(),
					SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading");
			prefs = AppPreferences.getInstance(getActivity());
			pDialog.show();
			pDialog.setCancelable(false);
			pDialog.setCanceledOnTouchOutside(false);
		}

		@Override
		protected String doInBackground(String... params) {
			String Message = null;

			try {
				responsedata = MethodSoap.sendNewTravelData(prefs.getUserID(),
						prefs.getEmpCode(), prefs.getEmpName(), prefs
								.getUserName(), arraylistdata.get(0)
								.getProjCode(), arraylistdata.get(0)
								.getProjName(), arraylistdata.get(0)
								.getProjMgrId(), arraylistdata.get(0)
								.getProjMgrCode(), arraylistdata.get(0)
								.getProjMgrName(), arraylistdata.get(0)
								.getProjMgrEmail(), arraylistdata.get(0)
								.getProjAllocationId(), startDATE, endDATE,
						startTIME, endTIME, FROMLOCATION, TOLOCATION,
						TRAVELAMOUNT, TRAVELKM, Remarks, ENCODEDIMAGE,
						TRANSFERMODE);
				JSONObject jsonobj = new JSONObject(responsedata);
				Message = jsonobj.getString("message");
				responseData = jsonobj.getString("DataArr");

			} catch (Exception e) {
				e.printStackTrace();
				flag = 1;
				Message = "";
				pDialog.dismiss();

			}

			return Message;

		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				if (result != null && !result.equals("")
						&& !result.equals("null") && result.equals("success")) {
					startdate.setText("");
					enddate.setText("");
					starttime.setText("");
					endtime.setText("");
					fromlocation.setText("");
					tolocation.setText("");
					transmode.setSelection(0);
					travelamt.setText("");
					travelkm.setText("");
					remarks.setText("");
					image_view.setImageResource(R.drawable.dummyimg);
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.SUCCESS_TYPE)
							.setTitleText("Successfully")
							.setContentText(responseData).show();
					pDialog.dismiss();

				} else if (result.equals("fail")) {
					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.WARNING_TYPE)
							.setTitleText("Oops...")
							.setContentText(responseData).show();
					pDialog.dismiss();
				} else if (!connection.isConnectingToInternet()) {

					new SweetAlertDialog(getActivity(),
							SweetAlertDialog.ERROR_TYPE)
							.setTitleText("Oops...")
							.setContentText("Connection not available!").show();
					pDialog.dismiss();
				} else {
					if (flag == 1) {
						flag = 0;
						new SweetAlertDialog(getActivity(),
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("Server Connection Problem.")
								.show();
						pDialog.dismiss();

					} else {
						new SweetAlertDialog(getActivity(),
								SweetAlertDialog.ERROR_TYPE)
								.setTitleText("Oops...")
								.setContentText("No Record Found.").show();
						pDialog.dismiss();
					}
				}
			} catch (Exception e) {

			}

		}
	}

	Uri mLastPhoto = null;

	/************************************* Capture Image Through Camera *********************************************************************************/
	void startPhotoTaker() {

		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File photo = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
				"cs_" + new Date(0).getTime() + ".jpg");
		mLastPhoto = Uri.fromFile(photo);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mLastPhoto);

		// start the image capture Intent
		startActivityForResult(intent, REQUEST_TAKE_PICTURE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == FragmentActivity.RESULT_OK) {

			if (requestCode == REQUEST_SEND_IMAGE) {
				Log.d("REQUEST_SEND_IMAGE", REQUEST_SEND_IMAGE + "");
				Uri uri = data.getData();
				if (uri == null) {
					return;
				}
				String tempPath = getRealPathFromURI(uri);
				Bitmap bm = decodeSampledBitmapFromFile(tempPath, 200, 200);
				Drawable dm = new BitmapDrawable(bm);
				setimageToImageview(dm);

			} else if (requestCode == REQUEST_TAKE_PICTURE) {
				// Log.d("REQUEST_TAKE_PICTURE", REQUEST_TAKE_PICTURE+"");
				File file = new File(getRealPathFromURI(mLastPhoto));
				final Handler handler = new Handler();
				MediaScannerConnection.scanFile(getActivity(),
						new String[] { file.toString() }, null,
						new MediaScannerConnection.OnScanCompletedListener() {
							public void onScanCompleted(String path,
									final Uri uri) {

								handler.post(new Runnable() {
									@Override
									public void run() {
										if (mLastPhoto == null) {
											return;
										}
										String tempPath = getRealPathFromURI(mLastPhoto);
										Bitmap bm = decodeSampledBitmapFromFile(
												tempPath, 200, 200);

										ByteArrayOutputStream baos = new ByteArrayOutputStream();
										bm.compress(Bitmap.CompressFormat.JPEG,
												100, baos); // bm is the bitmap
															// object
										byte[] b = baos.toByteArray();
										ENCODEDIMAGE = Base64.encodeToString(b,
												Base64.DEFAULT);
										Drawable draw = new BitmapDrawable(bm);

										setimageToImageview(draw);
									}
								});
							}
						});

			}
		}
	}

	@SuppressWarnings("deprecation")
	public String getRealPathFromURI(Uri contentUri) {

		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = getActivity().managedQuery(contentUri, proj, null,
					null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} catch (Exception e) {
			return contentUri.getPath();
		}
	}

	public void setimageToImageview(Drawable bm) {

		image_view.setImageDrawable(bm);

	}

	/******* Convert Image into bitmap *******************************************/
	public static Bitmap decodeSampledBitmapFromFile(String pathName,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/************************* Convert Date into Formatted Date *********************************/

	public String getData(String dateFrom) {
		
		String formated_date;

		String[] strdatefrom = dateFrom.split("-");
		if (strdatefrom[0].length() < 2) {
			strdatefrom[0] = "0" + strdatefrom[0];
		}
		if (strdatefrom[1].length() < 2) {
			strdatefrom[1] = "0" + strdatefrom[1];
		}
		formated_date = strdatefrom[2] + strdatefrom[1] + strdatefrom[0];

		return formated_date;
	}

	/***************** convert date int to Milliseconds ****************/
	public long ConverDateInToMilliseconds(String dateTo) {
		long timeinmills;
		Calendar calendar = Calendar.getInstance();
		String[] strdatefrom = dateTo.split("-");	
		calendar.set(Integer.parseInt(strdatefrom [2]), Integer.parseInt(strdatefrom [1]), Integer.parseInt(strdatefrom [0]));
		timeinmills = calendar.getTimeInMillis();
		return timeinmills;
	}
	/***************** convert time int to Milliseconds ****************/
	public long ConverTimeInToMilliseconds(String TimeTo) {
		long timeinmills;
		int hour,minute,seconds;	
		String[] strtimefrom = TimeTo.split(":");
		hour=Integer.parseInt(strtimefrom[0]) * 3600000;
		minute=Integer.parseInt(strtimefrom[1]) * 60000;
		seconds=Integer.parseInt(strtimefrom[2]) * 1000;
		timeinmills = hour+minute+seconds;
		return timeinmills;
	}
  
}
