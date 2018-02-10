package io.clab.mpf.shop.business.service.uuser.impl;

import java.util.List;

import io.clab.mpf.shop.business.entity.uuser.AssetsCount;
import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.uuser.DataItemMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.uuser.IDataItemService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class IDataItemServiceImpl extends BaseServiceImpl<DataItem> implements IDataItemService{

	@Resource
	private DataItemMapper dataItemMapper;

	@Override
	protected IBusBaseMapper<DataItem> getMapper() {
		return dataItemMapper;
	}
	
	@Override
	public Page<DataItem> selectByMerchantsIdList(int pageNow, int pageSize,Long merchantsId) {
		Page<DataItem> page = new Page<DataItem>(pageNow,pageSize == 0?10:pageSize);
		
		List<DataItem> list = dataItemMapper.selectByMerchantsIdList(page, merchantsId);
		page.setRecords(list);
		return page;
	}
	
	@Override
	public AssetsCount selectAssetsStatisticalByMerchantsId(Long merchantsId){
		return dataItemMapper.selectAssetsStatisticalByMerchantsId(merchantsId);
	}




}
