package io.clab.mpf.shop.common.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.entity.SmsBasicInfo;

public interface ISmsBasicInfoService {
	/**
	 * 获取用户收到的短信
	 * **/
	public Page<Map> getAllMsgByReceiver(long receiverId);
	
	public boolean add(SmsBasicInfo smsBasicInfo);
	/**
	 * 执行发送短信操作
	 * 
	 * ***/
	public void send(Long smsBasicInfoId );
}
