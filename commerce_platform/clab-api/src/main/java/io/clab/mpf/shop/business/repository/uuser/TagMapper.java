package io.clab.mpf.shop.business.repository.uuser;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.entity.uuser.Tag;

public interface TagMapper extends IBusBaseMapper<Tag>, BaseMapper<Tag> {

	public Tag selectByTagName(String tagName);
	
	public List<Tag> selectLikeTagNameList(Pagination page,String tagName);
	
	public List<Tag> selectByCategoryList(Pagination page,Integer categoryId);
}