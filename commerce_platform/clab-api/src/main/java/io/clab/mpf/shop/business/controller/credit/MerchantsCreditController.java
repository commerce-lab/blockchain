package io.clab.mpf.shop.business.controller.credit;

import java.util.List;

import io.clab.mpf.shop.business.entity.credit.MerchantsCredit;
import io.clab.mpf.shop.business.entity.credit.MerchantsCreditDetail;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.service.credit.IMerchantsCreditDetailService;
import io.clab.mpf.shop.business.service.credit.IMerchantsCreditService;
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

@Controller
@RequestMapping("/merchantsCredit")
@Api(value = "积分管理", description = "积分管理")
public class MerchantsCreditController {
	
	@Resource
	private IMerchantsCreditDetailService merchantsCreditDetailService;
	
	@Resource
	private IMerchantsCreditService merchantsCreditService;

	@ResponseBody
	@PostMapping("/creditCount")
	@ApiOperation("积分统计")
	private JsonResponse<MerchantsCredit> creditCount(HttpServletRequest request) {
		JsonResponse<MerchantsCredit> result = new JsonResponse<MerchantsCredit>(SystemCode.SUCCESS);
		
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		MerchantsCredit merchantsCredit = merchantsCreditService.findCreditByMerchantId(merchants.getMerchantsId());
		result.setObj(merchantsCredit);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/creditCountDetail")
	@ApiOperation("积分统计明细")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "year", value = "当前年份，如果2018年，传2018", required = true,paramType="query"),
		})
	private JsonResponse<List<MerchantsCreditDetail>> creditCountDetail(HttpServletRequest request,String year) {
		JsonResponse<List<MerchantsCreditDetail>> result = new JsonResponse<List<MerchantsCreditDetail>>(SystemCode.SUCCESS);
		
		Merchants merchants = SessionUtil.getMerchantsUser(request);
		if(merchants == null || merchants.getMerchantsId() <= 0){
			result.setRes(SystemCode.NO_LOGIN);
		    result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_LOGIN));  
		    return result;
		}
		
		List<MerchantsCreditDetail> list = merchantsCreditDetailService.findCreditDetailsByYear(merchants.getMerchantsId(), year);
		
		result.setObj(list);
		return result;
	}
}
