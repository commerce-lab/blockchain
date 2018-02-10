package io.clab.mpf.shop.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.consumer.constant.CreditHisRangeEnum;
import io.clab.mpf.shop.consumer.service.impl.ConsumerCreditService;
import io.clab.mpf.shop.consumer.vto.AddCreditSourceVto;
import io.clab.mpf.shop.consumer.vto.ConsumerCreditInfoVto;
import io.clab.mpf.shop.consumer.vto.CreditHisVto;
import io.clab.mpf.shop.controller.AbstractController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import util.JsonResponse;
@Controller
@RequestMapping("/c/consumerCredit")
public class ConsumerCreditController extends AbstractController{

	@Autowired
	private ConsumerCreditService consumerCreditService;

	@ResponseBody
	@PostMapping("/ConsumerCreditInfoVto")
	@ApiOperation("消费者积分信息概要")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "token", required = true,paramType="query"),
	})
	public JsonResponse<ConsumerCreditInfoVto> getCreditInfo(String token) {
		ConsumerCreditInfoVto vto = consumerCreditService.getCreditInfo(getUserIdByToken(token));
		JsonResponse<ConsumerCreditInfoVto> resp = new JsonResponse<>();
		resp.setObj(vto);
		return resp;
	}

	@ResponseBody
	@PostMapping("/getCreditHisList")
	@ApiOperation("用户获取或者支出积分历史记录")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNow", value = "当前页", required = true,paramType="query"),
		@ApiImplicitParam(name = "token", value = "token", required = true,paramType="query"),
		@ApiImplicitParam(name = "type", value = " 积分类型", required = true,paramType="query"),
	})
	public JsonResponse<Page<CreditHisVto>> getCreditHisList(String token, Integer type,
			int pageNow) {
		 int pageSize = 5;
		Page<CreditHisVto> page = consumerCreditService.getCreditHisList(getUserIdByToken(token), CreditHisRangeEnum.get(type), pageNow, pageSize);
		JsonResponse<Page<CreditHisVto>> resp = new JsonResponse<>();
		resp.setObj(page);
		return resp;
	}

	@ResponseBody
	@PostMapping("/getAddCreditSource")
	@ApiOperation("消费者获取的积分来源汇总")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "token", required = true,paramType="query"),
	})
	public JsonResponse<AddCreditSourceVto> getAddCreditSource(String token) {
		AddCreditSourceVto vto = consumerCreditService.getAddCreditSource(getUserIdByToken(token));
		JsonResponse<AddCreditSourceVto> resp = new JsonResponse<>();
		resp.setObj(vto);
		return resp;
	}
}
