package com.utils;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

public class OSUtils {
	 public static boolean hasFroyo() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	    }

	    public static boolean hasGingerbread() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	    }

	    public static boolean hasHoneycomb() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	    }

	    public static boolean hasHoneycombMR1() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
	    }

	    public static boolean hasJellyBean() {
	        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	    }
	    
	    public static void showToast(Context context, CharSequence msg){
	    	Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	    }
}
