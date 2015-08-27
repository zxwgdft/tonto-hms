package com.tonto.hms.util;

import com.tonto.hms.model.District;
import com.tonto.hms.service.district.DistrictManager;

public class DistrictUtil {
	
	public static String getFullDistrictName(Integer code)
	{
		String name="";
		TreeNode<District, Integer> node=DistrictManager.getDistrictNode(code);
		while(node!=null)
		{
			name=node.getValue().getDistrictName()+name;
			node=node.getParent();
		}
		return name;
		
	}
	
	public static String getDistrictName(Integer code)
	{
		District d= DistrictManager.getDistrict(code);
		if(d==null)
			return "";
		return d.getDistrictName();
	}
	
	public static Integer getDistrictLevel(Integer code) {
		
		TreeNode<District, Integer> node=DistrictManager.getDistrictNode(code);
		if(node==null)
			return 0;
		Integer level=1;
		while(node!=null)
		{
			node=node.getParent();
			level++;
		}
		return level;
	}
}
