package io.clab.mpf.shop.service.role;

import java.util.List;

import io.clab.mpf.shop.entity.role.Role;
import io.clab.mpf.shop.entity.roleModule.RoleModule;
import io.clab.mpf.shop.service.base.IBaseService;

/**
 * 管理员角色服务接口定义
 */
public interface IRoleService extends IBaseService<Role> {

	/**
	 * 授权
	 * 
	 * @param role
	 *            角色对象
	 * @param moduleList
	 *            模块列表
	 */
	void grantPowerToRole(Role role, List<RoleModule> moduleList) throws Exception;
}
