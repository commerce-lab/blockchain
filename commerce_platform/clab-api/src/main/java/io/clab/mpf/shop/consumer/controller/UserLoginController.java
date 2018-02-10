/**
 * 
 */
package io.clab.mpf.shop.consumer.controller;

import io.clab.mpf.shop.consumer.constant.RespCode;
import io.clab.mpf.shop.consumer.service.IUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.JsonResponse;

import com.alibaba.druid.util.StringUtils;

/**
 * @author iceray
 *
 */
@Controller
@RequestMapping("/c/user")
@Api(value = "用户登录", description = "用户登录api")
public class UserLoginController {

	@Autowired
	private IUserLoginService userLoginService;

	@ResponseBody
	@PostMapping("/codelogin")
	@ApiOperation(value = "验证码登录", notes = "手机验证码登录")
	public JsonResponse<String> authCodeLogin(@RequestParam("mobile") String mobile,
			@RequestParam("authcode") String authCode) {

		JsonResponse<String> response = new JsonResponse<String>();
        
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(authCode)) {
			response.setCode(RespCode.GET_TOKEN_ERROR.getCode());
			response.setMsg(RespCode.GET_TOKEN_ERROR.getMsg());
			return response;
		}
		
		String token = userLoginService.getToken(mobile, authCode);
		if(StringUtils.isEmpty(token)) {
			response.setCode(RespCode.GET_TOKEN_ERROR.getCode());
			response.setMsg(RespCode.GET_TOKEN_ERROR.getMsg());
			return response;
		}
		response.setCode(RespCode.SUCCESS.getCode());
		response.setObj(token);
		return response;
	}
}
