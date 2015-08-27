package com.tonto.hms.service;

import java.io.Serializable;

public class UserSession implements Serializable{

	private static final long serialVersionUID = -1399789053380243252L;
	
	private String userName;
	private String nickName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	private static ThreadLocal<UserSession> sessions=new ThreadLocal<UserSession>();
	
	public static void setLocalUserSession(UserSession session){
		sessions.set(session);
	}
	
	public static UserSession getLocalUserSession(){
		return sessions.get();
	}
}
