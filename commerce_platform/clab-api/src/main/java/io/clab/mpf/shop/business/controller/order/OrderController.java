package io.clab.mpf.shop.business.controller.order;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.entity.order.Order;
import io.clab.mpf.shop.business.service.goods.IGoodsPriceService;
import io.clab.mpf.shop.business.service.order.IOrderService;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.JsonResponse;
import io.clab.mpf.shop.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/order")
@Api(value = "订单管理", description = "订单管理")
public class OrderController {

	@Resource
	private IOrderService orderService;
	
	@ResponseBody
	@PostMapping("/selectPageList")
	@ApiOperation("查询订单列表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "查询第几页", required = true,paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "返回条数", required = true,paramType="query"),
		@ApiImplicitParam(name = "startCreateTime", value = "下单开始时间", required = false,paramType="query"),
		@ApiImplicitParam(name = "endCreateTime", value = "下单结束时间", required = false,paramType="query"),
		})
	private JsonResponse<Page<Order>> selectPageList(HttpServletRequest request,int pageNow, int pageSize,Order order,Date startCreateTime,Date endCreateTime) {
		JsonResponse<Page<Order>> result = new JsonResponse<Page<Order>>();
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		order.setMerchantsId(merchants.getMerchantsId());
		
		try {
			Page<Order> page = orderService.selectPageList(pageNow, pageSize, order, startCreateTime, endCreateTime);
			
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
}
