package com.example.applist;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ApkInfo extends Activity {
	TextView appLabel, packageName, version, features;
	TextView permissions, andVersion, installed, lastModify, path;
	PackageInfo packageInfo;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appinfo);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb( 0, 0, 0)));
		bar.setDisplayHomeAsUpEnabled(true);
		findViewById();

		AppData appData = (AppData) getApplicationContext();
		packageInfo = appData.getPackageInfo();
		bar.setIcon(getPackageManager().getApplicationIcon(packageInfo.applicationInfo));
		bar.setTitle(getPackageManager().getApplicationLabel(
				packageInfo.applicationInfo));
		setValues();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.open:
			try {
				i = this.getPackageManager().getLaunchIntentForPackage(
						packageInfo.packageName);
				startActivity(i);
			} catch (Exception e) {
				Toast.makeText(getBaseContext(),
						"Its a secured app could not be opened",
						Toast.LENGTH_LONG).show();
			}
			break;
		/*
		 * case R.id.extract: String i = this.getExtractPath();
		 * Toast.makeText(getBaseContext(), i, Toast.LENGTH_SHORT).show();
		 * break; case R.id.uninstall: //.getListAdapter().getItem(featureId);
		 * uninstallPackage(packageName.toString());
		 * Toast.makeText(getBaseContext(), "Under Construction ",
		 * Toast.LENGTH_SHORT).show(); break;
		 */

		}
		return super.onMenuItemSelected(featureId, item);
	}

	@SuppressLint("NewApi")
	private void setValues() {
		// TODO Auto-generated method stub
		// APP name
		appLabel.setText(getPackageManager().getApplicationLabel(
				packageInfo.applicationInfo));

		// package name
		packageName.setText(packageInfo.packageName);

		// version name
		version.setText(packageInfo.versionName);

		// target version
		andVersion.setText(Integer
				.toString(packageInfo.applicationInfo.targetSdkVersion));

		// path
		path.setText(packageInfo.applicationInfo.sourceDir);

		// first installation
		installed.setText(setDateFormat(packageInfo.firstInstallTime));

		// last modified
		lastModify.setText(setDateFormat(packageInfo.lastUpdateTime));

		// features
		if (packageInfo.reqFeatures != null)
			features.setText(getFeatures(packageInfo.reqFeatures));
		else
			features.setText("-");

		// uses-permission
		if (packageInfo.requestedPermissions != null)
			permissions
					.setText(getPermissions(packageInfo.requestedPermissions));
		else
			permissions.setText("-");

	}

	private CharSequence getPermissions(String[] requestedPermissions) {
		// TODO Auto-generated method stub
		String permission = "";
		for (int i = 0; i < requestedPermissions.length; i++) {
			permission = permission + requestedPermissions[i] + ",\n";
		}
		return permission;
	}

	private CharSequence getFeatures(FeatureInfo[] reqFeatures) {
		// TODO Auto-generated method stub
		String features = "";
		for (int i = 0; i < reqFeatures.length; i++) {
			features = features + reqFeatures[i] + ",\n";
		}
		return features;

	}

	@SuppressLint("SimpleDateFormat")
	private CharSequence setDateFormat(long firstInstallTime) {
		// TODO Auto-generated method stub
		Date date = new Date(firstInstallTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(date);
		return strDate;
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		appLabel = (TextView) findViewById(R.id.applabel);
		packageName = (TextView) findViewById(R.id.package_name);
		version = (TextView) findViewById(R.id.version_name);
		features = (TextView) findViewById(R.id.req_feature);
		permissions = (TextView) findViewById(R.id.req_permission);
		andVersion = (TextView) findViewById(R.id.andversion);
		installed = (TextView) findViewById(R.id.insdate);
		lastModify = (TextView) findViewById(R.id.last_modify);
		path = (TextView) findViewById(R.id.path);
	}

	/*
	 * private String getExtractPath() { // TODO Auto-generated method stub
	 * return PreferenceManager.getDefaultSharedPreferences(this).getString(
	 * "extract_path", new File(Environment.getExternalStorageDirectory(),
	 * "ApkExtractor").getAbsolutePath()); }
	 * 
	 * private void uninstallPackage(String paramResolveInfo) { Uri localUri =
	 * Uri.fromParts("package", paramResolveInfo, null); Intent localIntent =
	 * new Intent("android.intent.action.DELETE");
	 * localIntent.setData(localUri); startActivity(localIntent); }
	 */
}
