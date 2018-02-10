package io.clab.mpf.shop.business.service.uuser.impl;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.uuser.DiTagMapper;
import io.clab.mpf.shop.business.entity.uuser.DiTag;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.uuser.IDiTagService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class IDiTagServiceImpl extends BaseServiceImpl<DiTag> implements IDiTagService{

	@Resource
	private DiTagMapper diTagMapper;

	@Override
	protected IBusBaseMapper<DiTag> getMapper() {
		return diTagMapper;
	}


}
