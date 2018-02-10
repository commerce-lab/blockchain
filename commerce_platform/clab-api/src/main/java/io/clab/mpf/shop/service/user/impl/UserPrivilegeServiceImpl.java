package io.clab.mpf.shop.service.user.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.dao.user.UserPrivilegeMapper;
import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.entity.user.UserPrivilege;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.service.user.IUserPrivilegeService;

/**
 * 用户优惠接口实现

 */
@Service
public class UserPrivilegeServiceImpl extends BaseServiceImpl<UserPrivilege> implements IUserPrivilegeService {

	@Resource
	private UserPrivilegeMapper userPrivilegeMapper;

	@Override
	protected IBaseDao<UserPrivilege> getMapper() {
		return this.userPrivilegeMapper;
	}

	@Override
	public UserPrivilege queryUserPrivilege(User user) {
		return userPrivilegeMapper.selectByUserId(user.getUserId());
	}

	@Override
	public UserPrivilege getUserWholeSalePriceAndDiscountPrivate(User user) {
		 //`is_wholeSalePrice` tinyint(4) DEFAULT '0' COMMENT '享受批发价标志 1 享受批发价 0 不享受批发价',
		 // `isDiscount` tinyint(4) DEFAULT NULL COMMENT '是否享受折扣 1 是 0 否',
		 // `discount` int(11) DEFAULT NULL COMMENT '商品折扣 使用整数表示折扣数，比如98，表示98折',
		// 获取用户的优惠信息
		UserPrivilege userPrivilege = userPrivilegeMapper.selectByUserId(user
				.getUserId());
		if (null != userPrivilege) {			
			Byte isDiscountByte = userPrivilege.getIsDiscount();
			if (null == isDiscountByte || isDiscountByte == 0) {
				userPrivilege.setDiscount(100);//用户不享受折扣，则将折扣设为100
			}			
		}else{
			//没有查询到该人的权限，则不享受批发价，折扣为100
			userPrivilege =new UserPrivilege();
			userPrivilege.setIsWholeSalePrice((byte)0);
			userPrivilege.setIsDiscount((byte)0);
			userPrivilege.setDiscount(100);
		}
		return userPrivilege;
	}

}
