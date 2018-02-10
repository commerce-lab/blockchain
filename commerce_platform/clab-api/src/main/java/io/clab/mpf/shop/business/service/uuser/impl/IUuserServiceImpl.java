package io.clab.mpf.shop.business.service.uuser.impl;

import io.clab.mpf.shop.business.entity.uuser.DataItem;
import io.clab.mpf.shop.business.entity.uuser.ShopConditiontCount;
import io.clab.mpf.shop.business.entity.uuser.ShopNewData;
import io.clab.mpf.shop.business.entity.uuser.Tag;
import io.clab.mpf.shop.business.entity.uuser.UserAssetsParameter;
import io.clab.mpf.shop.business.entity.uuser.Uuser;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.uuser.UuserMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.uuser.IUuserService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class IUuserServiceImpl extends BaseServiceImpl<Uuser> implements IUuserService{

	@Resource
	private UuserMapper uuserMapper;

	@Override
	protected IBusBaseMapper<Uuser> getMapper() {
		return uuserMapper;
	}
	
	public Uuser selectByPhone(Long phone){
		return uuserMapper.selectByPhone(phone);
		
	}
	
	public Integer selectAllCount(){
		return uuserMapper.selectAllCount();
	}


	public ShopConditiontCount selectConditionCount(){
		return uuserMapper.selectConditionCount();
	}
	
	public Page<ShopNewData> selectShopNewData(int pageNow, int pageSize){
		Page<ShopNewData> page = new Page<ShopNewData>(pageNow,pageSize == 0?10:pageSize);
		page.setRecords(uuserMapper.selectShopNewData(page));
		return page;
	}
	
	public Page<Uuser> selectConditionTwo(int pageNow, int pageSize,DataItem dataItem,Long merchantsId,Integer categoryId,List<Tag> tags,Integer filterHaveUser,Integer filterHaveUserTag){
		Page<Uuser> page = new Page<Uuser>(pageNow,pageSize == 0?10:pageSize);
		page.setRecords(uuserMapper.selectConditionTwo(page, dataItem, merchantsId, categoryId, tags, filterHaveUser, filterHaveUserTag));

		return page;
	}
	
	public Page<Uuser> selectUserAssetsList(int pageNow, int pageSize,UserAssetsParameter userAssetsParameter){
		Page<Uuser> page = new Page<Uuser>(pageNow,pageSize == 0?10:pageSize);
		page.setRecords(uuserMapper.selectUserAssetsList(page, userAssetsParameter));
		
		return page;
	}
}
