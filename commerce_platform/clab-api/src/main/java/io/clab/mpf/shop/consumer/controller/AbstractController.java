package io.clab.mpf.shop.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import io.clab.mpf.shop.util.SessionUtil;
import io.clab.mpf.shop.util.token.Jwt;
import io.clab.mpf.shop.util.token.TokenState;
/**
 * 公共controller
 */
public abstract class AbstractController {
	/** 日志 */
	protected  Logger logger = Logger.getLogger(this.getClass());
	/**打印日志*/
	protected void logInfo(HttpServletRequest request,String msg){
		logger.info(SessionUtil.getLogHead(request)+msg);
	}
	protected void logError(HttpServletRequest request,String msg,Throwable e){
		logger.error(SessionUtil.getLogHead(request)+msg,e);
	}protected void logError(String msg,Throwable e){
		logger.error(msg,e);
	}
	public Long getUserIdByToken(String token) {
		Map<String,Object> result = Jwt.validToken(token);
		if(!result.containsKey("uid")) {
			if(TokenState.EXPIRED.toString().equals(result.get("state"))) {
				return 0L;
			} else {
				return -1L;
			}
		}
		return Long.parseLong((String)result.get("uid"));
	}
}
