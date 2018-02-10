/**
 * 
 */
package io.clab.mpf.shop.consumer.service.impl;

import io.clab.mpf.shop.consumer.constant.CreditChannelEnum;
import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.entity.ConsumerCredit;
import io.clab.mpf.shop.consumer.entity.ConsumerCreditDetail;
import io.clab.mpf.shop.consumer.entity.DiUploadUser;
import io.clab.mpf.shop.consumer.entity.DiUser;
import io.clab.mpf.shop.consumer.exception.ClabServerException;
import io.clab.mpf.shop.consumer.repository.ConsumerCreditDetailMapper;
import io.clab.mpf.shop.consumer.repository.ConsumerCreditMapper;
import io.clab.mpf.shop.consumer.repository.DiUploadUserMapper;
import io.clab.mpf.shop.consumer.repository.DiUserMapper;
import io.clab.mpf.shop.consumer.service.IConsumerDataAssertService;
import io.clab.mpf.shop.consumer.vto.DataAssertHisVto;
import io.clab.mpf.shop.consumer.vto.DataAssertLabelVto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author iceray
 * 数据资产
 */
@Service
public class ConsumerDataAssertService implements IConsumerDataAssertService {

	@Autowired
	private DiUserMapper diUserMapper;
	
	@Autowired
	private DiUploadUserMapper uploadUserMapper;
	
	@Autowired
	private ConsumerCreditDetailMapper creditDetailMapper;
	
	@Autowired
	private ConsumerCreditMapper creditMapper;
	
	/**
	 * 关联商家总数
	 */
	@Override
	public int getRelevantBusinessesCount(Long consumerId) {
		return creditDetailMapper.relevantBusinessesCount(consumerId);
	}

	/**
	 * 获取总的数据资产
	 */
	@Override
	public int getGeneralIncome(Long consumerId) {
		ConsumerCredit credit = new ConsumerCredit();
		credit.setUserId(consumerId);
		credit = creditMapper.selectOne(credit);
		return credit.getAssertValue();
	}

	
	@Override
	public List<DataAssertLabelVto> getDataAssertLabel(Long consumerId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 数据资产流水
	 */
	@Override
	public Page<DataAssertHisVto> getDataAssertHis(Long consumerId,
			int pageNow, int pageSize) {
		
		return null;
	}

	/**
	 * 数据授权操作
	 */
	@Override
	@Transactional
	public void grantDataItem(Long dataItemId,Long consumerId) {
		
		//查询未授权用户数据
		DiUser diUser = diUserMapper.findNotAutUser(dataItemId);
		if(diUser == null) {
			throw new ClabServerException("未找到该用户信息或者该用户已授权");
		}
		
		//更新授权状态
		DiUploadUser uploadUser = new DiUploadUser();
		uploadUser.setUuserId(diUser.getUuserId());
		uploadUserMapper.updateById(uploadUser);
		
		//新增积分流水
		ConsumerCreditDetail creditDetail = new ConsumerCreditDetail();
		creditDetail.setCreateTime(new Date());
		creditDetail.setQuantity(diUser.getPayPoints());
		creditDetail.setSource(CreditChannelEnum.SHOP_DATA_GRANT.getSource());
		creditDetail.setType(CreditHisRangeEnum.ADD.getType());
		creditDetail.setExt(diUser.getMerchantsId().toString()); //增加商家关联
		creditDetail.setUserId(consumerId);
		creditDetailMapper.insert(creditDetail);
		
		//更新积分汇总数据
		ConsumerCredit credit = new ConsumerCredit();
		credit.setUserId(consumerId);
		credit.setAssertValue(diUser.getPayPoints());
		creditMapper.updateCreditAssert(credit);
		
	}

}
