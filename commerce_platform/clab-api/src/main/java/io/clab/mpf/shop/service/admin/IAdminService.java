package io.clab.mpf.shop.service.admin;

import io.clab.mpf.shop.entity.admin.Admin;
import io.clab.mpf.shop.service.base.IBaseService;

public interface IAdminService extends IBaseService<Admin>{
	public Admin login(Admin admin);
	public int forgetPassword(Admin admin);
	public int getCountByAdminName(String name);
}
