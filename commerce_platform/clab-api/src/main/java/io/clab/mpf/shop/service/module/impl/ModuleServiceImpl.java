package io.clab.mpf.shop.service.module.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.dao.module.ModuleMapper;
import io.clab.mpf.shop.entity.module.Module;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.service.module.IModuleService;

/**
 * 系统模块服务接口实现
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements IModuleService {
	@Resource
	private ModuleMapper moduleMapper;

	@Override
	protected IBaseDao<Module> getMapper() {
		return this.moduleMapper;
	}

	@Override
	public List<Module> queryAllValidModule() {
		Module module = new Module();
		module.setState((byte) 1);
		return moduleMapper.getAllBySelect(module);
	}

	@Override
	public List<Module> queryRoleModule(int roleId) {
		return moduleMapper.selectByRoleId(roleId);

	}

	@Override
	public List<Module> queryModulesByAdminId(int adminId) {
		return moduleMapper.queryModulesByAdminId(adminId);
	}

}
