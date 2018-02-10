package io.clab.mpf.shop.business.repository.uuser;

import io.clab.mpf.shop.business.entity.uuser.AssetsCount;
import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface DataItemMapper extends IBusBaseMapper<DataItem> , BaseMapper<DataItem> {

	public List<DataItem> selectByMerchantsIdList(Pagination page,Long merchantsId);
	
	public AssetsCount selectAssetsStatisticalByMerchantsId(Long merchantsId);
	
	
}