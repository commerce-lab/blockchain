package io.clab.mpf.shop.business.controller.goods;

import io.clab.mpf.shop.business.entity.goods.Goods;
import io.clab.mpf.shop.business.entity.goods.GoodsAndPrice;
import io.clab.mpf.shop.business.entity.goods.GoodsDetail;
import io.clab.mpf.shop.business.entity.goods.GoodsPrice;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.goods.IGoodsPriceService;
import io.clab.mpf.shop.business.service.goods.IGoodsService;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.JsonResponse;
import io.clab.mpf.shop.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/goods")
@Api(value = "商品", description = "商品")
public class GoodsController {

	@Resource
	private IGoodsService goodsService;
	
	@Resource
	private IGoodsPriceService goodsPriceService;
	
	@ResponseBody
	@PostMapping("/selectPageList")
	@ApiOperation("查询商品列表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		@ApiImplicitParam(name = "startStrikePrice", value = "商品ID", required = false,paramType="query"),
		@ApiImplicitParam(name = "endStrikePrice", value = "商品ID", required = false,paramType="query"),
		})
	private JsonResponse<Page<GoodsDetail>> selectPageList(HttpServletRequest request,Integer pageNow,Integer pageSize,Goods goods,Long startStrikePrice,Long endStrikePrice) {
		JsonResponse<Page<GoodsDetail>> result = new JsonResponse<Page<GoodsDetail>>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		goods.setMerchantsId(merchants.getMerchantsId());
		
		try {
			Page<GoodsDetail> page = goodsService.selectPageList(pageNow, pageSize, goods, startStrikePrice, endStrikePrice);

			result.setObj(page);
			result.setRes(SystemCode.SUCCESS);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/insertSelectiveEntity")
	@ApiOperation("新增商品")
	private JsonResponse<String> insertSelectiveEntity(HttpServletRequest request,Goods goods,GoodsPrice goodsPrice) {
		JsonResponse<String> result = new JsonResponse<String>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		goods.setMerchantsId(merchants.getMerchantsId());
		try {
			Integer i = goodsService.insertSelectiveEntity(goods);
			if(goods != null && goods.getGoodsId() > 0){
				goodsPrice.setGoodsId(goods.getGoodsId());
				i = goodsPriceService.insertSelectiveEntity(goodsPrice);
				result.setRes(SystemCode.SUCCESS);
			    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
			    return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
	
	
	@ResponseBody
	@PostMapping("/checkGoods")
	@ApiOperation("提交商品审核")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true,paramType="query"),
		})
	private JsonResponse<String> checkGoods(HttpServletRequest request,Integer  goodsId) {
		JsonResponse<String> result = new JsonResponse<String>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		try {
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setCheckState(0);
			Integer i = goodsService.updateByPrimaryKeySelective(goods);
			result.setRes(SystemCode.SUCCESS);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/selectByPrimaryKey")
	@ApiOperation("根据ID查询商品信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true,paramType="query"),
		})
	private JsonResponse<GoodsAndPrice> selectByPrimaryKey(HttpServletRequest request,Integer  goodsId) {
		JsonResponse<GoodsAndPrice> result = new JsonResponse<GoodsAndPrice>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		try {
			Goods goods = goodsService.selectByPrimaryKey(goodsId);
			
			GoodsPrice goodsPrice = goodsPriceService.selectByGoodsId(goodsId);
			
			GoodsAndPrice gp = new GoodsAndPrice();
			gp.setGoods(goods);
			gp.setGoodsPrice(goodsPrice);
			
			result.setObj(gp);
			result.setRes(SystemCode.SUCCESS);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/updateByPrimaryKeySelective")
	@ApiOperation("编辑商品")
	private JsonResponse<String> updateByPrimaryKeySelective(HttpServletRequest request,Goods goods,GoodsPrice goodsPrice) {
		JsonResponse<String> result = new JsonResponse<String>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		try {
			Integer i = goodsService.updateByPrimaryKeySelective(goods);
			if(goods != null && goods.getGoodsId() > 0){
				goodsPrice.setGoodsId(goods.getGoodsId());
				i = goodsPriceService.updateByPrimaryKeySelective(goodsPrice);
				result.setRes(SystemCode.SUCCESS);
			    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
			    return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/Marketable")
	@ApiOperation("商品上架下架")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "goodsId", value = "商品ID", required = true,paramType="query"),
		@ApiImplicitParam(name = "isMarketable", value = "上架标志 1 已上架 0 下架", required = true,paramType="query"),
		})
	private JsonResponse<String> Marketable(HttpServletRequest request,Integer  goodsId, Integer isMarketable) {
		JsonResponse<String> result = new JsonResponse<String>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		try {
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setIsMarketable(isMarketable);
			Integer i = goodsService.updateByPrimaryKeySelective(goods);
			result.setRes(SystemCode.SUCCESS);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
		
	}
}
