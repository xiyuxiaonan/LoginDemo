package com.how2java.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.how2java.mapper.UserMapper;
import com.how2java.pojo.User;
import com.how2java.service.UserService;
@Service        //���������Զ�ע����Ϊҵ���
public class UserServiceImpl  implements UserService{
    @Autowired//�ѽӿ�װ���ȥ
    UserMapper userMapper;
	
    @Override
	public void add(User u) {
	userMapper.add(u);
		
	}
    @Override
	public List<User> select() {
	return userMapper.select();
		
	};
    //����Ϊʲô���Լ�һ������
 
}

