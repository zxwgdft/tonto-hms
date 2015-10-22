package com.tonto.hms.model;

public abstract class BaseModel {
	
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	private static final int STATUS_VALID = 1;
	private static final int STATUS_INVALID = 0;

	public boolean isValid() {
		return status == STATUS_VALID;
	}

	public void setValid(boolean isValid) {
		status=(isValid ? STATUS_VALID : STATUS_INVALID);
	}

}
