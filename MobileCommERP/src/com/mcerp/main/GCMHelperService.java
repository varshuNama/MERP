package com.mcerp.main;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mcerp.constant.Constant;
import com.mcerp.main.R;
import com.mcerp.notification.InboxNotificationActivity;
import com.mcerp.util.AppPreferences;

public class GCMHelperService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	AppPreferences prefs;

	public GCMHelperService() {
		super("GCMHelperService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);
		prefs = AppPreferences.getInstance(this);
		System.out.println("GCM Got the message = " + intent.getDataString());
		System.out.println("GCM data = " + gcm.toString());
		if (extras != null && !extras.isEmpty()
				&& extras.getString("message") != null
				&& !extras.getString("message").isEmpty()) {
			sendNotification(extras);
		}
		GCMMessegeReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(Bundle extras) {

		try {

			mNotificationManager = (NotificationManager) this
					.getSystemService(Context.NOTIFICATION_SERVICE);
			System.out
					.println("sending push notification....opening application....");
			Intent intentShow = new Intent(this,
					InboxNotificationActivity.class);
			intentShow.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			intentShow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentShow.putExtra("pin", extras.getString("pin"));
			intentShow.putExtra("message", extras.getString("message"));
			Constant.GCM_MESSAGE = extras.getString("message");
			intentShow.putExtra(Constant.MSG_RECEIVERD_WHILE_OUTSIDE_APP,
					"MSG_RECEIVERD_WHILE_OUTSIDE_APP");

			PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
					intentShow, 0);
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					this)
					.setSmallIcon(R.drawable.icon)
					.setContentTitle("MobileComm")
					.setStyle(
							new NotificationCompat.BigTextStyle()
									.bigText(extras.getString("message")))
					.setContentText(extras.getString("message"));
			mBuilder.setAutoCancel(true);
			mBuilder.setContentIntent(contentIntent);
			mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

			AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			Vibrator messageVibrate;
			switch (am.getRingerMode()) {
			case AudioManager.RINGER_MODE_SILENT:
				break;
			case AudioManager.RINGER_MODE_VIBRATE:
				messageVibrate = (Vibrator) this
						.getSystemService(Context.VIBRATOR_SERVICE);
				messageVibrate.vibrate(2 * 1000);
				break;
			case AudioManager.RINGER_MODE_NORMAL:
				messageVibrate = (Vibrator) this
						.getSystemService(Context.VIBRATOR_SERVICE);
				messageVibrate.vibrate(2 * 1000);
				Uri notification = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Ringtone ringer = RingtoneManager.getRingtone(
						getApplicationContext(), notification);
				ringer.play();
				break;
			}

		} catch (Exception e) {

		}
	}

}
