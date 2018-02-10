package io.clab.mpf.shop.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.constant.SmsBasicInfoStateConstant;
import io.clab.mpf.shop.common.entity.SmsBasicInfo;
import io.clab.mpf.shop.common.repository.SmsBasicInfoMapper;
import io.clab.mpf.shop.common.service.ISmsBasicInfoService;
import io.clab.mpf.shop.util.DateUtils;
@Service
public class SmsBasicInfoService implements ISmsBasicInfoService {
	@Resource
	private SmsBasicInfoMapper  smsBasicInfoMapper;
	@Override
	public Page<Map> getAllMsgByReceiver(long receiverId) {
		return null;
	}

	@Override
	public boolean add(SmsBasicInfo smsBasicInfo) {
		return smsBasicInfoMapper.insert(smsBasicInfo) ==1;
	}

	@Override
	public void send(Long smsBasicInfoId) {
		//更新开始发送状态
		SmsBasicInfo smsBasicInfo = smsBasicInfoMapper.selectById(smsBasicInfoId);
		smsBasicInfo.setBeginSendTime(DateUtils.getCreateTime());
		smsBasicInfo.setState(SmsBasicInfoStateConstant.SENDING);
		smsBasicInfoMapper.updateById(smsBasicInfo);
		//执行发送
		
		
		smsBasicInfo.setEndSendTime(DateUtils.getCreateTime());
		smsBasicInfo.setState(SmsBasicInfoStateConstant.END_SEND);
		smsBasicInfoMapper.updateById(smsBasicInfo);
		
		
	}

}
