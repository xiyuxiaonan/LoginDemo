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

// ����spring mvc����һ����������

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	UserService userService;// װ��ӿڵ�ͬʱ���Զ�����һ��������

	@RequestMapping("index") // ��ҳ
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("register") // ע�����
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping("defaultjsp") // ע�����
	public ModelAndView defaultjsp() {
		ModelAndView mav = new ModelAndView("default");
		return mav;
	}

	@RequestMapping("addUser") // ע������
	// request����Ҫ��������ȡ�ͻ��˵����ݼ�����
	public ModelAndView addUser(User user, HttpServletRequest request) {
		if ((user == null || user.getName() == "" || user.getPassword() == ""))// ����û��������벻Ϊ��
		{
			ModelAndView mav = new ModelAndView("register");
			mav.addObject("tip", "�û��������벻��Ϊ��");
			return mav;
		} else {
			String s = user.getPassword();// �õ�������������
			String smi = convertMD5(s);// ���μ���
			user.setPassword(smi);
			userService.add(user);
			ModelAndView mav = new ModelAndView("default");
			return mav;
		}
	}

	@ResponseBody
	@RequestMapping("login_ajax") // ��¼����
	// ���ص���һ��Map����,��ֵ��
	public Map<String, String> login_ajax(HttpServletRequest request, HttpServletResponse response, User user,
			String code)// �����ǻ�ȡ������
	{
		Map<String, String> map = new HashMap<String, String>();
		String s = user.getPassword();
		String smi = convertMD5(s);// ���μ���
		List<User> us = userService.select();// ��ȡ����
		int login_flag = 0;
		System.out.println(smi);
		// mav.addObject("us", us);//����ͼ����������ݿ��еĲ���
		// mav.addObject("us_view", user);//�����ݿ����������ҳ�洫��������
		for (User u : us) {
			if (u.getName().equals(user.getName()))// �û������
			{
				System.out.println(u.getPassword());
				if (u.getPassword().equals(smi))// �������������ȷ
				{
					if (u.getStatue().equals("����")) {
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
			// String AuthCode = request.getParameter("code");//�õ��������֤��ֵ
			HttpSession session = request.getSession();// �õ��ո�ҳ���session
			String Confirmcode = (String) session.getAttribute("code");
			System.out.println(code);
			if (code.equals(Confirmcode))// ��֤����ȷ�������
			{
				map.put("flag", 1 + "");
				System.out.println("���سɹ�");
				return map;
			} else {
				map.put("flag", 0 + "");
				System.out.println(login_flag);
				return map;
			}

		}
	}

	@RequestMapping("login") // ��¼����
	public ModelAndView login(User user, HttpServletRequest request)// �����ǻ�ȡ������
	{
		String s = user.getPassword();
		String smi = convertMD5(s);// ���μ���
		List<User> us = userService.select();// ��ȡ����
		int login_flag = 0;
		// mav.addObject("us", us);//����ͼ����������ݿ��еĲ���
		// mav.addObject("us_view", user);//�����ݿ����������ҳ�洫��������
		for (User u : us) {
			if (u.getName().equals(user.getName()))// �û������
			{
				if (u.getPassword().equals(smi))// �������������ȷ
				{
					if (u.getStatue().equals("����")) {
						login_flag = 1;
					} else {
						login_flag = 2;
					}
				}
			}
		}
		if (login_flag == 0) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("tip", "�û��������ڻ����벻��");
			return mav;
		} else if (login_flag == 2) {
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("tip", "���˺ű�����");
			return mav;
		} else {
			String AuthCode = request.getParameter("validatecode");// �õ��������֤��ֵ
			HttpSession session = request.getSession();// �õ��ո�ҳ���session
			String Confirmcode = (String) session.getAttribute("code");
			if (AuthCode.equals(Confirmcode))// ��֤����ȷ�������
			{
				ModelAndView mav = new ModelAndView("default");
				login_flag = 0;
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("index");
				mav.addObject("tip", "��֤�����");
				login_flag = 0;
				return mav;
			}

		}
	}

	/**
	 * ���ܽ����㷨 ִ��һ�μ��ܣ����ν���
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
