package io.clab.mpf.shop.service.admin.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.dao.admin.AdminMapper;
import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.admin.Admin;
import io.clab.mpf.shop.service.admin.IAdminService;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements IAdminService {

	@Resource
	private AdminMapper adminMapper;

	@Override
	protected IBaseDao<Admin> getMapper() {
		return adminMapper;
	}

	@Override
	public Admin login(Admin user) {
		return adminMapper.login(user);
	}

	@Override
	public int forgetPassword(Admin user) {
		return adminMapper.forgetPassword(user);
	}

	@Override
	public int getCountByAdminName(String name) {
		return adminMapper.getCountByAdminName(name);
	}
	
}
