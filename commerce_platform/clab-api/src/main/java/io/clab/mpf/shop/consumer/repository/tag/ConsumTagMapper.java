/**
 * 
 */
package io.clab.mpf.shop.consumer.repository.tag;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.clab.mpf.shop.consumer.entity.tag.Tag;

/**
 * @author iceray
 *
 */
public interface ConsumTagMapper extends BaseMapper<Tag> {

	List<String> findTagNameByUserId(Long userId);
}
