package io.clab.mpf.shop.business.controller.goods;

import io.clab.mpf.shop.business.entity.goods.GoodsBrand;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.goods.IGoodsBrandService;
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
@RequestMapping("/goodsBrand")
@Api(value = "商品品牌", description = "商品品牌")
public class GoodsBrandController {

	@Resource
	private IGoodsBrandService goodsBrandService;
	
	
	@ResponseBody
	@PostMapping("/getAll")
	@ApiOperation("获取所有品牌")
	private JsonResponse<List<GoodsBrand>> getAll(HttpServletRequest request) {
		JsonResponse<List<GoodsBrand>> result = new JsonResponse<List<GoodsBrand>>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		List<GoodsBrand> list = goodsBrandService.getAll();

		result.setObj(list);
		result.setRes(SystemCode.SUCCESS);
	    result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));  
	    return result;
		
	}
}
