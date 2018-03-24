/**
 * 
 */
package io.clab.mpf.shop.business.repository.credit;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.business.entity.credit.MerchantsCreditDetail;

/**
 * @author iceray
 *
 */
public interface MerchantsCreditDetailMapper extends BaseMapper<MerchantsCreditDetail> {

	List<MerchantsCreditDetail> monthStatisticsByYear(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
}
