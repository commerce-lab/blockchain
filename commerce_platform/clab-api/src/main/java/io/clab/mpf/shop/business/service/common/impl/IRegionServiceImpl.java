package io.clab.mpf.shop.business.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.clab.mpf.shop.business.repository.common.RegionMapper;
import io.clab.mpf.shop.business.entity.common.Region;
import io.clab.mpf.shop.business.service.common.IRegionService;

@Service
public class IRegionServiceImpl implements IRegionService{

	@Resource
	private RegionMapper regionMapper;

	@Override
	public List<Region> getRegionBycode(Integer regionCode) {
		return regionMapper.getRegionBycode(regionCode);
	}
}
