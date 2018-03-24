package io.clab.mpf.shop.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.clab.mpf.shop.common.entity.Region;
import io.clab.mpf.shop.common.service.IRegionService;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/region")
@Api(value = "行政区划分", description = "行政区划分")
public class RegionController {

	@Resource
	private IRegionService regionService;
	
	
	@ResponseBody
	@PostMapping("/getRegionBycode")
	@ApiOperation("根据行政区划代码查询所有下级行政区")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "regionCode", value = "行政区划代码(查询省默认传100000)", required = true,paramType="query"),
		})
	private JsonResponse<List<Region>> getRegionBycode(HttpServletRequest request,Integer regionCode) {
		JsonResponse<List<Region>> result = new JsonResponse<List<Region>>(SystemCode.SUCCESS);
		try {
			if(regionCode != null && regionCode > 0){
				List<Region> list = regionService.getRegionBycode(regionCode);
				result.setObj(list);
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
}
