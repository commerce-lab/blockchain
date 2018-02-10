package io.clab.mpf.shop.business.repository.uuser;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.entity.uuser.ShopConditiontCount;
import io.clab.mpf.shop.business.entity.uuser.ShopNewData;
import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.entity.uuser.UserAssetsParameter;
import io.clab.mpf.shop.business.entity.uuser.Uuser;

public interface UuserMapper extends IBusBaseMapper<Uuser> , BaseMapper<Uuser>{

	public Uuser selectByPhone(Long phone);
	
	public Integer selectAllCount();
	
	public ShopConditiontCount selectConditionCount();
	
	public List<ShopNewData> selectShopNewData(Pagination page);
	
	public List<Uuser>  selectConditionTwo(Pagination page,DataItem dataItem,Long merchantsId,Integer categoryId,List<Tag> tags,Integer filterHaveUser,Integer filterHaveUserTag);
	
	public List<Uuser> selectUserAssetsList(Pagination page,UserAssetsParameter userAssetsParameter); 
	
}