package io.clab.mpf.shop.business.controller.msg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.clab.mpf.shop.business.entity.msg.MsgTplSubj;
import io.clab.mpf.shop.business.service.msg.IMsgTplSubjService;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.consumer.controller.AbstractController;
import io.clab.mpf.shop.consumer.vto.credit.ConsumerCreditInfoVto;
import io.clab.mpf.shop.util.JsonResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/msgTplSubj")
public class MsgTplSubjController  extends AbstractController{
	private IMsgTplSubjService msgTplSubjService;

	@ResponseBody
	@GetMapping("/add")
	@ApiOperation("添加模板")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"), })
	public JsonResponse<Boolean> add(@RequestParam String token,@RequestParam String name) {
		Long userId = getUserIdByToken(token);
		boolean hasContainName = msgTplSubjService.hasContainName(name, userId);
		JsonResponse<Boolean> resp = new JsonResponse<>();
		if(hasContainName) {
			resp.setRes(SystemCode.FAILURE);
			resp.setMsg("主题名称已经存在！");
		}else {
			MsgTplSubj msgTplSubj = new MsgTplSubj();
			msgTplSubjService.insert(msgTplSubj);
			resp.setRes(SystemCode.SUCCESS);
			resp.setMsg("主题名称已经存在！");
		}
		
		return resp;
	}
}
