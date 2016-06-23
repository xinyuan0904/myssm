/**  
 * @Project: MySSM
 * @Title: UserController.java
 * @Package com.myssm.yuan.controller
 * @author yuan
 * @date 2016年6月23日 下午3:45:53
 * @Copyright: 2016
 * @version V1.0  
*/

package com.myssm.yuan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myssm.yuan.pojo.User;
import com.myssm.yuan.service.IUserService;

/**
 * @ClassName UserController
 * @author yuan
 * @date 2016年6月23日
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/getUserList")
	public String getUserList(Model model){
		List<User> list = this.userService.getUserList();
		model.addAttribute("ulist",list);
		return "user";
	}
	
}
