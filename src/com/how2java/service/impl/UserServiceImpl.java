package com.how2java.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.how2java.mapper.UserMapper;
import com.how2java.pojo.User;
import com.how2java.service.UserService;
@Service        //这个类可以自动注入且为业务层
public class UserServiceImpl  implements UserService{
    @Autowired//把接口装配进去
    UserMapper userMapper;
	
    @Override
	public void add(User u) {
	userMapper.add(u);
		
	}
    @Override
	public List<User> select() {
	return userMapper.select();
		
	};
    //这里为什么可以加一个逗号
 
}

