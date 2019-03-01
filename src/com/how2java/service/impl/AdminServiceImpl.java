package com.how2java.service.impl;

import com.how2java.mapper.AdminMapper;
import com.how2java.pojo.Admin;
import com.how2java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service          //允许自动装配
public class AdminServiceImpl implements AdminService{
     @Autowired 
     AdminMapper adminMapper;//自动装配一个接口
     
     @Override
 	public Admin validate_Admin(String name)
     {
 	   return adminMapper.select(name);
     };
}
