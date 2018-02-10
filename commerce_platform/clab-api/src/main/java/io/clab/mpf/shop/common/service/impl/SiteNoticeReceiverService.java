package io.clab.mpf.shop.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.entity.SiteMsgReceiver;
import io.clab.mpf.shop.common.repository.SiteNoticeReceiverMapper;
import io.clab.mpf.shop.common.service.ISiteNoticeReceiverService;
import io.clab.mpf.shop.util.DateUtils;
@Service
public class SiteNoticeReceiverService implements ISiteNoticeReceiverService {
	@Resource
	private SiteNoticeReceiverMapper siteNoticeReceiverMapper;
	@Override
	public boolean delete(Long siteNoticeBasicInfoId,Long receiverId) {
		SiteMsgReceiver entity = new SiteMsgReceiver();
		entity.setSiteNoticeBasicInfoId(siteNoticeBasicInfoId);
		entity.setReceiverId(receiverId);
		entity = siteNoticeReceiverMapper.selectOne(entity );
		entity.setDeleted(1);
		entity.setDeleteTime(DateUtils.getCreateTime());
		return siteNoticeReceiverMapper.updateById(entity) == 1;
	}

	@Override
	public boolean add(List<SiteMsgReceiver> siteMsgReceiverList) {
		return false;
	}

	@Override
	public Page<SiteMsgReceiver> getAllReceiveList(Long receiverId, int pageNow, int pageSize) {
		return null;
	}

	@Override
	public Page<SiteMsgReceiver> getAllReceiveListBySender(Long receiverId, Long senderId, int pageNow, int pageSize) {
		return null;
	}

	@Override
	public Page<SiteMsgReceiver> getAllNotReadReceiveList(Long receiverId, Long senderId, int pageNow, int pageSize) {
		return null;
	}

}
