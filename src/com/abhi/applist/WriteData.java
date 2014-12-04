package com.abhi.applist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.util.Log;

public class WriteData extends Activity {
	// List<PackageInfo> packageList;
	static String state;
	static StringBuilder data = new StringBuilder("");
	static File myfil;
	static PackageInfo info;

	public void makeFile(final List<PackageInfo> packageList1) { // TODO
		// Auto-generated method stub packageList = packageList1;
		state = Environment.getExternalStorageState();
		Thread a = new Thread() {
			public void run() {
				Environment.getExternalStorageState();
				if (state.equals(Environment.MEDIA_MOUNTED)) {

					String line = "";
					String l = "\n";
					for (int i = 0; i < packageList1.size(); i++) {
						info = packageList1.get(i);
						line = (MainActivity.packagemanager
								.getApplicationLabel(info.applicationInfo)
								.toString());
						Log.v("packageName", line);
						data.append(line + l);
					}
					try {
						File myFile = new File(Environment
								.getExternalStorageDirectory().toString()
								+ "/AppAndApkList");
						if (!myFile.exists())
							myFile.mkdirs();
						myFile = new File(myFile.getPath() + "/"
								+ "installedapps" + ".txt");
						myFile.createNewFile();
						BufferedWriter out = new BufferedWriter(new FileWriter(
								myFile));
						out.write(data.toString(), 0, data.length());
						out.close();
						OutputStream fOut = new FileOutputStream(myFile);
						OutputStreamWriter myOutWriter = new OutputStreamWriter(
								fOut);
						myOutWriter.write(data.toString(), 0, data.length());
						myOutWriter.close();
						fOut.close();
						Log.v("file created", "true");
					} catch (IOException e) { //
						/* TODO Auto-generated catch block */
						e.printStackTrace();
					}
				}
			}
		};
		a.start();
	}
}
