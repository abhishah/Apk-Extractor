package com.example.applist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ApkAdapter extends BaseExpandableListAdapter {
	List<PackageInfo> packageList;
	Activity context;
	PackageManager packagemanager;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static List<String> a = new ArrayList();

	public ApkAdapter(Activity mainActivity, List<PackageInfo> packageList1,
			PackageManager packagemanager) {
		// TODO Auto-generated constructor stub
		super();
		this.context = mainActivity;
		this.packageList = packageList1;
		this.packagemanager = packagemanager;
		a.add("Extract");
		a.add("AppInfo");
		a.add("Open");
	}

	public class ViewHolder {
		TextView apkname;
	}

	// @Override
	public int getCount() {
		// TODO Auto-generated method stub
		return packageList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return packageList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * public View getView(int position, View convertView, ViewGroup parent) {
	 * // TODO Auto-generated method stub ViewHolder holder; LayoutInflater
	 * inflater = context.getLayoutInflater();
	 * 
	 * if (convertView == null) { convertView =
	 * inflater.inflate(R.layout.apklist_item, null); holder = new ViewHolder();
	 * 
	 * holder.apkname = (TextView) convertView.findViewById(R.id.appname);
	 * convertView.setTag(holder); } else { holder = (ViewHolder)
	 * convertView.getTag(); }
	 * 
	 * PackageInfo packageInfo = (PackageInfo) getItem(position); Drawable
	 * appIcon = packagemanager
	 * .getApplicationIcon(packageInfo.applicationInfo); String appName =
	 * packagemanager.getApplicationLabel(
	 * packageInfo.applicationInfo).toString(); appIcon.setBounds(0, 0, 40, 40);
	 * holder.apkname.setCompoundDrawables(appIcon, null, null, null);
	 * holder.apkname.setCompoundDrawablePadding(15);
	 * holder.apkname.setText(appName);
	 * 
	 * return convertView;
	 * 
	 * }
	 */

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return a.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final String childText = (String) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item, null);
		}
		// convertView.s
		TextView b = (TextView) convertView.findViewById(R.id.btext);
		b.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return packageList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return packageList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.apklist_item, null);
			holder = new ViewHolder();

			holder.apkname = (TextView) convertView.findViewById(R.id.appname);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PackageInfo packageInfo = (PackageInfo) getItem(groupPosition);
		Drawable appIcon = packagemanager
				.getApplicationIcon(packageInfo.applicationInfo);
		String appName = packagemanager.getApplicationLabel(
				packageInfo.applicationInfo).toString();
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55,
				r.getDisplayMetrics());
		int pix = (int) px;
		appIcon.setBounds(0, 0, pix, pix);
		holder.apkname.setCompoundDrawables(appIcon, null, null, null);
		holder.apkname.setCompoundDrawablePadding(15);
		holder.apkname.setText(appName);
		holder.apkname.setPadding(10, 10, 10, 10);
		return convertView;
		// return null;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
