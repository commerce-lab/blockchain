package io.clab.mpf.shop.common.service.impl;


import io.clab.mpf.shop.business.repository.base.IBusBaseMapper;
import io.clab.mpf.shop.business.service.base.impl.BaseServiceImpl;
import io.clab.mpf.shop.business.util.GsonUtil;
import io.clab.mpf.shop.common.entity.LogisticsMsg;
import io.clab.mpf.shop.common.repository.LogisticsMsgMapper;
import io.clab.mpf.shop.common.service.ILogisticsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.internal.LinkedTreeMap;

@Service
public class LogisticsServiceImpl  extends BaseServiceImpl<LogisticsMsg> implements ILogisticsService{

	@Resource
	private LogisticsMsgMapper logisticsMsgMapper;
	
	@Override
	protected IBusBaseMapper<LogisticsMsg> getMapper() {
		// TODO Auto-generated method stub
		return logisticsMsgMapper;
	}

	@Override
	public Boolean kdBirdPush(String data) {
		System.out.println("data-->"+data);
		if(data != null && !"".equals(data)){
			List<LinkedTreeMap> dataList = GsonUtil.json2bean(data, ArrayList.class);
			if(dataList != null && dataList.size() > 0){
				for(LinkedTreeMap map : dataList){
					if(map != null && map.get("Success") != null && map.get("State") != null && map.get("Traces") != null){
						String Logistic_no = (String)map.get("LogisticCode");
						String siebel_company_code = (String)map.get("ShipperCode");
						if((Boolean)map.get("Success")){
							//状态
							String state = (String)map.get("State");
							
							ArrayList<LinkedTreeMap> list = (ArrayList<LinkedTreeMap>)map.get("Traces");
							
							if(list != null){
								ArrayList<HashMap<String, String>> newList = new ArrayList<HashMap<String,String>>();
								
								for(int i = (list.size() - 1) ; i >= 0;i--){
									HashMap<String, String> newMap = new HashMap<String, String>();
									newMap.put("time", (String)list.get(i).get("AcceptTime"));
									newMap.put("description", (String)list.get(i).get("AcceptStation"));
									newList.add(newMap);
								}
								String str = GsonUtil.bean2json(newList);
								try {
									//推送数据保存
									LogisticsMsg entity = new LogisticsMsg();
									entity.setLogisticNo(Logistic_no);
									entity.setState(state);
									entity.setContent(str);
									entity.setGetLogo("1");
									logisticsMsgMapper.insertSelectiveEntity(entity);
								} catch (Exception e) {
									e.printStackTrace();
									return false;
								}

							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public LogisticsMsg selectByLogisticNo(String logisticNo) {
		return logisticsMsgMapper.selectByLogisticNo(logisticNo);
	}

}
