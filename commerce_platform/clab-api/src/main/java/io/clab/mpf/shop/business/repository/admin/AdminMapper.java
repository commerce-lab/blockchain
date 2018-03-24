package io.clab.mpf.shop.business.repository.admin;

import io.clab.mpf.shop.business.entity.admin.Admin;
import io.clab.mpf.shop.business.repository.base.IBaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends IBaseDao<Admin> {
	public Admin login(Admin admin);
	public int forgetPassword(Admin admin);
	public int getCountByAdminName(String name);
	//测试导出数据到excel
	public List<Admin> getAdminByCondition(@Param("state")Byte state);
}