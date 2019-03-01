package com.how2java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.User;
import com.how2java.service.UserService;

// 告诉spring mvc这是一个控制器类

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	UserService userService;// 装配接口的同时会自动生成一个代理类

	@RequestMapping("index") // 首页
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("register") // 注册界面
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping("defaultjsp") // 注册界面
	public ModelAndView defaultjsp() {
		ModelAndView mav = new ModelAndView("default");
		return mav;
	}

	@RequestMapping("addUser") // 注册请求
	// request很重要，用来获取客户端的数据及对象
	public ModelAndView addUser(User user, HttpServletRequest request) {
		if ((user == null || user.getName() == "" || user.getPassword() == ""))// 如果用户名和密码不为空
		{
			ModelAndView mav = new ModelAndView("register");
			mav.addObject("tip", "用户名和密码不能为空");
			return mav;
		} else {
			String s = user.getPassword();// 得到传过来的密码
			String smi = convertMD5(s);// 二次加密
			user.setPassword(smi);
			userService.add(user);
			ModelAndView mav = new ModelAndView("default");
			return mav;
		}
	}

	@ResponseBody
	@RequestMapping("login_ajax") // 登录请求
	// 返回的是一个Map数组,键值对
	public Map<String, String> login_ajax(HttpServletRequest request, HttpServletResponse response, User user,
			String code)// 这里是获取表单属性
	{
		Map<String, String> map = new HashMap<String, String>();
		String s = user.getPassword();
		String smi = convertMD5(s);// 二次加密
		List<User> us = userService.select();// 获取集合
		int login_flag = 0;
		System.out.println(smi);
		// mav.addObject("us", us);//往视图里面放入数据库中的参数
		// mav.addObject("us_view", user);//往数据库里放入其它页面传来的数据
		for (User u : us) {
			if (u.getName().equals(user.getName()))// 用户名相等
			{
				System.out.println(u.getPassword());
				if (u.getPassword().equals(smi))// 如果密码输入正确
				{
					if (u.getStatue().equals("启用")) {
						login_flag = 1;
					} else {
						login_flag = 2;
					}
				}
			}
		}
		if (login_flag == 0) {
			map.put("flag", 0 + "");
			System.out.println(login_flag);
			return map;
		} else if (login_flag == 2) {
			map.put("flag", 0 + "");
			System.out.println(login_flag);
			return map;
		} else {
			// String AuthCode = request.getParameter("code");//得到输入的验证码值
			HttpSession session = request.getSession();// 得到刚刚页面的session
			String Confirmcode = (String) session.getAttribute("code");
			System.out.println(code);
			if (code.equals(Confirmcode))// 验证码正确的情况下
			{
				map.put("flag", 1 + "");
				System.out.println("返回成功");
				return map;
			} else {
				map.put("flag", 0 + "");
				System.out.println(login_flag);
				return map;
			}

		}
	}

	@RequestMapping("login") // 登录请求
	public ModelAndView login(User user, HttpServletRequest request)// 这里是获取表单属性
	{
		String s = user.getPassword();
		String smi = convertMD5(s);// 二次加密
		List<User> us = userService.select();// 获取集合
		int login_flag = 0;
		// mav.addObject("us", us);//往视图里面放入数据库中的参数
		// mav.addObject("us_view", user);//往数据库里放入其它页面传来的数据
		for (User u : us) {
			if (u.getName().equals(user.getName()))// 用户名相等
			{
				if (u.getPassword().equals(smi))// 如果密码输入正确
				{
					if (u.getStatue().equals("启用")) {
						login_flag = 1;
					} else {
						login_flag = 2;
					}
				}
			}
		}
		if (login_flag == 0) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("tip", "用户名不存在或密码不符");
			return mav;
		} else if (login_flag == 2) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("tip", "该账号被禁用");
			return mav;
		} else {
			String AuthCode = request.getParameter("validatecode");// 得到输入的验证码值
			HttpSession session = request.getSession();// 得到刚刚页面的session
			String Confirmcode = (String) session.getAttribute("code");
			if (AuthCode.equals(Confirmcode))// 验证码正确的情况下
			{
				ModelAndView mav = new ModelAndView("default");
				login_flag = 0;
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("index");
				mav.addObject("tip", "验证码错误");
				login_flag = 0;
				return mav;
			}

		}
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

}
