package com.tonto.hms.ums.permission;

public interface Permission {

	/**
	 * 判断对象是否有权限
	 * 
	 * @param objcet
	 * @return
	 */
	boolean judge(Object object);

}
