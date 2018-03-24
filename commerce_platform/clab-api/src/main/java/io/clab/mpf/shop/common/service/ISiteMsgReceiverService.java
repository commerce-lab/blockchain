package io.clab.mpf.shop.common.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.entity.SiteMsgReceiver;
import io.clab.mpf.shop.common.vto.SiteMsgVto;

public interface ISiteMsgReceiverService {
	public SiteMsgReceiver get(Long id);
	/***
	 * 逻辑删除一条站内通知
	 * **/ 
	boolean delete(Long siteMsgReceiverId,Long receiverId);
	 
	/**
	 * 批量添加站内通知
	 * 先写入数据到SiteNoticeBasicInfo，再写数据到SiteNoticeReceiver
	 * ***/
	boolean add(List<SiteMsgReceiver> siteMsgReceiverList);
	/***
	 * 收件人获取所有他接收到的站内通知列表
	 * **/
	public Page<SiteMsgVto> getAllReceiveList(Long receiverId,int pageNow,int pageSize);
	
	/***
	 * 收件人获取指定发件人的站内通知列表
	 * **/
	public Page<SiteMsgReceiver> getAllReceiveListBySender(Long receiverId,Long senderId,int pageNow,int pageSize);

	/***
	 * 收件人获取所有未读的站内通知列表
	 * **/
	public Page<SiteMsgVto> getAllNotReadReceiveList(Long receiverId,int pageNow,int pageSize);
	
	public Long getNotReadCount(Long receiverId);
	/***
	 * 消息置为已读
	 * **/
	public boolean markRead(Long siteMsgReceiverId,Long receiverId);
}
