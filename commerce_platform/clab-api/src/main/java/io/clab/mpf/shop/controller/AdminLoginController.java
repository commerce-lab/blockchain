package io.clab.mpf.shop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import util.JsonResponse;
import util.SessionUtil;
import util.Encryp.MD5Util;
import constant.SystemCode;
import io.clab.mpf.shop.entity.admin.Admin;
import io.clab.mpf.shop.service.admin.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 *
 *  商家用户登陆退出
 *
 */
@Controller
@RequestMapping("/adminLogin")
@Api(value = "商家用户登陆退出", description = "登陆退出api")
public class AdminLoginController {
	
	@Resource
	private IAdminService adminService;
	
	
	/**
	 * @param request
	 * @param admin 
	 *  登录
	 * @return adminName,password
	 */
	@ResponseBody
	//@RequestMapping(value ="/toLogin",method = RequestMethod.POST)
	@PostMapping("/toLogin")
	@ApiOperation(value = "登录", notes = "商家用户登陆")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "adminName", value = "用户名", required = true,paramType="query"),
		@ApiImplicitParam(name = "password", value = "密码", required = true,paramType="query"),
		})
	private JsonResponse<Admin> toLogin(HttpServletRequest request,Admin admin) {
		JsonResponse<Admin> result = new JsonResponse<Admin>(SystemCode.FAILURE);
		Admin model = adminService.login(admin);
		//首先判断用户状态是否为关闭
		if(model.getState() == 2) {
			return result;
		}
		if (model != null) {
			// 登录成功
			String password = MD5Util.encoder(admin.getPassword(),model.getRand());
			if (password.equals(model.getPassword())) {
				
				SessionUtil.setAdminUser(request, model);
				// 登陆成功
				result.setRes(SystemCode.SUCCESS);
				result.setObj(model);
				result.setResult(SystemCode.GetErrorDesc(SystemCode.SUCCESS));
				return result;
			}
		}
		// 登录失败
		result.setRes(SystemCode.NO_OBJ_ERROR_PASS);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.NO_OBJ_ERROR_PASS));
		return result;
	}

	@ResponseBody
	@RequestMapping("/loginOut")
	public JsonResponse<String> loginOut(HttpServletRequest request) {
		JsonResponse<String> result = new JsonResponse<String>();
		request.getSession().invalidate();
		result.setRes(SystemCode.SUCCESS);
		return result;
	}

	@ResponseBody
	@RequestMapping("/isLogin")
	public JsonResponse<String> isLogin(HttpServletRequest request) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
		Admin admin = SessionUtil.getAdminUser(request);
		if (admin != null) {
			result.setRes(SystemCode.SUCCESS);
		}
		return result;
	}

}
