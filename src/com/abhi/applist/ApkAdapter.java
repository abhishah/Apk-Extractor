package com.abhi.applist;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.abhi.applist.R;

public class ApkAdapter extends BaseExpandableListAdapter {
	List<PackageInfo> packageList;
	List<Boolean> checked;
	MainActivity context;
	PackageManager packagemanager;
	final List<CheckBox> selected = new ArrayList<CheckBox>();
	boolean visiblity = false;
	GroupPackage inform;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static List<String> a = new ArrayList();
	static SparseBooleanArray mCheckStates;

	public ApkAdapter(Activity mainActivity, List<PackageInfo> packageList1,
			PackageManager packagemanager, List<Boolean> selection) {
		// TODO Auto-generated constructor stub
		super();
		this.context = (MainActivity) mainActivity;
		this.packageList = packageList1;
		this.packagemanager = packagemanager;
		checked = selection;
		MainActivity.selected = new ArrayList<PackageInfo>();
		inform = new GroupPackage(packageList1, selection);
		mCheckStates = new SparseBooleanArray(packageList.size());
		a.add("Extract");
		a.add("AppInfo");
		a.add("Open");
	}

	public void checkable(boolean visible) {
		visiblity = visible;
	}

	public class ViewHolder {
		TextView apkname;
		CheckBox ckselect;
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
		// selected=(CheckBox)convertView.findViewById(R.id.select);
		// if(visiblity)selected.setVisibility(View.VISIBLE);
		// else selected.setVisibility(View.VISIBLE);
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

	public Object getGroupPackage() {
		return inform;
	}

	public List<CheckBox> getCheckbox() {
		return selected;
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
			holder.ckselect = (CheckBox) convertView.findViewById(R.id.select);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final PackageInfo packageInfo = (PackageInfo) getItem(groupPosition);
		final int location = inform.packagelist.indexOf(packageInfo);
		Drawable appIcon = packagemanager
				.getApplicationIcon(packageInfo.applicationInfo);
		String appName = packagemanager.getApplicationLabel(
				packageInfo.applicationInfo).toString();
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
				r.getDisplayMetrics());
		int pix = (int) px;
		appIcon.setBounds(0, 0, pix, pix);
		holder.apkname.setCompoundDrawables(appIcon, null, null, null);
		holder.apkname.setCompoundDrawablePadding(15);
		holder.apkname.setText(appName);
		holder.apkname.setPadding(10, 10, 10, 10);
		if (inform.getBackground(location)) {
			convertView.setBackgroundColor(color.holo_blue_bright);
		} else
			convertView.setBackgroundColor(color.white);
		holder.ckselect.setTag(groupPosition);
		selected.add(location, holder.ckselect);
		holder.ckselect.setChecked(mCheckStates.get(groupPosition, false));
		holder.ckselect
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						mCheckStates.put((Integer) buttonView.getTag(),
								isChecked);
						context.action.setSubtitle(context.checkedcount()
								+ " Items selected");
					}
				});
		// holder.ckselect.setChecked(mCheckStates.get(groupPosition, false));
		return convertView;
		// return null;
	}

	public boolean isChecked(int position) {
		return mCheckStates.get(position, false);
	}

	public void setChecked(int position, boolean isChecked) {
		mCheckStates.put(position, isChecked);

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
