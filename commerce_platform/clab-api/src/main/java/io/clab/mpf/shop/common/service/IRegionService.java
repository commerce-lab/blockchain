package io.clab.mpf.shop.common.service;



import io.clab.mpf.shop.common.entity.Region;

import java.util.List;

public interface IRegionService {

	/**
	 * 查询地区
	 * */
	public List<Region> getRegionBycode(Integer regionCode);

}
