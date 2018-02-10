package io.clab.mpf.shop.dao.roleModule;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.roleModule.RoleModule;

/**
 * 管理员角色模块Mapper
 */
public interface RoleModuleMapper extends IBaseDao<RoleModule> {
	/**
	 * 删除所有roleId的记录
	 * 
	 * @param roleId
	 */
	void deleteByRoleModuleId(int roleModuleId);
	/**
	 * 根据roleId查询记录
	 * @param roleId
	 */
	List<RoleModule>  selectByRoleId(int roleId);
}
