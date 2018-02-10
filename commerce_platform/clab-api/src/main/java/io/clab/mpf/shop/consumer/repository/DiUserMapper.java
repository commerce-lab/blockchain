/**
 * 
 */
package io.clab.mpf.shop.consumer.repository;

import io.clab.mpf.shop.consumer.entity.DiUser;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author iceray
 *
 */
public interface DiUserMapper extends BaseMapper<DiUser> {

	/**
	 * 获取未授权用户信息
	 * @param dataItem
	 * @return
	 */
	DiUser findNotAutUser(Long dataItem);
	
}
