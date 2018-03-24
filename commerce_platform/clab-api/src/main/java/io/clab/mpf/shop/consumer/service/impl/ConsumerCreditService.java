/**
 * 
 */
package io.clab.mpf.shop.consumer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.constant.CreditChannelEnum;
import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.entity.credit.ConsumerCredit;
import io.clab.mpf.shop.consumer.entity.credit.ConsumerCreditDetail;
import io.clab.mpf.shop.consumer.repository.credit.ConsumerCreditDetailMapper;
import io.clab.mpf.shop.consumer.repository.credit.ConsumerCreditMapper;
import io.clab.mpf.shop.consumer.service.IConsumerCreditService;
import io.clab.mpf.shop.consumer.vto.credit.AddCreditSourceVto;
import io.clab.mpf.shop.consumer.vto.credit.ConsumerCreditInfoVto;
import io.clab.mpf.shop.consumer.vto.credit.CreditHisVto;
import io.clab.mpf.shop.util.PageResult;

/**
 * @author iceray
 *
 */
@Component
public class ConsumerCreditService implements IConsumerCreditService {

	@Autowired
	private ConsumerCreditMapper creditMapper;
	
	@Autowired
	private ConsumerCreditDetailMapper creditDetailMapper;
	
	@Override
	public ConsumerCreditInfoVto getCreditInfo(long userId) {
		ConsumerCredit credit = new ConsumerCredit();
		credit.setUserId(userId);
		credit = creditMapper.selectOne(credit);
		ConsumerCreditInfoVto creditVto = new ConsumerCreditInfoVto();
		if(credit == null)
			return creditVto;
		BeanUtils.copyProperties(credit, creditVto);
		return creditVto;
	}

	
	@Override
	public Page<CreditHisVto> getCreditHisList(long userId,
			CreditHisRangeEnum creditHisRangeEnum, int pageNow, int pageSize) {
		Page<ConsumerCreditDetail> page = new Page<ConsumerCreditDetail>(pageNow,pageSize == 0?10:pageSize);
		
		ConsumerCreditDetail detail = new ConsumerCreditDetail();
		detail.setType(creditHisRangeEnum.getType());
		detail.setUserId(userId);
		page.setRecords(creditDetailMapper.getCreditHisList(page,detail));
		return PageResult.toPage(page,new Page<CreditHisVto>(),creditDetails -> convertToVto(creditDetails));
	}
	
	private List<CreditHisVto> convertToVto(List<ConsumerCreditDetail> creditDetails) {
		List<CreditHisVto> detailVtos = new ArrayList<CreditHisVto>();
		if(creditDetails == null || creditDetails.isEmpty())
			return detailVtos;
		
		creditDetails.stream().forEach(detail -> {
			CreditHisVto detailVto = new CreditHisVto();
			BeanUtils.copyProperties(detail, detailVto);
			detailVtos.add(detailVto);
		});
		
		return detailVtos;
	}

	
	@Override
	public AddCreditSourceVto getAddCreditSource(long userId) {
		List<ConsumerCreditDetail> sourceCounts = creditDetailMapper.getAddCreditSource(userId);
		AddCreditSourceVto addCreditSource = new AddCreditSourceVto();
		for(ConsumerCreditDetail creditDetail : sourceCounts) {
			if(creditDetail.getSource() == CreditChannelEnum.SHOP_DATA_GRANT.getSource())
				addCreditSource.setGrantData(creditDetail.getSourceSum());
			if(creditDetail.getSource() == CreditChannelEnum.SHOP_PRESENTED.getSource())
				addCreditSource.setShopPresent(creditDetail.getSourceSum());
			if(creditDetail.getSource() == CreditChannelEnum.RETURN.getSource())
				addCreditSource.setConsumptionReurn(creditDetail.getSourceSum());
			if(creditDetail.getSource() == CreditChannelEnum.DAILYTASK.getSource())
				addCreditSource.setDailyTask(creditDetail.getSourceSum());
		}
		return addCreditSource;
	}

}
