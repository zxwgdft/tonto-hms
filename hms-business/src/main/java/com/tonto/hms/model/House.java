package com.tonto.hms.model;

public class House {
    private Integer id;

    private String houseName;

    private String householderName;

    private String householderPhone;

    private String householderCellphone;

    private Integer loupanId;
    
    private Loupan loupan;

    public Loupan getLoupan() {
		return loupan;
	}

	public void setLoupan(Loupan loupan) {
		this.loupan = loupan;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public String getHouseholderName() {
        return householderName;
    }

    public void setHouseholderName(String householderName) {
        this.householderName = householderName == null ? null : householderName.trim();
    }

    public String getHouseholderPhone() {
        return householderPhone;
    }

    public void setHouseholderPhone(String householderPhone) {
        this.householderPhone = householderPhone == null ? null : householderPhone.trim();
    }

    public String getHouseholderCellphone() {
        return householderCellphone;
    }

    public void setHouseholderCellphone(String householderCellphone) {
        this.householderCellphone = householderCellphone == null ? null : householderCellphone.trim();
    }

    public Integer getLoupanId() {
        return loupanId;
    }

    public void setLoupanId(Integer loupanId) {
        this.loupanId = loupanId;
    }
}