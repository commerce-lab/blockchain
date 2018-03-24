/**
 * 
 */
package io.clab.mpf.shop.consumer.repository.credit;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.consumer.entity.credit.DataAssertDetail;

/**
 * @author iceray
 *
 */
public interface DataAssertMapper extends BaseMapper<DataAssertDetail> {

	List<DataAssertDetail> getDataAssertList(Pagination page,Long userId);
	
	DataAssertDetail getDataAssertDetail(Long dataId);
}
