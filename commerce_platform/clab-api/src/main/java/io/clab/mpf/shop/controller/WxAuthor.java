package io.clab.mpf.shop.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.WordUtils;
//import org.apache.commons.lang3.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.CommondUtil;
import util.Configure;
import util.SessionUtil;
import util.StringFormat;
import util.http.HttpsUtil;

import com.alibaba.fastjson.JSONObject;

import io.clab.mpf.shop.entity.user.User;
import io.clab.mpf.shop.service.user.IUserService;


/**
 *
 * @Date：2016年12月9日 9:38:31
 * 微信授权 回调
 */
@Controller
@RequestMapping("/wxAuthor")
public class WxAuthor {
	private static Logger log =LoggerFactory.getLogger(WxAuthor.class);
	
	@Resource
	private IUserService userService;
	/**
	 * 用户授权后微信回调地址
	 */
	@RequestMapping("/user")
	public void getUserInfo(@RequestParam(required=false,value="code")String code,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false,value="state")String state){
		log.info("开始到了微信回调");
		JSONObject jsonObject =null;//处理微信返回json数据
		
		User user=null;
		String openid="";//微信用户openid
		String access_token="";//授权码
		String resultStr ="";//接口返回字符
		String requesUrl = Configure.WX_GET_ACCESS_URL;//获取openid地址
		String userinfoUrl= Configure.WX_GET_USER_INFO_URL;//拉取用户信息地址
		try {
			if(!StringUtils.isBlank(code)&&!StringUtils.isBlank(requesUrl)){//开始获取用户openid
				requesUrl = requesUrl.replace("APP_ID",Configure.APP_ID).replace("APP_SECRET",Configure.APP_SECRET).replace("USE_CODE", code);
				resultStr = HttpsUtil.httpsRequest(requesUrl, "GET", null);//调用获取openid接口
				if(!StringUtils.isBlank(resultStr)&&resultStr.indexOf("openid")>0){
					jsonObject =JSONObject.parseObject(resultStr);
					openid=jsonObject.get("openid").toString();
					log.info("userService.getOneByOpenid" + "...." + openid);
					user = userService.getOneByOpenid(openid);
					if(user==null){
						log.info("user==null");
						if(resultStr.indexOf("access_token")>0){
							access_token=jsonObject.get("access_token").toString();
						}
						userinfoUrl= userinfoUrl.replace("ACCESS_TOKEN", access_token).replace("OPEN_ID", openid);
						resultStr = HttpsUtil.httpsRequest(userinfoUrl, "GET", null);//拉取用户信息
						if(!StringUtils.isBlank(resultStr) && resultStr.indexOf("openid")>0){//判断返回值是否含有openid
							jsonObject = JSONObject.parseObject(resultStr);
							//这里可以用oppenid获取数据库t_user表里的user信息判断是否已经存在该user
								user=CommondUtil.getWxUser(jsonObject);
								//开始插入用户信息
								String nickName=StringFormat.toSubString(user.getNickname());
								if("".equals(nickName)){
									user.setNickname("...");
								}else{
									user.setNickname(nickName);
								}
								user.setCreateTime(new Date());
								userService.insertSelective(user);
								log.error("userService.insertSelective" + user);
						}
				}
				SessionUtil.setUser(request, user);
				log.error("SessionUtil.setUser" + user);
//				response.sendRedirect("*****?openid="+openid);//跳转至前端 html
				response.sendRedirect("http://localhost:4003/");
				log.info("开始到了微信回调");
			}else{//授权失败或者网络异常
				log.error("授权失败,返回code为："+code+"获取openid地址:"+requesUrl);
			}
		}else{
			//获取code失败 用户不同意授权，以游客身份进入
			log.error("获取code失败....");
		}
	} catch (Exception e) {
			log.error("发生异常："+e.getMessage());
	}
		log.info("微信回调结束");
	}
}
