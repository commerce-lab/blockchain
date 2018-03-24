package io.clab.mpf.shop.business.controller.goods;

import io.clab.mpf.shop.business.entity.goods.GoodsCategory;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.goods.IGoodsCategoryService;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.JsonResponse;
import io.clab.mpf.shop.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goodsCategory")
@Api(value = "商品种类", description = "商品种类")
public class GoodsCategoryController {

	@Resource
	private IGoodsCategoryService goodsCategoryService;
	
	
	@ResponseBody
	@PostMapping("/getAll")
	@ApiOperation("获取所有商品种类")
	private JsonResponse<List<GoodsCategory>> getAll(HttpServletRequest request) {
		JsonResponse<List<GoodsCategory>> result = new JsonResponse<List<GoodsCategory>>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		List<GoodsCategory> list = goodsCategoryService.getAll();
		
		result.setObj(list);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
}
