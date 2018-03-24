/**
 * 
 */
package io.clab.mpf.shop.business.service.credit;

import java.util.List;

import io.clab.mpf.shop.business.entity.credit.MerchantsCreditDetail;

/**
 * @author iceray
 *
 */
public interface IMerchantsCreditDetailService{

	List<MerchantsCreditDetail> findCreditDetailsByYear(Long merchantId,String year);
}
