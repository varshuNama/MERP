package com.mcerp.main;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mcerp.constant.Constant;

public class PlayServicesHelper {
	private static final String PROPERTY_APP_VERSION = "appVersion";

	private static final String TAG = "PlayServicesHelper";
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	private GoogleCloudMessaging googleCloudMessaging;
	private Activity activity;
	private String regId;

	public PlayServicesHelper(Activity activity) {
		this.activity = activity;
		checkPlayService();
	}

	public void checkPlayService() {

		if (checkPlayServices()) {
			googleCloudMessaging = GoogleCloudMessaging.getInstance(activity);
			regId = getRegistrationId();
			Log.w(TAG, "" + regId);
			if (regId.isEmpty()) {
				registerInBackground();
			}
		} else {
			Log.i(TAG, "No valid Google Play Services APK found.");
		}
	}

	public boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(activity);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				activity.finish();
			}
			return false;
		}
		return true;
	}

	public String getRegistrationId() {
		final SharedPreferences prefs = getGCMPreferences();
		String registrationId = prefs.getString(Constant.PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.w("GCM", "Registration not found.");
			return "";
		}

		return registrationId;
	}

	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (googleCloudMessaging == null) {
						googleCloudMessaging = GoogleCloudMessaging
								.getInstance(activity);
					}
					regId = googleCloudMessaging.register(Constant.GCM_SENDER_ID);
					msg = "Device registered, registration ID=" + regId;
					Log.w(TAG, "registration id");

					Handler h = new Handler(activity.getMainLooper());
					h.post(new Runnable() {
						@Override
						public void run() {
							subscribeToPushNotifications(regId);
						}
					});

					storeRegistrationId(regId);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					ex.printStackTrace();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Log.w("GCM", msg + "\n");
			}
		}.execute(null, null, null);
	}

	private SharedPreferences getGCMPreferences() {

		return activity.getSharedPreferences(activity.getPackageName(),
				Context.MODE_PRIVATE);
	}

	private void subscribeToPushNotifications(String regId) {

		Log.d(TAG, "subscribing...");

		@SuppressWarnings("unused")
		String deviceId;

		final TelephonyManager mTelephony = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephony.getDeviceId() != null) {
			deviceId = mTelephony.getDeviceId(); // *** use for mobiles
		} else {
			deviceId = Settings.Secure.getString(activity.getContentResolver(),
					Settings.Secure.ANDROID_ID); // *** use for tablets
		}

	}

	private void storeRegistrationId(String regId) {
		final SharedPreferences prefs = getGCMPreferences();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(Constant.PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, 1);
		editor.commit();
	}
}