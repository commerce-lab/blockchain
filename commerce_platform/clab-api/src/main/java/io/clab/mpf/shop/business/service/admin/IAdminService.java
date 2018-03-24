package io.clab.mpf.shop.business.service.admin;

import io.clab.mpf.shop.business.entity.admin.Admin;

public interface IAdminService {
	public Admin login(Admin admin);
	public int forgetPassword(Admin admin);
	public int getCountByAdminName(String name);
}
