package io.clab.mpf.shop.dao.receive;

import java.util.List;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.entity.receive.Receive;

public interface ReceiveMapper extends IBaseDao<Receive> {
	public Receive selectUserReceive(String openid);
	
	public Receive selectDefaultReceive(int userId);
	public List<Receive> selectReceive(int userId);
}