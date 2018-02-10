package io.clab.mpf.shop.business.service.uuser;

import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.entity.uuser.ShopConditiontCount;
import io.clab.mpf.shop.business.entity.uuser.ShopNewData;
import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.entity.uuser.UserAssetsParameter;
import io.clab.mpf.shop.business.entity.uuser.Uuser;
import io.clab.mpf.shop.business.service.base.IBaseService;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

public interface IUuserService extends IBaseService<Uuser>{

	public Uuser selectByPhone(Long phone);
	
	public Integer selectAllCount();
	
	public ShopConditiontCount selectConditionCount();
	
	public Page<ShopNewData> selectShopNewData(int pageNow, int pageSize);
	
	public Page<Uuser> selectConditionTwo(int pageNow, int pageSize,DataItem dataItem,Long merchantsId,Integer categoryId,List<Tag> tags,Integer filterHaveUser,Integer filterHaveUserTag);

	public Page<Uuser> selectUserAssetsList(int pageNow, int pageSize,UserAssetsParameter userAssetsParameter);
}
