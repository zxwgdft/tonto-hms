package com.tonto.hms.model;

import com.tonto.hms.util.DistrictUtil;

public class HouseDetail extends House{
	private String loupanName;
	private Integer districtCode;
	 
	public String getLoupanName() {
		return loupanName;
	}
	public void setLoupanName(String loupanName) {
		this.loupanName = loupanName;
	}
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	 
	public String getFullDistrictName(){
		return DistrictUtil.getFullDistrictName(districtCode);
	}
	
	public String getDistrictName(){
		return DistrictUtil.getDistrictName(districtCode);
	}
	
	public Integer getDistrictLevel(){
		if(districtCode==null)
			return null;
		Integer level= DistrictUtil.getDistrictLevel(districtCode);
		return level==null?null:level;
	}
}
