package io.clab.mpf.shop.service.shortMsg;

import javax.servlet.http.HttpServletRequest;

import io.clab.mpf.shop.entity.shortMsg.ShortMessageModel;
import io.clab.mpf.shop.service.base.IBaseService;
import util.JsonResponse;

public interface IShortMessageService extends IBaseService<ShortMessageModel> {
	public ShortMessageModel getCodeByPhone(String phone);
	
	public JsonResponse<String> sendMessage(HttpServletRequest request,String phone);
	public JsonResponse<String> checkCode(String phone,Integer code);
}