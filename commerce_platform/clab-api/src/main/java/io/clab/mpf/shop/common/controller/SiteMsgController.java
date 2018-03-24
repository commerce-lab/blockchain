package io.clab.mpf.shop.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import io.clab.mpf.shop.common.service.ISiteMsgReceiverService;
import io.clab.mpf.shop.common.vto.SiteMsgVto;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.consumer.controller.AbstractController;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * by chenzhenhua
 ***/
@Controller
@RequestMapping("/siteMsg")
@Api(value = "站内消息", description = "站内消息")
public class SiteMsgController extends AbstractController {
	@Autowired
	private ISiteMsgReceiverService siteMsgReceiverService;

	@ApiOperation("用户获取所有接收到的站内通知列表,默认100条")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNow", value = "当前页", required = true, paramType = "query") })
	@PostMapping("/getAllReceiveList")
	@ResponseBody
	public JsonResponse<Page<SiteMsgVto>> getAllReceiveList(@RequestParam String token, @RequestParam Integer pageNow) {
		Long receiverId = getUserIdByToken(token);
		int pageSize = 100;
		Page<SiteMsgVto> page = siteMsgReceiverService.getAllReceiveList(receiverId, pageNow, pageSize);
		JsonResponse<Page<SiteMsgVto>> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(page);
		return json;
	}

	@ApiOperation("用户获取所有未读的站内通知列表，默认100条")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNow", value = "当前页", required = true, paramType = "query") })
	@PostMapping("/getAllNotReadReceiveList")
	@ResponseBody
	public JsonResponse<Page<SiteMsgVto>> getAllNotReadReceiveList(@RequestParam String token,
			@RequestParam Integer pageNow) {

		Long receiverId = getUserIdByToken(token);
		int pageSize = 100;
		Page<SiteMsgVto> page = siteMsgReceiverService.getAllNotReadReceiveList(receiverId, pageNow, pageSize);
		JsonResponse<Page<SiteMsgVto>> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(page);
		return json;
	}

	@ApiOperation("用户获取未读站内消息数量")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"), })
	@GetMapping("/getNotReadCount")
	@ResponseBody
	public JsonResponse<Long> getNotReadCount(@RequestParam String token) {
		Long receiverId = getUserIdByToken(token);
		long notReadCount = siteMsgReceiverService.getNotReadCount(receiverId);
		JsonResponse<Long> json = new JsonResponse<>();
		json.setRes(SystemCode.SUCCESS);
		json.setObj(notReadCount);
		return json;
	}

	@ApiOperation("将一条站内消息置为已读")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "站内消息id", required = true, paramType = "query"), })
	@GetMapping("/markRead")
	@ResponseBody
	public JsonResponse<Boolean> markRead(@RequestParam String token, @RequestParam Long id) {
		Long receiverId = getUserIdByToken(token);
		JsonResponse<Boolean> json = new JsonResponse<>();
		boolean res = siteMsgReceiverService.markRead(id, receiverId);
		if (res) {
			json.setRes(SystemCode.SUCCESS);
			json.setObj(true);
			json.setMsg("成功置为已读");
		} else {
			json.setRes(SystemCode.FAILURE);
			json.setObj(false);
			json.setMsg("操作失败");
		}
		return json;
	}

	@ApiOperation("删除一条站内消息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "站内消息id", required = true, paramType = "query"), })
	@GetMapping("/delete")
	@ResponseBody
	public JsonResponse<Boolean> delete(@RequestParam String token, @RequestParam Long id) {
		Long receiverId = getUserIdByToken(token);
		JsonResponse<Boolean> json = new JsonResponse<>();
		boolean res = siteMsgReceiverService.delete(id, receiverId);
		if (res) {
			json.setRes(SystemCode.SUCCESS);
			json.setObj(true);
			json.setMsg("删除成功");

		} else {
			json.setRes(SystemCode.FAILURE);
			json.setObj(false);
			json.setMsg("操作失败");
		}
		return json;
	}
}
