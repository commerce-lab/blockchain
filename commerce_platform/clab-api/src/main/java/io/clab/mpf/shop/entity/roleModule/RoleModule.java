package io.clab.mpf.shop.entity.roleModule;

/**
 * 管理员角色模块类
 */
public class RoleModule {
	private Integer roleModuleId;
	private Integer roleId;
	private Integer moduleId;

	public Integer getRoleModuleId() {
		return roleModuleId;
	}

	public void setRoleModuleId(Integer roleModuleId) {
		this.roleModuleId = roleModuleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

}
