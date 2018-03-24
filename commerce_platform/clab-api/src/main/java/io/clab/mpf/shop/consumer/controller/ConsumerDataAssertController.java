package io.clab.mpf.shop.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertDetail;
import io.clab.mpf.shop.consumer.entity.credit.DataAssertLabel;
import io.clab.mpf.shop.consumer.service.dataassert.IConsumerDataAssertService;
import io.clab.mpf.shop.consumer.vto.dataAssert.CreditHisByLabelVto;
import io.clab.mpf.shop.consumer.vto.dataAssert.RelevantBusinessBasicInfoVto;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 数据资产 by chenzhenhua
 **/
@Api(value="数据资产",description="数据资产")
@Controller
@RequestMapping("/c/consumerDataAssert")
public class ConsumerDataAssertController extends AbstractController {
	@Autowired
	private IConsumerDataAssertService consumerDataAssertService;

	@ResponseBody
	@GetMapping("/getRelevantBusinessBasicInfo")
	@ApiOperation("消费者数据资产概要:相关商家数量，数据资产总收入")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"), })
	public JsonResponse<RelevantBusinessBasicInfoVto> getRelevantBusinessBasicInfo(@RequestParam String token) {
		Long userId = getUserIdByToken(token);
		int count = consumerDataAssertService.getRelevantBusinessCount(userId);
		Long income = consumerDataAssertService.getGeneralIncome(userId);
		RelevantBusinessBasicInfoVto vto = new RelevantBusinessBasicInfoVto();
		vto.setBusinessCount(count);
		vto.setIncome(income);
		JsonResponse<RelevantBusinessBasicInfoVto> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(vto);
		return json;
	}

	@ResponseBody
	@GetMapping("/getDataAssertLabel")
	@ApiOperation("我的数据资产-标签数据情况，获取消费者拥有的标签和对应的积分")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query")})
	public JsonResponse<DataAssertLabel> getDataAssertLabel(@RequestParam String token) {
		Long userId = getUserIdByToken(token);
		List<DataAssertLabel> vtoList = consumerDataAssertService.getDataAssertLabel(userId);
		JsonResponse<DataAssertLabel> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setList(vtoList);
		return json;
	}

	@ResponseBody
	@GetMapping("/getDataAssertHis")
	@ApiOperation("我的数据资产-数据记录，获取数据记录历史")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNow", value = "当前页", required = true, paramType = "query") })
	public JsonResponse<Page<DataAssertDetail>> getDataAssertHis(@RequestParam String token,
			@RequestParam Integer pageNow) {
		Long userId = getUserIdByToken(token);
		int pageSize = 10;
		Page<DataAssertDetail> page = consumerDataAssertService.getDataAssertHis(userId, pageNow, pageSize);
		JsonResponse<Page<DataAssertDetail>> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(page);
		return json;
	}
	@ResponseBody
	@GetMapping("/getDataDetailVto")
	@ApiOperation("我的数据资产-数据记录详情")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "dataItemId", value = "数据项id", required = true, paramType = "query")})
	public JsonResponse<DataAssertDetail> getDataDetailVto(@RequestParam String token, @RequestParam Long dataItemId) {
		Long userId = getUserIdByToken(token);
		DataAssertDetail dataDetailVto = consumerDataAssertService.getDataDetail(userId, dataItemId);
		JsonResponse<DataAssertDetail> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(dataDetailVto);
		return json;
	}
	@ResponseBody
	@GetMapping("/isGrantLabel")
	@ApiOperation("我的数据资产-消费者是否对某个标签进行了授权")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "label", value = "标签名称", required = true, paramType = "query")})
	public JsonResponse<Boolean> isGrantLabel(String token,String label){
		Long userId = getUserIdByToken(token);
		boolean isGrantLabel = consumerDataAssertService.isGrantLabel(userId, label);
		JsonResponse<Boolean> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(isGrantLabel);
		return json;
	}
	@ResponseBody
	@GetMapping("/getCreditQuantityByLabel")
	@ApiOperation("我的数据资产-标签数据详情-获得的积分数量(调用前先获取消费者是否对该标签授权了)")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "label", value = "标签名称", required = true, paramType = "query")})
	public JsonResponse<Long> getCreditQuantityByLabel(@RequestParam String token, @RequestParam String label){
		Long userId = getUserIdByToken(token);
		long quantity = consumerDataAssertService.getCreditQuantityByLabel(userId, label);
		JsonResponse<Long> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(quantity);
		return json;
	}
	@ResponseBody
	@GetMapping("/getCreditHisByLabel")
	@ApiOperation("我的数据资产-标签数据详情 - 获得的标签历史记录时间轴(调用前先获取消费者是否对该标签授权了)")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "label", value = "标签名称", required = true, paramType = "query")})
	public JsonResponse<CreditHisByLabelVto> getCreditHisByLabel(@RequestParam String token, @RequestParam String label){
		Long userId = getUserIdByToken(token);
		List<CreditHisByLabelVto> vtoList = consumerDataAssertService.getCreditHisByLabel(userId,label);
		JsonResponse<CreditHisByLabelVto> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setList(vtoList);
		return json;
	}
	@ResponseBody
	@GetMapping("/grantLabelSwitch")
	@ApiOperation("我的数据资产-标签数据详情 - 授权开关")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "label", value = "标签名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "isGrantg", value = "是否进行授权", required = true, paramType = "query")
		})
	public JsonResponse<Boolean> grantLabelSwitch(@RequestParam String token
			, @RequestParam String label,@RequestParam Boolean isGrantg ){
		Long userId = getUserIdByToken(token);
		boolean res = consumerDataAssertService.grantLabelSwitch(userId, label, isGrantg);
		JsonResponse<Boolean> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(res);
		return json;
	}
}
