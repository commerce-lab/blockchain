package io.clab.mpf.shop.business.service.goods.impl;

import io.clab.mpf.shop.business.entity.goods.Goods;
import io.clab.mpf.shop.business.entity.goods.GoodsDetail;
import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.repository.goods.GoodsMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.service.goods.IGoodsService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService{

	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	protected IBusBaseMapper<Goods> getMapper() {
		return goodsMapper;
	}

	
	public Page<GoodsDetail> selectPageList(int pageNow, int pageSize,Goods goods,Long startStrikePrice,Long endStrikePrice){
		Page<GoodsDetail> page = new Page<GoodsDetail>(pageNow,pageSize == 0?10:pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantsId", goods.getMerchantsId());
		paramMap.put("categoryId", goods.getCategoryId());
		paramMap.put("brandId", goods.getBrandId());
		paramMap.put("goodsName", goods.getGoodsName());
		paramMap.put("goodsNo", goods.getGoodsNo());
		paramMap.put("checkState", goods.getCheckState());
		paramMap.put("startStrikePrice", startStrikePrice);
		paramMap.put("endStrikePrice", endStrikePrice);

		page.setRecords(goodsMapper.selectPageList(page,paramMap));
		
		return page;
	}
}
