/**
 * 
 */
package io.clab.mpf.shop.consumer.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.consumer.entity.ConsumerCredit;

/**
 * @author iceray
 *
 */
public interface ConsumerCreditMapper extends BaseMapper<ConsumerCredit> {

	void updateCreditAssert(ConsumerCredit credit);
}
