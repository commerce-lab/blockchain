/**
 * 
 */
package io.clab.mpf.shop.business.service.credit;

import io.clab.mpf.shop.business.entity.credit.MerchantsCredit;

/**
 * @author iceray
 *
 */
public interface IMerchantsCreditService {

	MerchantsCredit findCreditByMerchantId(Long merchantId);
	
}
