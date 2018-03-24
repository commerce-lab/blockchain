package io.clab.mpf.shop.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.constant.SiteMsgReceiverStateConst;
import io.clab.mpf.shop.common.entity.SiteMsgReceiver;
import io.clab.mpf.shop.common.repository.SiteMsgReceiverMapper;
import io.clab.mpf.shop.common.service.ISiteMsgReceiverService;
import io.clab.mpf.shop.common.vto.SiteMsgVto;
import io.clab.mpf.shop.util.DateUtils;
@Service
public class SiteMsgReceiverService implements ISiteMsgReceiverService {
	@Resource
	private SiteMsgReceiverMapper siteNoticeReceiverMapper;

	@Override
	public SiteMsgReceiver get(Long id) {
		return siteNoticeReceiverMapper.selectById(id);
	}
	@Override
	public boolean delete(Long siteMsgReceiverId,Long receiverId) {
		SiteMsgReceiver siteNoticeReceiver = siteNoticeReceiverMapper.selectById(siteMsgReceiverId);
		//这条数据不属于该用户，或者早就删除了
		if(siteNoticeReceiver.getReceiverId() != receiverId || siteNoticeReceiver.getDeleted()==1){
			return false;
		}
		siteNoticeReceiver.setDeleted(1);
		siteNoticeReceiver.setDeleteTime(DateUtils.getCreateTime());
		return siteNoticeReceiverMapper.updateById(siteNoticeReceiver) == 1;
	}

	@Override
	public boolean add(List<SiteMsgReceiver> siteMsgReceiverList) {
		return false;
	}

	@Override
	public Page<SiteMsgVto> getAllReceiveList(Long receiverId, int pageNow, int pageSize) {
		Page<SiteMsgVto> page = new Page<SiteMsgVto>(pageNow,pageSize == 0?10:pageSize);
		List<SiteMsgVto> siteMsgVtoList = siteNoticeReceiverMapper.getAllReceiveList(page, receiverId);
		page.setRecords(siteMsgVtoList);
		return page;
	}

	@Override
	public Page<SiteMsgReceiver> getAllReceiveListBySender(Long receiverId, Long senderId, int pageNow, int pageSize) {
		return null;
	}

	@Override
	public Page<SiteMsgVto> getAllNotReadReceiveList(Long receiverId, int pageNow, int pageSize) {
		Page<SiteMsgVto> page = new Page<SiteMsgVto>(pageNow,pageSize == 0?10:pageSize);
		List<SiteMsgVto> siteMsgVtoList = siteNoticeReceiverMapper.getAllNotReadReceiveList(page,receiverId);
		page.setRecords(siteMsgVtoList);
		return page;
	}

	@Override
	public Long getNotReadCount(Long receiverId) {
		return siteNoticeReceiverMapper.getNotReadCount(receiverId);
	}

	@Override
	public boolean markRead(Long siteMsgReceiverId,Long receiverId) {
		SiteMsgReceiver siteNoticeReceiver = siteNoticeReceiverMapper.selectById(siteMsgReceiverId);
		//这条数据不属于该用户,或者已经删除了，或者早就读取了
		if(siteNoticeReceiver.getReceiverId() != receiverId || siteNoticeReceiver.getDeleted() == 1 || 
				SiteMsgReceiverStateConst.HAVE_READ.equals(siteNoticeReceiver.getState()) ){
			return false;
		}
		if( SiteMsgReceiverStateConst.UNREAD.equals(siteNoticeReceiver.getState()) ){
			siteNoticeReceiver.setReadTime(DateUtils.getCreateTime());
			siteNoticeReceiver.setState(SiteMsgReceiverStateConst.HAVE_READ);
			return siteNoticeReceiverMapper.updateById(siteNoticeReceiver) == 1;
		}
		return false;
	}


}
