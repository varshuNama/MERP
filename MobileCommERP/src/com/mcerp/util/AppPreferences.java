package com.mcerp.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {

	private static final String SHARED_PREFERENCE_NAME = "PMRSSharedPrefernce";
	public static AppPreferences sAppPreference;
	private SharedPreferences mPreferences;
	public  Editor mEditor;

	enum SharedPreferncesKeys {
		UserID,Registered,GCMRegID,UserName,Password,Response,EmpCode,EmpName,ToWhich,Flag,MonthName,ProjectMgr,MonthYear,MailDesc,whichScreen;
	}

	/**
	 * private constructor for singleton class
	 * 
	 * @param context
	 */
	private AppPreferences(Context context) {
		mPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		mEditor = mPreferences.edit();
	}
	/**
	 * Enum for shared preferences keys to store various values
	 * 
	 * VARSHA NAMA <varsha.nama@mcpsinc.com> 
	 */

	public static AppPreferences getInstance(Context context) {
		if (sAppPreference == null)
			sAppPreference = new AppPreferences(context);
		return sAppPreference;
	}

	
	public boolean getRegistered() {
		return mPreferences.getBoolean(
				SharedPreferncesKeys.Registered.toString(), false);
	}

	public void setRegistered(boolean registered) {
		mEditor.putBoolean(SharedPreferncesKeys.Registered.toString(),
				registered);
		mEditor.commit();
	}
	public void setUserID(String UserId) {
		mEditor.putString(SharedPreferncesKeys.UserID.toString(), UserId);
		mEditor.commit();
	}
	public String getUserID() {
		return mPreferences.getString(SharedPreferncesKeys.UserID.toString(),"");
	}
	public String getUserName() {
		return mPreferences.getString(SharedPreferncesKeys.UserName.toString(),"");
	}
	
	public void setUserName(String UserName) {
		mEditor.putString(SharedPreferncesKeys.UserName.toString(), UserName);
		mEditor.commit();
	}

	public String getPassword() {
		return mPreferences.getString(SharedPreferncesKeys.Password.toString(),"");
	}
	
	public void setPassword(String Password) {
		mEditor.putString(SharedPreferncesKeys.Password.toString(), Password);
		mEditor.commit();
	}
	
	public String getResponse() {
		return mPreferences.getString(SharedPreferncesKeys.Response.toString(),"");
	}
	
	public void setResponse(String Response) {
		mEditor.putString(SharedPreferncesKeys.Response.toString(), Response);
		mEditor.commit();
	}
	
	public String getEmpCode() {
		return mPreferences.getString(SharedPreferncesKeys.EmpCode.toString(),"");
	}
	
	public void setEmpCode(String EmpCode) {
		mEditor.putString(SharedPreferncesKeys.EmpCode.toString(), EmpCode);
		mEditor.commit();
	}
	
	public String getEmpName() {
		return mPreferences.getString(SharedPreferncesKeys.EmpName.toString(),"");
	}
	
	public void setEmpName(String EmpName) {
		mEditor.putString(SharedPreferncesKeys.EmpName.toString(), EmpName);
		mEditor.commit();
	}
	public String getMonthName() {
		return mPreferences.getString(SharedPreferncesKeys.MonthName.toString(),"");
	}
	
	public void setMonthName(String MonthName) {
		mEditor.putString(SharedPreferncesKeys.MonthName.toString(), MonthName);
		mEditor.commit();
	}
	
	public String getProjectMgr() {
		return mPreferences.getString(SharedPreferncesKeys.ProjectMgr.toString(),"");
	}
	
	public void setProjectMgr(String ProjectMgr) {
		mEditor.putString(SharedPreferncesKeys.ProjectMgr.toString(), ProjectMgr);
		mEditor.commit();
	}
	public String getMonthYear() {
		return mPreferences.getString(SharedPreferncesKeys.MonthYear.toString(),"");
	}
	
	public void setMonthYear(String MonthYear) {
		mEditor.putString(SharedPreferncesKeys.MonthYear.toString(), MonthYear);
		mEditor.commit();
	}
	
	public String getMailDesc() {
		return mPreferences.getString(SharedPreferncesKeys.MailDesc.toString(),"");
	}
	
	public void setMailDesc(String MailDesc) {
		mEditor.putString(SharedPreferncesKeys.MailDesc.toString(), MailDesc);
		mEditor.commit();
	}
	
	
	public boolean getFlag() {
		return mPreferences.getBoolean(SharedPreferncesKeys.Flag.toString(), false);
	}

	public void setFlag(boolean registered) {
		mEditor.putBoolean(SharedPreferncesKeys.Flag.toString(),registered);
		mEditor.commit();
	}
	
	public void setScreen(int screen) {
		mEditor.putInt(SharedPreferncesKeys.whichScreen.toString(), screen);
		mEditor.commit();
	}

	public int getScreen() {
		return mPreferences.getInt(SharedPreferncesKeys.whichScreen.toString(), 0);
	}
	public void setGCMRegID(String GCMRegID) {
		mEditor.putString(SharedPreferncesKeys.GCMRegID.toString(), GCMRegID);
		mEditor.commit();
	}
	public String getGCMRegID() {
		return mPreferences.getString(SharedPreferncesKeys.GCMRegID.toString(),"");
	}
}
