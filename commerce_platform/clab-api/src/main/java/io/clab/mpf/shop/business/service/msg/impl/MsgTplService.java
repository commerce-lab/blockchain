package io.clab.mpf.shop.business.service.msg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.business.entity.msg.MsgTpl;
import io.clab.mpf.shop.business.repository.msg.MsgTplMapper;
import io.clab.mpf.shop.business.service.msg.IMsgTplService;
@Service
public class MsgTplService implements IMsgTplService {
	@Autowired
	private MsgTplMapper msgTplMapper;

	@Override
	public Page<MsgTpl> getList(long creator, int pageNow, int pageSize) {
		return null;
	}
	
}
