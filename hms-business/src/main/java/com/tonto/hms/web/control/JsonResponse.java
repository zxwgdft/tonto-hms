package com.tonto.hms.web.control;

public class JsonResponse<T> {
	
	
	private int status;
	private String msg;
	private T result;
	
	public JsonResponse()
	{
	}
	
	public JsonResponse(int status)
	{
		this.status=status;
	}
	
	public JsonResponse(int status,T result,String msg)
	{
		this.status=status;
		this.result=result;
		this.msg=msg;
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
	
	
	public static JsonResponse<?> getSuccessResponse()
	{
		return new JsonResponse<Object>(Response.STATUS_SUCCESS);
	}
	public static JsonResponse<?> getSuccessResponse(String msg)
	{
		return new JsonResponse<Object>(Response.STATUS_SUCCESS,null,msg);
	}
	public static JsonResponse<?> getUnLoginResponse()
	{
		return new JsonResponse<Object>(Response.STATUS_NO_LOGIN);
	}
	public static JsonResponse<?> getUnLoginResponse(String msg)
	{
		return new JsonResponse<Object>(Response.STATUS_NO_LOGIN,null,msg);
	}
	public static JsonResponse<?> getErrorResponse()
	{
		return new JsonResponse<Object>(Response.STATUS_ERROR);
	}
	public static JsonResponse<?> getErrorResponse(String msg)
	{
		return new JsonResponse<Object>(Response.STATUS_ERROR,null,msg);
	}
	

	
}
