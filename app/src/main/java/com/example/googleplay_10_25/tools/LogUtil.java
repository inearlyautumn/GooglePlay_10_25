package com.example.googleplay_10_25.tools;

import android.util.Log;

public class LogUtil {
	private static boolean isDebug = true;//
	public static void i(String tag,String msg){
		if(isDebug){
			Log.i(tag, msg);
		}
	}
	public static void i(Object obj,String msg){
		if(isDebug){
			Log.i(obj.getClass().getSimpleName(), msg);
		}
	}
	public static void e(String tag,String msg){
		if(isDebug){
			Log.e(tag, msg);
		}
	}
	public static void e(Object obj,String msg){
		if(isDebug){
			Log.e(obj.getClass().getSimpleName(), msg);
		}
	}
}
