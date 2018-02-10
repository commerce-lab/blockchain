package io.clab.mpf.shop.business.service.uuser;

import io.clab.mpf.shop.business.entity.uuser.AssetsCount;
import io.clab.mpf.shop.business.entity.uuser.DataItem;


import io.clab.mpf.shop.business.service.base.IBaseService;

import com.baomidou.mybatisplus.plugins.Page;

public interface IDataItemService extends IBaseService<DataItem>{

	public Page<DataItem> selectByMerchantsIdList(int pageNow, int pageSize,Long merchantsId);
	
	public AssetsCount selectAssetsStatisticalByMerchantsId(Long merchantsId);
}
