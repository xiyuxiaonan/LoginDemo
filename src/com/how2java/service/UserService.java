package com.how2java.service;

import com.how2java.pojo.User;
import java.util.List;
public interface UserService {
	void add(User u);
	List<User> select();
}
