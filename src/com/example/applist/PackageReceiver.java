package com.example.applist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PackageReceiver extends BroadcastReceiver {
	public void onReceive(Context paramContext, Intent paramIntent) {
		Log.v("@@@", "there is a broadcast " + paramIntent);
		if (("android.intent.action.PACKAGE_ADDED".equals(paramIntent
				.getAction()))
				|| ("android.intent.action.PACKAGE_INSTALL".equals(paramIntent
						.getAction()))
				|| ("android.intent.action.PACKAGE_REMOVED".equals(paramIntent
						.getAction()))) {
			SharedPreferences.Editor localEditor = PreferenceManager
					.getDefaultSharedPreferences(paramContext).edit();
			localEditor.putBoolean("needReload", true);
			localEditor.commit();
		}
	}
}
