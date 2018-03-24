package io.clab.mpf.shop.consumer.interceptor;

import io.clab.mpf.shop.consumer.service.token.ITokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class QqWebInterceptor implements HandlerInterceptor{

	@Autowired
	private ITokenService tokenService;

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}
	/**
	 *  true表示继续流程（如调用下一个拦截器或处理器）；
     *  false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		    String token = request.getParameter("token");
		    if(StringUtils.isEmpty(token)) {
			    response.getWriter().write("{\"res\":301,\"msg\":\"token error\"}");
			    return false;
		    }
		    
		    Long userId = tokenService.getUserIdByToken(token);
		    if(userId == 0L) {
		    	response.getWriter().write("{\"res\":302,\"msg\":\"token invalidate\"}");
		    	return false;
		    }
		    
		    if(userId == -1L) {
		    	response.getWriter().write("{\"res\":301,\"msg\":\"token error\"}");
			    return false;
		    }
		    	

			return true;
		}
}
