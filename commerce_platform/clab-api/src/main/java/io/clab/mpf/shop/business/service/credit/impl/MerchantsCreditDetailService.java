/**
 * 
 */
package io.clab.mpf.shop.business.service.credit.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import io.clab.mpf.shop.business.entity.credit.MerchantsCreditDetail;
import io.clab.mpf.shop.business.repository.credit.MerchantsCreditDetailMapper;
import io.clab.mpf.shop.business.service.credit.IMerchantsCreditDetailService;
import io.clab.mpf.shop.util.DateUtils;

/**
 * @author iceray
 *
 */
@Service
public class MerchantsCreditDetailService implements IMerchantsCreditDetailService {

	private static Logger logger = Logger.getLogger(MerchantsCreditDetailService.class);
	
	@Autowired
	private MerchantsCreditDetailMapper merchantsCreditDetialMapper;
	
	@Override
	public List<MerchantsCreditDetail> findCreditDetailsByYear(Long merchantId, String year) {
		
		List<MerchantsCreditDetail> details = Lists.newArrayList();
		
		if(!NumberUtils.isCreatable(year)) {
			logger.info("=====年份不是一个有效的数字=====");
			return details;
		}
		Date beginTime = DateUtils.getBeginTimeByYear(year);
		Date endTime = DateUtils.getBeginTimeByYear(Integer.toString(NumberUtils.toInt(year) + 1));
		details = merchantsCreditDetialMapper.monthStatisticsByYear(beginTime, endTime);
		return details;
	}

}
