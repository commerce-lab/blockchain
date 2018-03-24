package io.clab.mpf.shop.business.repository.uuser;

import io.clab.mpf.shop.business.entity.uuser.ShopConditiontCount;
import io.clab.mpf.shop.business.entity.uuser.ShopNewData;
import io.clab.mpf.shop.business.entity.uuser.UserAssetsParameter;
import io.clab.mpf.shop.business.entity.uuser.Uuser;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface UuserMapper extends IBusBaseMapper<Uuser> , BaseMapper<Uuser>{

	public Uuser selectByPhone(Long phone);
	
	public Integer selectAllCount();
	
	public ShopConditiontCount selectConditionCount(Map paramMap);
	
	public List<ShopNewData> selectShopNewData(Pagination page);
	
	public List<Uuser>  selectConditionTwo(Pagination page,Map paramMap);
	
	public List<Uuser> selectUserAssetsList(Pagination page,UserAssetsParameter userAssetsParameter); 
	
}