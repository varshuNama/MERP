package com.mcerp.util;

import android.content.Context;
import android.graphics.Typeface;

public class Utility {
	public static Typeface Ascom_Zwo_BoldPS = null;
	
	public static void initFonts(Context context) {
		Ascom_Zwo_BoldPS = Typeface.createFromAsset(context.getAssets(), "Ascom_Zwo_BoldPS.otf");
		
	}
}
