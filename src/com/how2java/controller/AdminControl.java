package com.how2java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.Admin;
import com.how2java.pojo.User;
import com.how2java.service.AdminService;
import com.how2java.service.UserService;

@Controller      //控制器类
@RequestMapping("")
public class AdminControl {
	@Autowired
	AdminService adminService;//自动装配对象
	@Autowired
	UserService userService;
	@RequestMapping("adminLogin")//管理员登录界面
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("adminLogin");
	    return mav;
	}
	@RequestMapping("back_user")//后台管理用户界面
	public ModelAndView back_user(){
		List<User> us=userService.select();
		ModelAndView mav = new ModelAndView("back_user");
		mav.addObject("us", us);
	    return mav;
	}
	@RequestMapping("back_Login")//后台登录请求
	public ModelAndView back_Login(Admin admin,HttpServletRequest request){
		    Admin ad=new Admin();
		    ad=adminService.validate_Admin(admin.getName());
		    String admin_pwd=String.valueOf(ad.getPassword());
		    String ad_pwd=String.valueOf(admin.getPassword());
		    if(ad_pwd.equals(admin_pwd))
		    {
			ModelAndView mav = new ModelAndView("redirect:/back_user");//相当于重新打开一个页面
			//ModelAndView mav = new ModelAndView("back_user");//只是新建一个视图并不会请求数据库数据
		    return mav;
		    }
		    else
		    {
		    ModelAndView mav = new ModelAndView("adminLogin");
		    mav.addObject("tip","账号不存在或密码错误");
			return mav;
		    }
	}
}
