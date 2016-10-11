package com.tonto.hms.ums.permission;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.tonto.hms.ums.permission.action.ActionPermission;


/**
 * 角色
 * @author xwzhou
 * @date 2014-12-24
 */
public class Role {
	Serializable roleId;
	boolean isValid;

	// ActionPermission列表
	List<ActionPermission> actionPermissionList = new CopyOnWriteArrayList<ActionPermission>();
	Map<Serializable, ActionPermission> actionPermissionMap = new HashMap<Serializable, ActionPermission>();

	// 用于同步添加删除权限的锁
	private Object lock = new Object();

	public void addActionPermission(ActionPermission permission) {
		if (permission == null)
			return;
		synchronized (lock) {
			Serializable id = permission.getId();
			Permission p = actionPermissionMap.get(id);
			if (p != null)
				actionPermissionList.remove(p);
			actionPermissionList.add(permission);
			actionPermissionMap.put(id, permission);
		}
	}

	public void removeActionPermission(ActionPermission permission) {
		if (permission == null)
			return;
		synchronized (lock) {
			Serializable id = permission.getId();
			Permission p = actionPermissionMap.get(id);
			if (p != null) {
				actionPermissionList.remove(p);
				actionPermissionMap.put(id, null);
			}
		}
	}

	public void setActionPermission(List<ActionPermission> permissionList) {
		synchronized (lock) {
			actionPermissionList = new CopyOnWriteArrayList<ActionPermission>();
			actionPermissionMap = new HashMap<Serializable, ActionPermission>();
			if (permissionList != null) {
				for (ActionPermission permission : permissionList) {
					addActionPermission(permission);
				}
			}
		}
	}

	/**
	 * 判断角色是否拥有该权限
	 * 
	 * @param permission
	 * @return
	 */
	public boolean hasActionPermisson(Object object) {
		if (isValid) {
			for (Permission p : actionPermissionList) {
				if (p.judge(object))
					return true;
			}
		}

		return false;
	}

	public Serializable getRoleId() {
		return roleId;
	}

	public void setRoleId(Serializable roleId) {
		this.roleId = roleId;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
