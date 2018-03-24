/**
 * 
 */
package io.clab.mpf.shop.consumer.controller;

import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.consumer.constant.RespCode;
import io.clab.mpf.shop.consumer.service.user.IUserLoginService;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@PostMapping("/register")
	@ApiOperation("用户注册")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "form"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
		@ApiImplicitParam(name = "authCode", value = "验证码", required = true, paramType = "form")})
	public JsonResponse<String> registUser(String mobile,String password,String authCode) {
		
		JsonResponse<String> response = new JsonResponse<String>();
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(authCode) 
				|| StringUtils.isEmpty(password)) {
			response.setCode(RespCode.PARAM_ERROR.getCode());
			response.setMsg(RespCode.PARAM_ERROR.getMsg());
			return response;
		}
		
		Boolean success = userLoginService.register(mobile, password, authCode);
		buildResponse(response, success);
		
		return response;
	}

	private void buildResponse(JsonResponse<String> response, Boolean success) {
		if(!success) {
			response.setCode(RespCode.ERROR.getCode());
			response.setMsg(RespCode.ERROR.getMsg());
		}
		
		response.setRes(SystemCode.SUCCESS);
		response.setCode(RespCode.SUCCESS.getCode());
	}
	
	@ResponseBody
	@PostMapping("/logout")
	@ApiOperation("用户注册")
	@ApiImplicitParams(
		{ @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "form")})
	public JsonResponse<String> logout(String mobile) {
		
		JsonResponse<String> response = new JsonResponse<String>();
		if(StringUtils.isEmpty(mobile) ) {
			response.setCode(RespCode.PARAM_ERROR.getCode());
			response.setMsg(RespCode.PARAM_ERROR.getMsg());
			return response;
		}
		
		Boolean success = userLoginService.logOut(mobile);
		buildResponse(response, success);
		
		return response;
	}
	
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
		response.setRes(SystemCode.SUCCESS);
		response.setCode(RespCode.SUCCESS.getCode());
		response.setObj(token);
		return response;
	}
}
