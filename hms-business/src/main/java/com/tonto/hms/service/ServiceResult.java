package com.tonto.hms.service;

public class ServiceResult {
	
	public ServiceResult(boolean successful,String msg)
	{
		this.successful=successful;
		if(successful)
			successMsg=msg;
		else
			errorMsg=msg;
	}
	
	private boolean successful;
	private String errorMsg;
	private String successMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
}
