package io.clab.mpf.shop.business.controller.merchants;

import io.clab.mpf.shop.business.entity.admin.Admin;
import io.clab.mpf.shop.business.entity.merchants.Merchants;
import io.clab.mpf.shop.business.entity.merchants.MerchantsCategory;
import io.clab.mpf.shop.business.service.merchants.IMerchantsCategoryService;
import io.clab.mpf.shop.business.service.merchants.IMerchantsService;
import io.clab.mpf.shop.business.util.EmailUtil;
import io.clab.mpf.shop.business.util.RedisUtil;
import io.clab.mpf.shop.constant.SystemCode;
import io.clab.mpf.shop.util.Configure;
import io.clab.mpf.shop.util.JsonResponse;
import io.clab.mpf.shop.util.UserIdWroker;
import io.clab.mpf.shop.util.encryp.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/merchants")
@Api(value = "商家入驻", description = "商家入驻")
public class MerchantsController {
	
	 private static Logger logger = Logger.getLogger(MerchantsController.class);
	
	@Resource
	private IMerchantsService merchantsService;
	
	@Resource
	private IMerchantsCategoryService merchantsCategoryService;
	
	@ResponseBody
	@PostMapping("/sendEmailRandomCode")
	@ApiOperation("向邮箱发送验证码")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "email", value = "邮箱地址", required = true,paramType="query"),
		})
	private JsonResponse<String> sendEmailRandomCode(HttpServletRequest request,String email) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.SUCCESS);
		try {
			//获取随机码
 			String r = MD5Util.getRand();
			//存redis
 			RedisUtil.getInstance().strings().setEx(Configure.email_randm_code+email+r, 20 * 60, r+"");

 			//发邮件
 			EmailUtil.sendHtmlMail(email, "clab注册邮箱认证", "尊敬的客户,您好!<Br/><Br/>感谢你申请入驻Clab,你正在进行邮箱验证：请在验证码框输入此验证码："+r+",以完成验证。");
 			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
	}
	
	@ResponseBody
	@PostMapping("/registered")
	@ApiOperation("入驻")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "randomCode", value = "邮箱验证码", required = true,paramType="query"),
		})
	private JsonResponse<String> registered(HttpServletRequest request,Merchants merchants,String randomCode) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.SUCCESS);
		if(merchants == null || merchants.getContactEmail() == null || merchants.getContactPhone() == null){
			result.setRes(SystemCode.FAILURE);
			result.setResult("请输入邮箱和手机号码。");
			return result;
		}
		
		if(randomCode == null ){
			result.setRes(SystemCode.FAILURE);
			result.setResult("请输入验证码。");
			return result;
		}
		
		//判断邮箱验证码
		String str = RedisUtil.getInstance().strings().get(Configure.email_randm_code+merchants.getContactEmail()+randomCode);
		if(!randomCode.equals(str)){
			result.setRes(SystemCode.FAILURE);
			result.setResult("输入验证码不正确或已经过期，请输入正确验证码或重新获取验证码。");
			return result;
		}

		try {
			Merchants temp = merchantsService.getOne(merchants);
			if(temp != null && temp.getContactEmail() != null){
				result.setRes(SystemCode.FAILURE);
				result.setResult("对不起，该邮箱已经入驻。");
				return result;
			}
			
			merchants.setMerchantsId(UserIdWroker.getMerchantsId());
			Integer i = merchantsService.insertSelectiveEntity(merchants);
			if(i > 0){
				//添加用户
				Admin admin = new Admin();
				admin.setAdminName(merchants.getContactEmail());
				String rand = MD5Util.getRand();
				String password = MD5Util.encoder(merchants.getPassword(), rand);
				admin.setRand(rand);
				admin.setPassword(password);
				Date now = new Date();
				admin.setCreateTime(now);
				admin.setUpdateTime(now);
				
				//adminService.insertSelective(admin);
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
	
	@ResponseBody
	@PostMapping("/isRegistered")
	@ApiOperation("是否已经入驻（返回true or false）")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "account", value = "账号（email）", required = true,paramType="query"),
		})
	private JsonResponse<String> isRegistered(HttpServletRequest request,String account) {
		JsonResponse<String> result = new JsonResponse<String>(SystemCode.SUCCESS);
		try {
			Merchants merchants = new Merchants();
			merchants.setContactEmail(account);
			merchants.setContactPhone(account);
			Merchants temp = merchantsService.getOne(merchants);
			if(temp != null && temp.getContactEmail() != null){
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
	
	@ResponseBody
	@PostMapping("/getMerchantsByAccount")
	@ApiOperation("根据账号获取商家信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "account", value = "账号（email）", required = true,paramType="query"),
		})
	private JsonResponse<Merchants> getMerchantsByAccount(HttpServletRequest request,String account) {
		JsonResponse<Merchants> result = new JsonResponse<Merchants>(SystemCode.SUCCESS);
		try {
			Merchants merchants = new Merchants();
			merchants.setContactEmail(account);
			merchants.setContactPhone(account);
			Merchants temp = merchantsService.getOne(merchants);
			if(temp != null && temp.getContactEmail() != null){
				result.setObj(temp);
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
	
	@ResponseBody
	@PostMapping("/getALLCategory")
	@ApiOperation("获取商家类目")
	private JsonResponse<List<MerchantsCategory>> getALLCategory(HttpServletRequest request) {
		JsonResponse<List<MerchantsCategory>> result = new JsonResponse<List<MerchantsCategory>>(SystemCode.SUCCESS);
		try {
			
			List<MerchantsCategory> list = merchantsCategoryService.getAll();
			result.setObj(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 失败
		result.setRes(SystemCode.FAILURE);
		result.setResult(SystemCode.GetErrorDesc(SystemCode.FAILURE));
		return result;
	}

	
}
