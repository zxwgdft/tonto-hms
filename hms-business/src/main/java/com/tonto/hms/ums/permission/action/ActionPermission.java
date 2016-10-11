package com.tonto.hms.ums.permission.action;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.tonto.hms.ums.permission.Permission;

/**
 * <h2>用于rest风格下请求访问时的权限</h2>
 * <ul>
 * <li>启用正则匹配，使用*进行模糊匹配</li>
 * <li>为了效率，防止每次请求判断所有权限，加入权限索引，为第一个/中内容，例如/login/*则为login，所以约定在第一个/中不能有*通配</li>
 * </ul>
 * 
 * @author xwzhou
 * @date 2014-12-24
 */
public class ActionPermission implements Permission {

	private static final Pattern pathPattern = Pattern.compile("^/.+$");

	// 用于数据库的唯一标示
	Serializable id;
	// 权限是否有效
	boolean isValid = false;
	//匹配模式
	Pattern pattern;
	

	public ActionPermission(Serializable id,String path) throws Exception {
		this.id = id;
		buildPattern(path);
	}
	
	private void buildPattern(String path) throws Exception
	{
		if(path!=null&&pathPattern.matcher(path).matches())
		{
			String regex=path.replaceAll("\\*", ".*");
			regex="^"+regex+"$";
			pattern=Pattern.compile(regex);
			return;
		}
		throw new Exception("permission path error");
	}
	
	@Override
	public boolean judge(Object object) {
		return isValid&&pattern.matcher((String) object).matches();
	}

	
	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
