package io.clab.mpf.shop.business.service.uuser;

import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.service.base.IBaseService;

import com.baomidou.mybatisplus.plugins.Page;

public interface ITagService extends IBaseService<Tag>{

	public Tag selectByTagName(String tagName);
	
	/**
	 * 根据标签名模糊查询
	 * */
	public Page<Tag> selectLikeTagNameList(int pageNow, int pageSize,String tagName);
	
	/**
	 * 根据商家类目获取标签
	 * */
	public Page<Tag> selectByCategoryList(int pageNow, int pageSize,Integer categoryId);
}
