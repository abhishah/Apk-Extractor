package com.example.applist;

import android.app.Application;
import android.content.pm.PackageInfo;

public class AppData extends Application {
	PackageInfo packageinfo;

	public void setPackageInfo(PackageInfo packageinfo) {
		// TODO Auto-generated method stub
		this.packageinfo = packageinfo;
	}

	public PackageInfo getPackageInfo() {
		return this.packageinfo;
	}

}
