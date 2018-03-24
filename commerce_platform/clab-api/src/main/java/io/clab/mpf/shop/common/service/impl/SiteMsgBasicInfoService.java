package io.clab.mpf.shop.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.entity.SiteMsgBasicInfo;
import io.clab.mpf.shop.common.repository.SiteMsgBasicInfoMapper;
import io.clab.mpf.shop.common.service.ISiteMsgBasicInfoService;
@Service
public class SiteMsgBasicInfoService implements ISiteMsgBasicInfoService {
	@Resource
	private SiteMsgBasicInfoMapper siteMsgBasicInfoMapper;
	@Override
	public boolean add(SiteMsgBasicInfo siteNoticeBasicInfo) {
		return siteMsgBasicInfoMapper.insert(siteNoticeBasicInfo) == 1;
	}

	@Override
	public Page<SiteMsgBasicInfo> getSendList(long senderId, int pageNow, int pageSize) {
		return null;
	}

}
