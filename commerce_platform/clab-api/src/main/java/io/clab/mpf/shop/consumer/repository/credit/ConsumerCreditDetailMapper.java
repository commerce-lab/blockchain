/**
 * 
 */
package io.clab.mpf.shop.consumer.repository.credit;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.consumer.entity.credit.ConsumerCreditDetail;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertLabel;

/**
 * @author iceray
 *
 */
public interface ConsumerCreditDetailMapper extends BaseMapper<ConsumerCreditDetail>{

	List<ConsumerCreditDetail> getAddCreditSource(Long userId);
	
	List<ConsumerCreditDetail> getCreditHisList(Pagination page,ConsumerCreditDetail detail);
	
	Integer relevantBusinessCount(Long userId);
	
	Long tagAssertCount(@Param("userId") Long userId,@Param("type") Integer type,@Param("tagId") Integer tagId);
	
	List<ConsumerCreditDetail> tagAssertStatics(@Param("type") Integer type,@Param("userId") Long userId,@Param("tagId") Integer tagId);
	
	List<DataAssertLabel> findAssertLableStatics(@Param("userId") Long userId,@Param("type") Integer type);
}
