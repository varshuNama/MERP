package com.mcerp.main;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;



public class GCMMessegeReceiver extends WakefulBroadcastReceiver
{
		protected String getGCMIntentServiceClassName(Context context) 
	{
	System.out.println(" GCMMessegeReceiver  gets called ");
	return GCMHelperService.class.getCanonicalName();
}

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// TODO Auto-generated method stub
		 ComponentName comp = new ComponentName(context.getPackageName(), GCMHelperService.class.getName());
	        // Start the service, keeping the device awake while it is launching.
	        startWakefulService(context, (intent.setComponent(comp)));
	        setResultCode(Activity.RESULT_OK);
	    
	}
}

