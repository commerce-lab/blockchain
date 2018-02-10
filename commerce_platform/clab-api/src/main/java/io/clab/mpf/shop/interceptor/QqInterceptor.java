package io.clab.mpf.shop.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.clab.mpf.shop.entity.admin.Admin;
import util.SessionUtil;



/**拦截器
 * 
 * */
public class QqInterceptor implements HandlerInterceptor{
	

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
			Admin adminUser = SessionUtil.getAdminUser(request);
				if(adminUser!=null){
					//session 中有用户信息
					return true;
				}
				response.getWriter().write("{\"res\":301}");
				return false;
		}
}
