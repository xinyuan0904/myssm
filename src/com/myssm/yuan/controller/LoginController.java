/**  
 * @Project: MySSM
 * @Title: LoginController.java
 * @Package com.online.owl.controller
 * @author yuan
 * @date 2016年6月24日 下午4:49:45
 * @Copyright: 2016
 * @version V1.0  
 */

package com.myssm.yuan.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName LoginController
 * @author yuan
 * @version 1.0
 */
@Controller
public class LoginController {

	/**
	 * 登录
	 * @MethodName execute 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @return
	 * @author yuan
	 * @date 2016年6月27日 下午2:29:23
	 * @return ModelAndView
	 */
	@RequestMapping("/login")
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		ModelAndView mv = new ModelAndView();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		// 记录该令牌
		token.setRememberMe(false);
		// subject权限对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		String exceptionClassName = (String) request
				.getAttribute("shiroLoginFailure");
		//判断是否已登录
		if (subject.isAuthenticated()){
			subject.logout();
		}
		try {
			// 提交申请，验证能不能通过，也回调reaml里的doGetAuthenticationInfo验证是否通过
			subject.login(token);
			System.out.println(exceptionClassName);
		} catch (UnknownAccountException ex) {// 用户名没有找到
			mv.addObject("msg", "用户未找到");
//			ex.printStackTrace();
		} catch (IncorrectCredentialsException ex) {// 用户名密码不匹配
			mv.addObject("msg", "密码不正确");
//			map.put("msg", "密码不正确");
//			ex.printStackTrace();
		} catch (AuthenticationException e) {// 其他的登录错误
			mv.addObject("msg", "其他错误");
//			e.printStackTrace();
		} catch (Exception e) {
			mv.addObject("msg", "登录异常");
//			e.printStackTrace();
		}
		
		// 验证是否成功登录的方法
		if (subject.isAuthenticated()) {
			mv.setViewName("index");
		}else{
//			mv.setViewName("redirect:/login.jsp");
			mv.setViewName("login"); //此处偷懒，一般是ajax请求，或重定向时将失败传回
		}
//		return new ModelAndView("redirect:/login.jsp");
		return mv;
	}

	// 退出
	@RequestMapping("/logout")
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
}
