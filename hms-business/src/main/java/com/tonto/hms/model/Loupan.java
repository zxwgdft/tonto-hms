package com.tonto.hms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tonto.hms.service.district.DistrictManager;

@JsonIgnoreProperties(value={"district","district1","district2","district3","district4"})
public class Loupan {
    private Integer id;

    private Integer districtCode;

    private Integer district1;

    private Integer district2;

    private Integer district3;

    private Integer district4;

    private String street;

    private String loupanName;
    
    
    public District getDistrict()
    {
    	if(districtCode!=null)
    	{
    		return DistrictManager.getDistrict(districtCode);
    	}
    	return null;
    }
    
   
    public String getFullDistrict()
    {
    	String fullDistrict="";
    	if(district1!=null)
    	{
    		fullDistrict+=DistrictManager.getDistrict(district1).getDistrictName();
    		if(district2!=null)
        	{
        		fullDistrict+=DistrictManager.getDistrict(district2).getDistrictName();
        		if(district3!=null)
            	{
            		fullDistrict+=DistrictManager.getDistrict(district3).getDistrictName();
            		if(district4!=null)
                	{
                		fullDistrict+=DistrictManager.getDistrict(district4).getDistrictName();
                		
                	}
            	}
        	}
    	}
    	if("".equals(fullDistrict))
    	{
    		fullDistrict=DistrictManager.getDistrict(districtCode).getDistrictName();
    	}
    	return fullDistrict;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getDistrict1() {
        return district1;
    }

    public void setDistrict1(Integer district1) {
        this.district1 = district1;
    }

    public Integer getDistrict2() {
        return district2;
    }

    public void setDistrict2(Integer district2) {
        this.district2 = district2;
    }

    public Integer getDistrict3() {
        return district3;
    }

    public void setDistrict3(Integer district3) {
        this.district3 = district3;
    }

    public Integer getDistrict4() {
        return district4;
    }

    public void setDistrict4(Integer district4) {
        this.district4 = district4;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getLoupanName() {
        return loupanName;
    }

    public void setLoupanName(String loupanName) {
        this.loupanName = loupanName == null ? null : loupanName.trim();
    }
}