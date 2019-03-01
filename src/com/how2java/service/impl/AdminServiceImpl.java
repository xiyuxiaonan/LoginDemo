package com.how2java.service.impl;

import com.how2java.mapper.AdminMapper;
import com.how2java.pojo.Admin;
import com.how2java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service          //�����Զ�װ��
public class AdminServiceImpl implements AdminService{
     @Autowired 
     AdminMapper adminMapper;//�Զ�װ��һ���ӿ�
     
     @Override
 	public Admin validate_Admin(String name)
     {
 	   return adminMapper.select(name);
     };
}
