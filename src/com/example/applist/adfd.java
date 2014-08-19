package com.example.applist;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class adfd extends Activity{
	/* private void sortOnBackgrount(int paramInt)
	  {
	    SortTask localSortTask = new SortTask(this);
	    Integer[] arrayOfInteger = new Integer[3];
	    arrayOfInteger[0] = Integer.valueOf(paramInt);
	    arrayOfInteger[1] = null;
	    arrayOfInteger[2] = null;
	    localSortTask.execute(arrayOfInteger);
	  }
	 private class SortTask
	    extends AsyncTask<Integer, Void, List<ResolveInfo>>
	  {
	    private Context context;
	    private Dialog dialog;
	    
	    public SortTask(Context paramContext)
	    {
	      this.context = paramContext;
	    }
	    
	    protected List<ResolveInfo> doInBackground(Integer... paramVarArgs)
	    {
	      Intent localIntent = new Intent("android.intent.action.MAIN", null);
	      localIntent.addCategory("android.intent.category.LAUNCHER");
	      List localList = MainActivity.this.getPackageManager().queryIntentActivities(localIntent, 0);
	      for (int i = -1 + localList.size();; i--)
	      {
	        if (i < 0) {}
	        switch (paramVarArgs[0].intValue())
	        {
	        default: 
	          return localList;
	          ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
	          if (localResolveInfo.activityInfo.applicationInfo.sourceDir.startsWith("/data/app-private/")) {
	            localList.remove(localResolveInfo);
	          }
	          break;
	        }
	      }
	      Collections.sort(localList, ApkExtractor.this.fileSizeComparator);
	      return localList;
	      Collections.sort(localList, ApkExtractor.this.appNameComparator);
	      return localList;
	    }
	    
	    protected void onPostExecute(List<ResolveInfo> paramList)
	    {
	      super.onPostExecute(paramList);
	      ApkExtractor.this.adapter = new ApkExtractor.AppListAdapter(this.context, 2131099652, paramList);
	      ApkExtractor.this.setListAdapter(ApkExtractor.this.adapter);
	      this.dialog.dismiss();
	    }
	    
	    protected void onPreExecute()
	    {
	      super.onPreExecute();
	      if (ApkExtractor.this.adapter != null) {
	        ApkExtractor.this.adapter.clear();
	      }
	      this.dialog = new Dialog(this.context, 2131296257);
	      this.dialog.addContentView(new ProgressBar(this.context), new ViewGroup.LayoutParams(-2, -2));
	      this.dialog.show();
	    }*/
	  }