/**
 * 
 */
package io.clab.mpf.shop.consumer.service.tag.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.consumer.entity.tag.Tag;
import io.clab.mpf.shop.consumer.entity.tag.TagAuth;
import io.clab.mpf.shop.consumer.repository.tag.ConsumTagMapper;
import io.clab.mpf.shop.consumer.repository.tag.TagAuthMapper;
import io.clab.mpf.shop.consumer.service.tag.ITagService;

/**
 * @author iceray
 *
 */
@Service
public class TagServiceImpl implements ITagService {

	@Autowired
	private ConsumTagMapper tagMapper;
	
	@Autowired
	private TagAuthMapper tagAuthMapper;
	
	@Cacheable(value = "tags", key = "#tagCode")
	@Override	
	public Integer getTagByCode(String tagCode) {
		Tag tag = new Tag();
		tag.setTagName(tagCode);
		tag = tagMapper.selectOne(tag);
		if(tag == null || tag.getTagId() == null)
			return 0;
		return tag.getTagId();
	}

	@Override
	public boolean isGrantLabel(Long userId, Integer tagId) {
		TagAuth tagAuth = new TagAuth();
		tagAuth.setUuserId(userId);
		tagAuth.setTagId(tagId);
		//tagAuth.setUpdateTime(new Date());
		tagAuth = tagAuthMapper.selectOne(tagAuth);
		return tagAuth == null ? false : tagAuth.getAuthorizationFlag() == 0 ? false : true;
	}

	@Override
	public boolean grantLabelSwitch(Long userId, Integer tagId, boolean isGrantg) {
		TagAuth tagAuth = new TagAuth();
		tagAuth.setUuserId(userId);
		tagAuth.setTagId(tagId);
		tagAuth = tagAuthMapper.selectOne(tagAuth);
		if(tagAuth.getAuthId() == null)
			return false;
		tagAuth.setAuthorizationFlag(isGrantg ? 1 : 0);
		tagAuth.setUpdateTime(new Date());
		tagAuthMapper.updateById(tagAuth);
		return true;
	}

	@Override
	public List<String> findTagNameByUserId(Long userId) {
		return this.tagMapper.findTagNameByUserId(userId);
	}

}
