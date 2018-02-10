package io.clab.mpf.shop.business.service.uuser.impl;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.uuser.DiUuserMapper;
import io.clab.mpf.shop.business.entity.uuser.DiUuser;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.uuser.IDiUuserService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class IDiUuserServiceImpl extends BaseServiceImpl<DiUuser> implements IDiUuserService{

	@Resource
	private DiUuserMapper diUuserMapper;

	@Override
	protected IBusBaseMapper<DiUuser> getMapper() {
		return diUuserMapper;
	}


}
