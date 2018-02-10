package io.clab.mpf.shop.business.service.common;

import io.clab.mpf.shop.business.entity.common.Region;

import java.util.List;

public interface IRegionService {

	/**
	 * 查询地区
	 * */
	public List<Region> getRegionBycode(Integer regionCode);

}
