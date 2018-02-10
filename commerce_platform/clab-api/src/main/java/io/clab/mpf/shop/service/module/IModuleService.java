package io.clab.mpf.shop.service.module;

import java.util.List;

import io.clab.mpf.shop.entity.module.Module;
import io.clab.mpf.shop.service.base.IBaseService;

/**
 * 系统模块服务接口定义
 */
public interface IModuleService extends IBaseService<Module> {
	/**
	 * 查询所有有效的系统模块
	 * 
	 * @return
	 */
	List<Module> queryAllValidModule();
	/**
	 * 查询该角色拥有的有效系统模块
	 */
	List<Module> queryRoleModule(int roleId);
	List<Module> queryModulesByAdminId(int adminId);
}
