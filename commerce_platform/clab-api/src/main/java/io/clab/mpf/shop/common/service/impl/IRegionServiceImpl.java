package io.clab.mpf.shop.common.service.impl;

import io.clab.mpf.shop.common.entity.Region;
import io.clab.mpf.shop.common.repository.RegionMapper;
import io.clab.mpf.shop.common.service.IRegionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class IRegionServiceImpl implements IRegionService{

	@Resource
	private RegionMapper regionMapper;

	@Override
	public List<Region> getRegionBycode(Integer regionCode) {
		return regionMapper.getRegionBycode(regionCode);
	}
}
