package io.clab.mpf.shop.dao.shortMsg;

import org.apache.ibatis.annotations.Param;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.shortMsg.ShortMessageModel;

public interface IShortMessageDao extends IBaseDao<ShortMessageModel> {
	public ShortMessageModel getCodeByPhone(@Param("phone")String phone);
}