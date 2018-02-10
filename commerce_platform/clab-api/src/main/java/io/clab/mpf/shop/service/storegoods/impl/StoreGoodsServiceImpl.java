package io.clab.mpf.shop.service.storegoods.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.clab.mpf.shop.dao.base.IBaseDao;
import io.clab.mpf.shop.dao.storegoods.StoreGoodsMapper;
import io.clab.mpf.shop.entity.storegoods.StoreGoods;
import io.clab.mpf.shop.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.service.storegoods.IStoreGoodsService;

@Service
public class StoreGoodsServiceImpl extends BaseServiceImpl<StoreGoods>
		implements IStoreGoodsService {

	@Resource
	private StoreGoodsMapper storeGoodsMapper;

	@Override
	protected IBaseDao<StoreGoods> getMapper() {
		return storeGoodsMapper;
	}

	@Override
	public List<StoreGoods> queryAllStoreGoodsByUserId(StoreGoods storeGoods) {
		return storeGoodsMapper.queryAllStoreGoodsByUserId(storeGoods);
	}

	@Override
	public StoreGoods queryStoreGoodsByUserIdAndGoodsId(StoreGoods storeGoods) {
		return storeGoodsMapper.queryStoreGoodsByUserIdAndGoodsId(storeGoods);
	}

}
