/**
 * 
 */
package io.clab.mpf.shop.consumer.repository;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.consumer.entity.ConsumerCreditDetail;

/**
 * @author iceray
 *
 */
public interface ConsumerCreditDetailMapper extends BaseMapper<ConsumerCreditDetail>{

	List<ConsumerCreditDetail> getAddCreditSource(Long userId);
	
	List<ConsumerCreditDetail> getCreditHisList(Pagination page,ConsumerCreditDetail detail);
	
	Integer relevantBusinessesCount(Long userId);
	
	
}
