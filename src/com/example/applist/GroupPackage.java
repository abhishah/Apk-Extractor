package com.example.applist;

import java.util.List;

import android.content.pm.PackageInfo;

public class GroupPackage {
	List <PackageInfo> packagelist;
	List <Boolean> selected;
	
  public GroupPackage(List <PackageInfo> packageList,List <Boolean> selection){
	  packagelist=packageList;
	  selected=selection;
  }
  public void setBackground(int position,Boolean what){
	  selected.set(position, what);
  }
  public Boolean getBackground(int position){
	  return selected.get(position);
  }
}
