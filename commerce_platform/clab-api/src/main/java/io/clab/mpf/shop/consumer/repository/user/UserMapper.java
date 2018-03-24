/**
 * 
 */
package io.clab.mpf.shop.consumer.repository.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.consumer.entity.user.User;

/**
 * @author iceray
 *
 */
public interface UserMapper extends BaseMapper<User> {

	Long selectUserIdByConsumerId(Long consumerId);
	
}
