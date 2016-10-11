package com.tonto.hms.ums.permission.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonto.hms.dao.PermissionDao;
import com.tonto.hms.dao.RoleDao;
import com.tonto.hms.manager.Manager;
import com.tonto.hms.model.AcPermission;
import com.tonto.hms.model.AcRole;
import com.tonto.hms.ums.permission.Role;


@Component
public class PermissionManager implements Manager{

	private static Logger logger = Logger.getLogger(PermissionManager.class);
	
	@Autowired
	PermissionDao permissionDao;
	@Autowired
	RoleDao roleDao;

	Map<Integer, Role> roleMap;
	Map<Integer, ActionPermission> actionPermissionMap;

	@Override
	public boolean init() {
		logger.info("--------------开始载入权限与角色数据------------");

		roleMap = new HashMap<Integer, Role>();
		actionPermissionMap = new HashMap<Integer, ActionPermission>();

		List<AcPermission> acPermissions = permissionDao.findAll();
		// 封装所有权限
		for (AcPermission acPermission : acPermissions) {
			try {
				Integer permissionId = acPermission.getId();
				logger.info("创建权限：" + acPermission.getPermissionName()
						+ ",权限ID：" + permissionId);
				ActionPermission actionPermission = convert2ActionPermission(acPermission);
				actionPermissionMap.put(permissionId, actionPermission);
			} catch (Exception e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
			}
		}

		List<AcRole> acRoles = roleDao.findAll();

		// 封装所有角色
		for (AcRole acRole : acRoles) {
			Integer roleId = acRole.getId();

			Role role = new Role();
			role.setRoleId(roleId);
			role.setValid(acRole.isValid());

			roleMap.put(roleId, role);

			// 权限ID拼接的字符串，以逗号分隔
			String permissionIds = acRole.getPermissions();
			if (permissionIds != null && !"".equals(permissionIds)) {
				try {
					String[] idsStr = permissionIds.split(",");
					for (String idStr : idsStr) {
						Integer id = Integer.valueOf(idStr);
						ActionPermission actionPermission = actionPermissionMap
								.get(id);
						if (actionPermission == null) {
							logger.warn("无法为角色设置权限，权限不存在！权限ID：" + idStr);
						} else {
							role.addActionPermission(actionPermission);
						}
					}
				} catch (Exception e) {
					logger.warn("角色分配权限错误！角色ID：" + roleId);
					logger.warn(e.getMessage());

				}
			}
			logger.info("创建角色：" + acRole.getRoleName() + ",角色ID：" + roleId);
		}

		logger.info("--------------载入权限与角色数据结束------------");
		
		return true;
	}
	
	@Override
	public boolean afterInit() {
		return true;
	}

	// 解析转化为ActionPermission
	private ActionPermission convert2ActionPermission(AcPermission acPermission)
			throws Exception {
		Integer pid = acPermission.getId();
		String url = acPermission.getDefinition();
		boolean isValid = acPermission.isValid();


		ActionPermission actionPermission = new ActionPermission(pid,url);
		actionPermission.setValid(isValid);

		return actionPermission;
	}

	private Object roleLock = new Object();

	public void addRole(AcRole acRole) {
		synchronized (roleLock) {
			Integer roleId = acRole.getId();

			Role role = new Role();
			role.setRoleId(roleId);

			roleMap.put(roleId, role);
			logger.info("创建角色：" + acRole.getRoleName() + ",角色ID：" + roleId);
		}
	}

	public void deletRole(AcRole acRole) {
		synchronized (roleLock) {
			Integer roleId = acRole.getId();
			Role role = roleMap.get(roleId);

			if (role != null) {
				role.setValid(false);
				logger.info("删除角色：" + acRole.getRoleName() + ",角色ID：" + roleId);
			}
		}
	}

	public void setPermissionOfRole(AcRole acRole,
			List<Integer> permissionIdList) {
		synchronized (roleLock) {
			Integer roleId = acRole.getId();
			Role role = roleMap.get(roleId);

			if (permissionIdList != null) {
				List<ActionPermission> permissionList = new ArrayList<ActionPermission>(
						permissionIdList.size());

				for (Integer pid : permissionIdList) {
					ActionPermission actionPermission = actionPermissionMap
							.get(pid);
					permissionList.add(actionPermission);
				}

				role.setActionPermission(permissionList);
			} else {
				role.setActionPermission(null);
			}
		}
	}

	public Role getRole(Integer roleId) {
		return roleMap.get(roleId);
	}

	private Object permissionLock = new Object();

	public void updatePermission(AcPermission acPermission) throws Exception {

		synchronized (permissionLock) {
			Integer permissionId = acPermission.getId();
			ActionPermission permission = actionPermissionMap.get(permissionId);
			if (permission != null) {
				ActionPermission newPermission = convert2ActionPermission(acPermission);
				permission.setPattern(newPermission.getPattern());
				permission.setValid(newPermission.isValid());
			} else {
				throw new Exception("权限不存在！权限ID：" + permissionId);
			}
		}
	}

	public void deletePermission(AcPermission acPermission) throws Exception {
		synchronized (permissionLock) {
			Integer permissionId = acPermission.getId();
			ActionPermission permission = actionPermissionMap.get(permissionId);
			if (permission != null) {
				permission.setValid(false);
			} else {
				throw new Exception("权限不存在！权限ID：" + permissionId);
			}
		}
	}

	public synchronized void addPermission(AcPermission acPermission)
			throws Exception {
		Integer permissionId = acPermission.getId();
		ActionPermission actionPermission = actionPermissionMap
				.get(permissionId);

		if (actionPermission == null) {
			logger.info("创建权限：" + acPermission.getPermissionName() + ",权限ID："
					+ permissionId);
			actionPermission = convert2ActionPermission(acPermission);
			actionPermissionMap.put(permissionId, actionPermission);
		} else {
			throw new Exception("该权限已经存在！权限ID：" + permissionId);
		}
	}

	

}
