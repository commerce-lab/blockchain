/**
 * 
 */
package io.clab.mpf.shop.consumer.service.tag;

import java.util.List;

/**
 * @author iceray
 *
 */
public interface ITagService {

	
	Integer getTagByCode(String tagCode);
	
	boolean isGrantLabel(Long userId, Integer tagId);
	
	boolean grantLabelSwitch(Long userId, Integer tagId, boolean isGrantg);
	
	List<String> findTagNameByUserId(Long userId);
}
