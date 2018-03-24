package io.clab.mpf.shop.common.repository;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.common.entity.Region;

import java.util.List;

public interface RegionMapper extends IBusBaseMapper<Region> {

	public List<Region> getRegionBycode(Integer regionCode);
}