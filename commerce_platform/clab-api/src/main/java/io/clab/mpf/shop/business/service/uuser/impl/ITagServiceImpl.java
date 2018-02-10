package io.clab.mpf.shop.business.service.uuser.impl;

import java.util.List;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.uuser.TagMapper;
import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.uuser.ITagService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

@Service
public class ITagServiceImpl extends BaseServiceImpl<Tag> implements ITagService{

	@Resource
	private TagMapper tagMapper;

	@Override
	protected IBusBaseMapper<Tag> getMapper() {
		return tagMapper;
	}

	public Tag selectByTagName(String tagName){
		return tagMapper.selectByTagName(tagName);
	}
	
	public Page<Tag> selectLikeTagNameList(int pageNow, int pageSize,String tagName){
		Page<Tag> page = new Page<Tag>(pageNow,pageSize == 0?10:pageSize);
		
		page.setRecords(tagMapper.selectLikeTagNameList(page, tagName));
		return page;
	}
	
	public Page<Tag> selectByCategoryList(int pageNow, int pageSize,Integer categoryId){
		Page<Tag> page = new Page<Tag>(pageNow,pageSize == 0?10:pageSize);
		page.setRecords(tagMapper.selectByCategoryList(page, categoryId));
		return page;
	}
}
