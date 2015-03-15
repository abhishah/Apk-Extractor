package com.abhi.applist;

import java.util.ArrayList;
import java.util.List;

import com.abhi.applist.ApkAdapter.ViewHolder;

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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class Adapter extends ArrayAdapter{

	List<PackageInfo> packageList;
	List<Boolean> checked;
	ListView lv_parent;
	MainActivity context;
	PackageManager packagemanager;
	final List<CheckBox> selected = new ArrayList<CheckBox>();
	boolean visiblity = false;
	GroupPackage inform;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static List<String> a = new ArrayList();
	static SparseBooleanArray mCheckStates;

	public Adapter(Activity mainActivity, List<PackageInfo> packageList1,
			PackageManager packagemanager, List<Boolean> selection) {
		// TODO Auto-generated constructor stub
        super(mainActivity,0);
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
		a.add("Share");
	}
	public void checkable(boolean visible) {
		visiblity = visible;
	}
	public class ViewHolder {
		TextView apkname;
		CheckBox ckselect;
		ImageView overflow;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return packageList.get(position);
	}
	
	public Object getGroupPackage() {
		return inform;
	}

	 @Override
		public int getCount() {
			// TODO Auto-generated method stub
			return packageList.size();
		}
		
	public List<CheckBox> getCheckbox() {
		return selected;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		LayoutInflater inflater = context.getLayoutInflater();
         lv_parent=(ListView)parent;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.apklist_item, null);
			holder = new ViewHolder();
			holder.apkname = (TextView) convertView.findViewById(R.id.appname);
			holder.ckselect = (CheckBox) convertView.findViewById(R.id.select);
			holder.overflow=(ImageView) convertView.findViewById(R.id.overflow_in_li);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final PackageInfo packageInfo = (PackageInfo) getItem(position);
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
			convertView.setBackgroundColor(0xFFFFFFFF);
		holder.ckselect.setTag(position);
		selected.add(location, holder.ckselect);
		holder.ckselect.setChecked(mCheckStates.get(position, false));
		holder.ckselect
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						mCheckStates.put((Integer) buttonView.getTag(),
								isChecked);
				//		context.action.setSubtitle(context.checkedcount()
					//			+ " Items selected");
					}
				});
		holder.apkname.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				 lv_parent.performItemClick(v, position,(long) 0);
				 return true;
			}
		});
		holder.ckselect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lv_parent.performItemClick(v, position, 0);
			}
		});
		
		holder.overflow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(context, holder.overflow);
				popup.getMenuInflater().inflate(R.menu.popup_menu,
						popup.getMenu());
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(context,
								"You Clicked : " + arg0.getTitle(),
								Toast.LENGTH_SHORT).show();
						switch (arg0.getItemId()) {
						case R.id.extract:
							lv_parent.performItemClick(holder.overflow,
									position * 100 + 0, 0);
							break;
						case R.id.appinfo:
							lv_parent.performItemClick(holder.overflow,
									position * 100 + 1, 0);
							break;
						case R.id.open:
							lv_parent.performItemClick(holder.overflow,
									position * 100 + 2, 0);
							break;
						case R.id.share:
							lv_parent.performItemClick(holder.overflow,
									position * 100 + 3, 0);
							break;
						}
						return true;
					}
				});popup.show();
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

	}
 
	

