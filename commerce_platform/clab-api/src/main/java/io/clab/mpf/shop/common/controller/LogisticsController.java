package io.clab.mpf.shop.common.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import io.clab.mpf.shop.business.util.GsonUtil;
import io.clab.mpf.shop.common.service.ILogisticsService;
import io.swagger.annotations.Api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/logistics")
@Api(value = "物流", description = "物流")
public class LogisticsController {

	@Resource
	private ILogisticsService logisticsService;
	
	
	/**
	 * 推送
	 * */
	@ResponseBody
	@RequestMapping(value="/push")
	public HashMap<String, Object> push(String EBusinessID,String Count,String PushTime,String RequestData){
		System.out.println("RequestData:"+RequestData);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Success", false);
		map.put("Reason", "");
		try {
			HashMap<String, Object> dataMap = GsonUtil.json2bean(RequestData, HashMap.class);
			map.put("EBusinessID",  dataMap.get("EBusinessID"));
			map.put("UpdateTime",  dataMap.get("PushTime"));
			//map.put("Success", true);
			
			String Data = GsonUtil.bean2json(dataMap.get("Data"));
			if(Data != null && !"".equals(Data)){
				Boolean flag = logisticsService.kdBirdPush(Data);
				if(flag){
					map.put("Success", true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
