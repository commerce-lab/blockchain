package io.clab.mpf.shop.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.consumer.entity.user.User;
import io.clab.mpf.shop.consumer.service.user.IUserInfoService;
import io.clab.mpf.shop.util.DateUtils;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * by czh
 * **/
@Api(value="个人信息",description="个人信息")
@Controller
@RequestMapping("/c/user")
public class UserInfoController extends AbstractController{
	@Autowired
	private IUserInfoService userInfoService;
	@ResponseBody
	@PostMapping("/getUserInfo")
	@ApiOperation("获取个人信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query") })
	public  JsonResponse<User> getUserInfo(@RequestParam String token) {
		Long userId = getUserIdByToken(token);
		User user = userInfoService.get(userId);
		user.setSalt(null);
		user.setPwd(null);
		JsonResponse<User> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(user);
		return json;
	}
	
	@ResponseBody
	@PostMapping("/updateSex")
	@ApiOperation("修改性别")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sex", value = "取值：男，女", required = true, paramType = "query") })
	public JsonResponse<Boolean>  updateSex(@RequestParam String token,@RequestParam String sex){
		JsonResponse<Boolean> json = new JsonResponse<>();
		if(Lists.newArrayList("男","女").contains(sex)){
			Long userId = getUserIdByToken(token);
			User user = userInfoService.get(userId);
			user.setSex(sex);
			userInfoService.update(user);
			json.setRes(SystemCode.SUCCESS);
			json.setObj(true);
			json.setMsg("更新成功");
			return json;
		}else{
			json.setRes(SystemCode.FAILURE);
			json.setObj(false);
			json.setMsg("非法性别");
		}
		return json;
		
	}
	@ResponseBody
	@PostMapping("/updateNickname")
	@ApiOperation("修改个人昵称（昵称长度为2-10）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "nickname", value = "昵称", required = true, paramType = "query") })
	public JsonResponse<Boolean> updateNickname(@RequestParam String token,@RequestParam String nickname){
		JsonResponse<Boolean> json = new JsonResponse<>();
		if(nickname.length() >=2 && nickname.length() <=10){
			Long userId = getUserIdByToken(token);
			User user = userInfoService.get(userId);
			user.setNickname(nickname);
			userInfoService.update(user);
			json.setRes(SystemCode.SUCCESS);
			json.setObj(true);
			json.setMsg("更新成功");
			return json;
		}else{
			json.setRes(SystemCode.FAILURE);
			json.setObj(false);
			json.setMsg("长度访问必须在2-10");
		}
		return json;
		
		
	}
	@ResponseBody
	@PostMapping("/updateBirthday")
	@ApiOperation("修改个人出生日,如20180301")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
		@ApiImplicitParam(name = "birthday", value = "格式yyyyMMdd", required = true, paramType = "query") })
	public JsonResponse<Boolean> updateBirthday(@RequestParam String token,@RequestParam String birthday){
		DateUtils.parseDate(birthday, "yyyyMMdd");
		JsonResponse<Boolean> json = new JsonResponse<>();
		Long userId = getUserIdByToken(token);
		User user = userInfoService.get(userId);
		user.setBirthday(birthday);
		userInfoService.update(user);
		json.setRes(SystemCode.SUCCESS);
		json.setObj(true);
		json.setMsg("更新成功");
		return json;
	}
}
