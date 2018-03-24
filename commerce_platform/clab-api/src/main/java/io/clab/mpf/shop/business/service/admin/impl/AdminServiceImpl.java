package io.clab.mpf.shop.business.service.admin.impl;



import io.clab.mpf.shop.business.entity.admin.Admin;
import io.clab.mpf.shop.business.repository.admin.AdminMapper;
import io.clab.mpf.shop.business.service.admin.IAdminService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements IAdminService {

	@Resource
	private AdminMapper adminMapper;

	

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
