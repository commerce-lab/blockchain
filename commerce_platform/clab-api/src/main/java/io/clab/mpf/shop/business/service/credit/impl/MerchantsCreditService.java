/**
 * 
 */
package io.clab.mpf.shop.business.service.credit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.business.entity.credit.MerchantsCredit;
import io.clab.mpf.shop.business.repository.credit.MerchantsCreditMapper;
import io.clab.mpf.shop.business.service.credit.IMerchantsCreditService;

/**
 * @author iceray
 *
 */
@Service
public class MerchantsCreditService implements IMerchantsCreditService {

	@Autowired
	private MerchantsCreditMapper merchantsCreditMapper;
	
	@Override
	public MerchantsCredit findCreditByMerchantId(Long merchantId) {
		MerchantsCredit merchantsCredit = new MerchantsCredit();
		merchantsCredit.setMerchantId(merchantId);
		return merchantsCreditMapper.selectOne(merchantsCredit);
	}

}
