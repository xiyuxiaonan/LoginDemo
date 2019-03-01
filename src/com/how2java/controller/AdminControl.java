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

@Controller      //��������
@RequestMapping("")
public class AdminControl {
	@Autowired
	AdminService adminService;//�Զ�װ�����
	@Autowired
	UserService userService;
	@RequestMapping("adminLogin")//����Ա��¼����
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("adminLogin");
	    return mav;
	}
	@RequestMapping("back_user")//��̨�����û�����
	public ModelAndView back_user(){
		List<User> us=userService.select();
		ModelAndView mav = new ModelAndView("back_user");
		mav.addObject("us", us);
	    return mav;
	}
	@RequestMapping("back_Login")//��̨��¼����
	public ModelAndView back_Login(Admin admin,HttpServletRequest request){
		    Admin ad=new Admin();
		    ad=adminService.validate_Admin(admin.getName());
		    String admin_pwd=String.valueOf(ad.getPassword());
		    String ad_pwd=String.valueOf(admin.getPassword());
		    if(ad_pwd.equals(admin_pwd))
		    {
			ModelAndView mav = new ModelAndView("redirect:/back_user");//�൱�����´�һ��ҳ��
			//ModelAndView mav = new ModelAndView("back_user");//ֻ���½�һ����ͼ�������������ݿ�����
		    return mav;
		    }
		    else
		    {
		    ModelAndView mav = new ModelAndView("adminLogin");
		    mav.addObject("tip","�˺Ų����ڻ��������");
			return mav;
		    }
	}
}
