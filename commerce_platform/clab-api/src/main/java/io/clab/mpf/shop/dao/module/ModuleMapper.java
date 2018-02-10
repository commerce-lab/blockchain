package io.clab.mpf.shop.dao.module;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.module.Module;
/**
 * 系统模块Mapper

 */
public interface ModuleMapper extends IBaseDao<Module> {
	/**
	 * 查询该角色拥有的系统模块
	 */
	List<Module> selectByRoleId(int roleId);

	List<Module> queryModulesByAdminId(int adminId);
}
