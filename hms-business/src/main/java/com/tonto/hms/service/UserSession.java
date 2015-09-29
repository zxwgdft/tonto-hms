package com.tonto.hms.service;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;

import com.tonto.hms.ums.permission.Role;

public class UserSession implements Serializable{

	private static final long serialVersionUID = -1399789053380243252L;
	
	private String userName;
	private String nickName;	
	private Role role;
	
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public static UserSession getUserSession(){
		return (UserSession) SecurityUtils.getSubject().getPrincipal();
	}
}
