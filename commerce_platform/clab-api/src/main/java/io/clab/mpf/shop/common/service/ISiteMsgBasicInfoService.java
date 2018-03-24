package io.clab.mpf.shop.common.service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.entity.SiteMsgBasicInfo;

/**
 * 站内消息操作
 * **/
public interface ISiteMsgBasicInfoService {
	
	public boolean add(SiteMsgBasicInfo siteNoticeBasicInfo);
	
	/**
	 * 获取发送列表
	 * ***/
	public Page<SiteMsgBasicInfo> getSendList(long senderId,int pageNow,int pageSize);
	
}
