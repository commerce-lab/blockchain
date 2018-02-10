package io.clab.mpf.shop.dao.logistics;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.logistics.Logistics;

public interface LogisticsMapper extends IBaseDao<Logistics> {
	//搜索物流列表
	public List<Logistics> getListLogistics();
	public Logistics getFirstLogistics();
}